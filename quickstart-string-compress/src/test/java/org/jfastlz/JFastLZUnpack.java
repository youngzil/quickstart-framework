/**
 *
 */
package org.jfastlz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JFastLZ - Java port of FastLZ. http://www.fastlz.org/
 *
 * Original C version: Copyright (C) 2007 Ariya Hidayat (ariya@kde.org)
 *
 */
public class JFastLZUnpack {

  private static final int DEFAULT_BLOCK_SIZE = 64*1024;

  private final Log LOG = LogFactory.getLog(getClass());
  private int blockSize = DEFAULT_BLOCK_SIZE;

  /**
   * @param args
   */
  public static void main(String[] args) throws IOException {

    if (args == null || args.length == 0 ||
        args[0].trim().equalsIgnoreCase("--help") ||
        args[0].trim().equalsIgnoreCase("-h")){
      System.out.println("Usage: java " +
          JFastLZUnpack.class.getCanonicalName() + " archive-file");
      return;
    }

    new JFastLZUnpack().unpackFile(args[0].trim());
  }


  public int getBlockSize() {
    return this.blockSize;
  }

  public void setBlockSize(int blockSize) {
    this.blockSize = blockSize;
  }

  /**
   * Unpacks a file to the directory contains the file.
   * Wrapper for {@link #unpackFile(File)}.
   *
   * @param archiveFileName
   * @throws IOException
   */
  public void unpackFile(String archiveFileName) throws IOException {

    if (null == archiveFileName || "".equals(archiveFileName.trim())) {
      throw new IllegalArgumentException("archiveFileName is null or empty");
    }

    unpackFile(new File(archiveFileName));

  }

  /**
   * Unpacks a file to the directory contains the file, via archive.getAbsoluteFile().getParent().
   * Wrapper for {@link #unpackFile(File, String)}.
   *
   * @param archive
   * @throws IOException
   */
  public void unpackFile(File archive) throws IOException {
    if (null == archive) {
      throw new IllegalArgumentException("archive is null");
    }
    if (!archive.isFile()) {
      throw new RuntimeException("File argument is not a file: " + archive.getName());
    }
    if (!archive.exists()) {
      throw new RuntimeException("File does not exist: " + archive.getName());
    }
    if (!archive.canRead()) {
      throw new RuntimeException("Cannot read file: " + archive.getName());
    }

    unpackFile(archive, archive.getAbsoluteFile().getParent());
  }


  /**
   *  Unpacks a file archive to directory unpackDir, via a buffered FileInputStream.
   *  Wrapper for {@link #unpackStream(InputStream, long, OutputStream, String)}.
   *
   * @param archive
   * @param unpackDir
   * @throws IOException
   */
  public void unpackFile(File archive, String unpackDir) throws IOException {
    if (null == archive) {
      throw new IllegalArgumentException("File archive is null");
    }
    if (null == unpackDir) {
      throw new IllegalArgumentException("unpack directory is null");
    }

    if (LOG.isInfoEnabled()) {
      LOG.info("Unpacking archive: " + archive.getAbsolutePath());
    }

    InputStream is = null;
    try {
      is = new BufferedInputStream(new FileInputStream(archive), blockSize*2);
      unpackStream(is, null, unpackDir);
    } finally {
      try { is.close(); } catch (Exception ignore) {}
    }

  }

  /**
   * Unpacks input stream to the output stream.
   * Wrapper for {@link #unpackStream(InputStream, long, OutputStream, String)}, with null
   * for unpackDir.
   *
   * @param is
   * @param inputSize
   * @param os
   * @throws IOException
   */
  public void unpackStream(InputStream is, OutputStream os) throws IOException {
    unpackStream(is, os, null);
  }

  /**
   * Unpacks input stream to the output stream. If output stream is null, output is written
   * to files in the unpackDir argument, utilizing file names stored in the archive.
   *
   *
   * @see JFastLZ#fastlzDecompress(byte[], int, byte[], int, JFastLZLevel)
   *
   * @param is
   * @param inputSize
   * @param os
   * @param unpackDir
   * @throws IOException
   */
  private void unpackStream(InputStream is, OutputStream os, String unpackDir) throws IOException {

    if (null == is) {
      throw new IllegalArgumentException("InputStream is null");
    }

    if (null == os && null == unpackDir) {
      throw new IllegalArgumentException("OutputStream and unpackDir are both null. One or the other must be valid");
    }

    boolean writeFiles = false;
    if (null == os) {
      writeFiles = true;
      File unpackDirectory = new File(unpackDir);
      if (!unpackDirectory.isDirectory()) {
        throw new RuntimeException("Unpack directory is not a directory: " + unpackDir);
      }
      if (LOG.isInfoEnabled()) {
        LOG.info("Unpack directory: " + unpackDirectory.getAbsolutePath());
      }
    }

    byte[] buffer = new byte[blockSize];
    byte[] chunkHeader = new byte[16];

    JFastLZ jfastlz = new JFastLZ();

    // added null != unpackDir
    if (null != unpackDir && !jfastlz.detectMagic(is)) {
      throw new IllegalArgumentException("File is not a FastLZ archive");
    }

    int chunkId;
    int chunkOptions;
    long chunkSize;
    long chunkChecksum;
    long chunkExtra;

    long checksum;

    long decompressedSize = 0l;
    long totalExtracted = 0l;

    byte[] compressedBuffer = null;
    byte[] decompressedBuffer = null;
    long compressedBufsize = 0;
    long decompressedBufsize = 0;


    int nameLength;
    String outputFileName = null;

    while (true) {
      // read chunk header. 16 bytes.
      int bytesRead = is.read(chunkHeader);

      if (bytesRead <= 0) {
        // end of file
        if (LOG.isDebugEnabled()) {
          LOG.debug("No bytes read. End of stream.");
        }
        break;
      }

      // 2 bytes - chunkId
      // 2 bytes - chunkOptions
      // 4 bytes - chunkSize
      // 4 bytes - chunkChecksum
      // 4 bytes - chunkExtra

      chunkId = readChunkHeaderId(chunkHeader);
      chunkOptions = readChunkHeaderOptions(chunkHeader);
      chunkSize = readChunkHeaderSize(chunkHeader);
      chunkChecksum = readChunkHeaderChecksum(chunkHeader);
      chunkExtra = readChunkHeaderExtra(chunkHeader);

      // chunk contains:
      // - self checksum
      // - decompressedSize
      // - compressed file name length & name
      if (chunkId == 1 && chunkSize > 10 && chunkSize < blockSize) {

        outputFileName = null;

        // close current file, if any
        if (writeFiles && null != os) {
          os.close();
          os = null;
        }

        /* file entry */
        is.read(buffer, 0, (int)chunkSize);

        checksum = JFastLZ.updateAdler32(1L, buffer, (int)chunkSize);
        if(checksum != chunkChecksum) {
          LOG.error("Checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum);
          throw new RuntimeException("Error: checksum mismatch!");
        }

        decompressedSize = JFastLZ.readU32(buffer);

        totalExtracted = 0;

        /* get file to extract */
        nameLength = (int)JFastLZ.readU16(buffer, 8);
        if(nameLength > (int)chunkSize - 10) {
          nameLength = (int)chunkSize - 10;
        }
        // trim() b/c ...well it had whitespace in my tests
        outputFileName = new String(buffer, 10, nameLength).trim();

        if (LOG.isDebugEnabled()){
          LOG.debug("decompressedSize: " + decompressedSize);
          LOG.debug("outputFileName: " + outputFileName);
        }

        if (writeFiles) {
          File outputFile = new File(unpackDir + File.separator + outputFileName);
          // check if file exists
          if (outputFile.exists()) {
            throw new RuntimeException("output file already exists: " + outputFile.getAbsolutePath());
          }
          if (LOG.isInfoEnabled()) {
            LOG.info("Unpacking file to: " + outputFile.getAbsolutePath());
          }
          os = new BufferedOutputStream(new FileOutputStream(outputFile), blockSize*2);

        }

      }

      if(chunkId == 17 && outputFileName != null && decompressedSize > 0){

        long remaining = 0l;
        /* uncompressed */
        switch(chunkOptions) {
        /* stored, simply copy to output */
        case 0:
          /* read one block at at time, write and update checksum */
          totalExtracted += chunkSize;
          remaining = chunkSize;
          checksum = 1L;
          while(true) {
            long r = (blockSize < remaining) ? blockSize: remaining;
            int chunkBytesRead = is.read(buffer, 0, (int) r);
            if(chunkBytesRead == 0) {
              break;
            }
            os.write(buffer, 0, chunkBytesRead);

            checksum = JFastLZ.updateAdler32(checksum, buffer, chunkBytesRead);
            remaining -= chunkBytesRead;
          }

          /* verify everything is written correctly */
          if(checksum != chunkChecksum) {
            outputFileName = null;
            LOG.error("Error: checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum + ". Aborted.");
            throw new RuntimeException("Error: checksum mismatch!");
          }
          break;

        /* compressed using FastLZ */
        case 1:
          /* enlarge input buffer if necessary */
          if(chunkSize > compressedBufsize){
            compressedBufsize = chunkSize;
            compressedBuffer = new byte[(int)compressedBufsize];
          }

          /* enlarge output buffer if necessary */
          if(chunkExtra > decompressedBufsize) {
            decompressedBufsize = chunkExtra;
            decompressedBuffer = new byte[(int)decompressedBufsize];
          }

          /* read and check checksum */
          is.read(compressedBuffer, 0, (int)chunkSize);

          checksum = JFastLZ.updateAdler32(1L, compressedBuffer, (int)chunkSize);
          totalExtracted += chunkExtra;

          /* verify that the chunk data is correct */
          if(checksum != chunkChecksum) {
            LOG.error("Error: checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum + ". Skipping...");
            outputFileName = null;
          } else {
            /* decompress and verify */
            remaining = jfastlz.fastlzDecompress(compressedBuffer, 0, (int)chunkSize, decompressedBuffer, 0, (int)chunkExtra);
            if(remaining != chunkExtra){
              LOG.error("Error: decompression failed. decompressed length (" + remaining + ") does not match header value (" + chunkExtra + "). Skipping...");
              outputFileName = null;
            } else {
              os.write(decompressedBuffer, 0, (int)chunkExtra);
            }

          }
          break;

        default:
          LOG.error("Error: unknown compression method: " + chunkOptions);
          outputFileName = null;
          break;


        }

      }

    }

    // close os if we created the os via FileOuputStream
    if (writeFiles && null != os) {
      os.close();
    }

  }

  private int readChunkHeaderId(byte[] chunkHeader) {
    return readChunkHeaderId(chunkHeader, 0);
  }

  private int readChunkHeaderOptions(byte[] chunkHeader) {
    return readChunkHeaderOptions(chunkHeader, 0);
  }

  private long readChunkHeaderSize(byte[] chunkHeader) {
    return readChunkHeaderSize(chunkHeader, 0);
  }

  private long readChunkHeaderChecksum(byte[] chunkHeader) {
    return readChunkHeaderChecksum(chunkHeader, 0);
  }

  private long readChunkHeaderExtra(byte[] chunkHeader) {
    return readChunkHeaderExtra(chunkHeader, 0);
  }


  private int readChunkHeaderId(byte[] chunkHeader, int offset) {
    return JFastLZ.readU16(chunkHeader, offset) & 0xffff;
  }

  private int readChunkHeaderOptions(byte[] chunkHeader, int offset) {
    return JFastLZ.readU16(chunkHeader, offset+2) & 0xffff;
  }

  private long readChunkHeaderSize(byte[] chunkHeader, int offset) {
    return JFastLZ.readU32(chunkHeader, offset+4) & 0xffffffff;
  }

  private long readChunkHeaderChecksum(byte[] chunkHeader, int offset) {
    return JFastLZ.readU32(chunkHeader, offset+8) & 0xffffffff;
  }

  private long readChunkHeaderExtra(byte[] chunkHeader, int offset) {
    return JFastLZ.readU32(chunkHeader, offset+12) & 0xffffffff;
  }

  public void unpackStreamBeta(InputStream is, OutputStream os) throws IOException {

    if (null == is) {
      throw new IllegalArgumentException("InputStream is null");
    }

    if (null == os) {
      throw new IllegalArgumentException("OutputStream is null");
    }

    byte[] buffer = new byte[blockSize];
    byte[] chunkHeader = new byte[16];

    JFastLZ jfastlz = new JFastLZ();

    int chunkId;
    int chunkOptions;
    long chunkSize;
    long chunkChecksum;
    long chunkExtra;

    long checksum;

    long decompressedSize = 0l;
    long totalExtracted = 0l;

    byte[] compressedBuffer = null;
    byte[] decompressedBuffer = null;
    long compressedBufsize = 0;
    long decompressedBufsize = 0;


    int nameLength;
    String outputFileName = null;

    while (true) {
      // read chunk header. 16 bytes.
      int bytesRead = is.read(chunkHeader);

      if (bytesRead <= 0) {
        // end of file
        if (LOG.isDebugEnabled()) {
          LOG.debug("No bytes read. End of stream.");
        }
        break;
      }

      // 2 bytes - chunkId
      // 2 bytes - chunkOptions
      // 4 bytes - chunkSize
      // 4 bytes - chunkChecksum
      // 4 bytes - chunkExtra

      chunkId = readChunkHeaderId(chunkHeader);
      chunkOptions = readChunkHeaderOptions(chunkHeader);
      chunkSize = readChunkHeaderSize(chunkHeader);
      chunkChecksum = readChunkHeaderChecksum(chunkHeader);
      chunkExtra = readChunkHeaderExtra(chunkHeader);

      // chunk contains:
      // - self checksum
      // - decompressedSize
      // - compressed file name length & name
      if (chunkId == 1 && chunkSize > 10 && chunkSize < blockSize) {

        outputFileName = null;

        /* file entry */
        is.read(buffer, 0, (int)chunkSize);

        checksum = JFastLZ.updateAdler32(1L, buffer, (int)chunkSize);
        if(checksum != chunkChecksum) {
          LOG.error("Checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum);
          throw new RuntimeException("Error: checksum mismatch!");
        }

        decompressedSize = JFastLZ.readU32(buffer);

        totalExtracted = 0;

        /* get file to extract */
        nameLength = (int)JFastLZ.readU16(buffer, 8);
        if(nameLength > (int)chunkSize - 10) {
          nameLength = (int)chunkSize - 10;
        }
        // trim() b/c ...well it had whitespace in my tests
        outputFileName = new String(buffer, 10, nameLength).trim();

        if (LOG.isDebugEnabled()){
          LOG.debug("decompressedSize: " + decompressedSize);
          LOG.debug("outputFileName: " + outputFileName);
        }

      }

      if(chunkId == 17 && outputFileName != null){

        long remaining = 0l;
        /* uncompressed */
        switch(chunkOptions) {
        /* stored, simply copy to output */
        case 0:
          /* read one block at at time, write and update checksum */
          totalExtracted += chunkSize;
          remaining = chunkSize;
          checksum = 1L;
          while(true) {
            long r = (blockSize < remaining) ? blockSize: remaining;
            int chunkBytesRead = is.read(buffer, 0, (int) r);
            if(chunkBytesRead == 0) {
              break;
            }
            os.write(buffer, 0, chunkBytesRead);

            checksum = JFastLZ.updateAdler32(checksum, buffer, chunkBytesRead);
            remaining -= chunkBytesRead;
          }

          /* verify everything is written correctly */
          if(checksum != chunkChecksum) {
            outputFileName = null;
            LOG.error("Error: checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum + ". Aborted.");
            throw new RuntimeException("Error: checksum mismatch!");
          }
          break;

        /* compressed using FastLZ */
        case 1:
          /* enlarge input buffer if necessary */
          if(chunkSize > compressedBufsize){
            compressedBufsize = chunkSize;
            compressedBuffer = new byte[(int)compressedBufsize];
          }

          /* enlarge output buffer if necessary */
          if(chunkExtra > decompressedBufsize) {
            decompressedBufsize = chunkExtra;
            decompressedBuffer = new byte[(int)decompressedBufsize];
          }

          /* read and check checksum */
          is.read(compressedBuffer, 0, (int)chunkSize);

          checksum = JFastLZ.updateAdler32(1L, compressedBuffer, (int)chunkSize);
          totalExtracted += chunkExtra;

          /* verify that the chunk data is correct */
          if(checksum != chunkChecksum) {
            LOG.error("Error: checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum + ". Skipping...");
            outputFileName = null;
          } else {
            /* decompress and verify */
            remaining = jfastlz.fastlzDecompress(compressedBuffer, 0, (int)chunkSize, decompressedBuffer, 0, (int)chunkExtra);

            if(remaining != chunkExtra){
              LOG.error("Error: decompression failed. decompressed length (" + remaining + ") does not match header value (" + chunkExtra + "). Skipping...");
              outputFileName = null;
            } else {
              os.write(decompressedBuffer, 0, (int)chunkExtra);
            }

          }
          break;

        default:
          LOG.error("Error: unknown compression method: " + chunkOptions);
          outputFileName = null;
          break;


        }

      }

    }


  }


  /**
   *
   * @param in
   * @param inOffset
   * @param inLength
   * @param out
   * @param outOffset
   * @param outLength
   * @return length of unpacked data. 0 for need more input, or -1 for need
   * more output space.
   * @throws IOException
   */
  public int unpack(final byte[] in, final int inOffset, final int inLength,
      final byte[] out, final int outOffset, final int outLength) throws IOException {

    if (null == in) {
      throw new IllegalArgumentException("in buffer is null");
    }

    if (null == out) {
      throw new IllegalArgumentException("out buffer is null");
    }

    if ((inOffset | inLength | (inOffset) | (in.length - (inOffset + inLength))) < 0) {
      throw new IndexOutOfBoundsException();
    } else if (inLength == 0) {
      return 0;
    }
    if ((outOffset | outLength | (outOffset) | (out.length - (outOffset + outLength))) < 0) {
      throw new IndexOutOfBoundsException();
    } else if (outLength == 0) {
      return -1;
    }

    JFastLZ jfastlz = new JFastLZ();

    int chunkId;
    int chunkOptions;
    long chunkSize;
    long chunkChecksum;
    long chunkExtra;

    long checksum;

    long decompressedSize = -1l;
    long totalExtracted = 0l;

    int bytesRead = 0;
    int totalWrite = 0;

    while (true) {

      if (bytesRead >= inLength) {
        // end of data
        if (LOG.isDebugEnabled()) {
          LOG.debug("No bytes read. End of stream.");
        }
        break;
      }

      // 2 bytes - chunkId
      // 2 bytes - chunkOptions
      // 4 bytes - chunkSize
      // 4 bytes - chunkChecksum
      // 4 bytes - chunkExtra

      chunkId = readChunkHeaderId(in, inOffset+bytesRead);
      chunkOptions = readChunkHeaderOptions(in, inOffset+bytesRead);
      chunkSize = readChunkHeaderSize(in, inOffset+bytesRead);
      chunkChecksum = readChunkHeaderChecksum(in, inOffset+bytesRead);
      chunkExtra = readChunkHeaderExtra(in, inOffset+bytesRead);

      bytesRead += 16;

      // chunk contains:
      // - self checksum
      // - decompressedSize
      // - compressed file name length & name
      if (chunkId == 1 && chunkSize >= 10 && chunkSize < blockSize) {

        checksum = JFastLZ.updateAdler32(1L, in, inOffset+bytesRead, (int)chunkSize);
        if(checksum != chunkChecksum) {
          LOG.error("Checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum);
          throw new RuntimeException("Error: checksum mismatch!");
        }

        decompressedSize = JFastLZ.readU32(in, inOffset+bytesRead);
        bytesRead += 10;
        totalExtracted = 0;
      }

      if(chunkId == 17 && decompressedSize >= 0){

        int remaining = 0;

        switch(chunkOptions) {
        /* stored, simply copy to output */
        case 0:
          /* read one block at at time, write and update checksum */
          totalExtracted += chunkSize;
          remaining = (int) chunkSize;
          checksum = 1L;
          while(true) {
            int r = (blockSize < remaining) ? blockSize: remaining;

            if (remaining == 0 || bytesRead+r > inLength) {
              break;
            }

            System.arraycopy(in, inOffset+bytesRead, out, outOffset+totalWrite, r);

            bytesRead += r;

            checksum = JFastLZ.updateAdler32(checksum, out, outOffset+totalWrite, r);
            totalWrite += r;
            remaining -= r;
          }

          /* verify everything is written correctly */
          if(checksum != chunkChecksum) {
            LOG.error("Error: checksum mismatch. Got " + checksum + " Expecting " + chunkChecksum + ". Aborted.");
            throw new RuntimeException("Error: checksum mismatch!");
          }
          break;

        /* compressed using FastLZ */
        case 1:
          if (chunkSize > (inLength-bytesRead)) {
            if (LOG.isWarnEnabled()) {
              LOG.warn("Next chunk is outside remaining input buffer. " +
              "need more input.");
            }
            // signaling give me more input
            return 0;

          }
          if (chunkExtra > (outLength-totalWrite)) {
            if (LOG.isWarnEnabled()) {
              LOG.warn("decompressed chunk is greater than remaining output " +
              "buffer. need more output space.");
            }
            // signaling give me a bigger output buffer
            return -1;
          }

          /* read and check checksum */
          checksum = JFastLZ.updateAdler32(1L, in, inOffset+bytesRead, (int)chunkSize);
          totalExtracted += chunkExtra;

          /* verify that the chunk data is correct */
          if(checksum != chunkChecksum) {
            LOG.error("Error: checksum mismatch. Got " + checksum +
                " Expecting " + chunkChecksum + ". Skipping...");
            decompressedSize = -1;
          } else {
            /* decompress and verify */
            remaining = jfastlz.fastlzDecompress(in, inOffset+bytesRead, (int)chunkSize,
                out, outOffset+totalWrite, (int)chunkExtra);
            bytesRead += chunkSize;
            totalWrite += remaining;

            if(remaining != chunkExtra){
              LOG.error("Error: decompression failed. decompressed length (" + remaining +
                  ") does not match header value (" + chunkExtra + "). Skipping...");
              decompressedSize = -1;
            }

          }
          break;

        default:
          LOG.error("Error: unknown compression method: " + chunkOptions);
          decompressedSize = -1;
          break;


        }

      }

    }

    return totalWrite;
  }


}
