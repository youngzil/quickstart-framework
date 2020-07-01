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
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JFastLZ - Java port of FastLZ. http://www.fastlz.org/
 *
 * Original C version: Copyright (C) 2007 Ariya Hidayat (ariya@kde.org)
 *
 */
public class JFastLZPack {

  private static final JFastLZLevel DEFAULT_JFASTLZLEVEL = JFastLZLevel.Two;
  private static final int DEFAULT_BLOCK_SIZE = 2*64*1024;

  private final Log LOG = LogFactory.getLog(getClass());
  private int blockSize = DEFAULT_BLOCK_SIZE;

  public static void main(String[] args) throws IOException {

    if (args == null || args.length == 0 ||
        args[0].trim().equalsIgnoreCase("--help") ||
        args[0].trim().equalsIgnoreCase("-h")) {
      System.out.println("Usage: java " +
          JFastLZPack.class.getCanonicalName() + " file-to-archive archive-name");
      return;
    }

    new JFastLZPack().packFile(args[0].trim(), args[1].trim());
  }

  public int getBlockSize() {
    return this.blockSize;
  }

  public void setBlockSize(int blockSize) {
    this.blockSize = blockSize;
  }

  /**
   *
   * Wrapper for {@link #packFile(String, JFastLZLevel))},
   * with {@link #DEFAULT_JFASTLZLEVEL}.
   *
   * @param fileName
   * @throws IOException
   */
  public void packFile(String fileName) throws IOException {
    packFile(fileName, DEFAULT_JFASTLZLEVEL);
  }

  /**
   * Packs fileName to archiveName, with {@link #DEFAULT_JFASTLZLEVEL}.
   *
   * Wrapper for {@link #packFile(String, String, JFastLZLevel))}
   *
   * @param fileName
   * @param archiveName
   * @throws IOException
   */
  public void packFile(String fileName, String archiveName) throws IOException {
    packFile(fileName, archiveName, DEFAULT_JFASTLZLEVEL);
  }


  /**
   * Wrapper for {@link #packFile(String, String, JFastLZLevel)},
   * with null archiveName and {@link JFastLZ#DEFAULT_ARCHIVE_EXTENSION}.
   *
   * @param fileName
   * @param jfastLZLevel
   * @throws IOException
   */
  public void packFile(String fileName, JFastLZLevel jfastLZLevel) throws IOException {
    if (null == fileName || "".equals(fileName.trim())) {
      throw new IllegalArgumentException("file to archive is null or empty");
    }

    packFile(fileName, null, jfastLZLevel);
  }

  /**
   * Wrapper for {@link #packFile(File, String, JFastLZLevel)},
   * with null archiveName and {@link JFastLZ#DEFAULT_ARCHIVE_EXTENSION}.
   *
   * @param fileToArchive
   * @throws IOException
   */
  public void packFile(File fileToArchive) throws IOException {
    packFile(fileToArchive, null, DEFAULT_JFASTLZLEVEL);
  }

  /**
   *
   * Wrapper for {@link #packFile(File, String, JFastLZLevel)}.
   *
   * @param fileName
   * @param archiveName
   * @param jfastLZlevel
   * @throws IOException
   */
  public void packFile(String fileName, String archiveName, JFastLZLevel jfastLZlevel) throws IOException {
    if (null == fileName) {
      throw new IllegalArgumentException("file to archive is null");
    }
    if (null == jfastLZlevel) {
      throw new IllegalArgumentException("jfastLZ level is null");
    }

    packFile(new File(fileName), archiveName, jfastLZlevel);
  }

  /**
   * Packs fileToArchive to archiveName using JFastLZLevel jfastLZlevel.
   *
   * If archiveName is null, archiveName is specified to be
   * fileToArchive.getAbsoluteFile().getParent() + File.separator +
   * fileToArchive.getName() + {@link JFastLZ#DEFAULT_ARCHIVE_EXTENSION}.
   *
   * Creates buffered FileInputStream from fileToArchive.
   *
   * Wrapper for {@link #packStream(InputStream, long, String, OutputStream, JFastLZLevel, String)))}.
   *
   * @param fileToArchive
   * @param archiveName
   * @param jfastLZlevel
   * @throws IOException
   */
  public void packFile(File fileToArchive, String archiveName, JFastLZLevel jfastLZlevel) throws IOException {

    if (null == fileToArchive) {
      throw new IllegalArgumentException("File to archive is null");
    }
    if (!fileToArchive.isFile()) {
      throw new RuntimeException("File argument is not a file: "
          + fileToArchive.getName());
    }
    if (!fileToArchive.exists()) {
      throw new RuntimeException("File does not exist: " + fileToArchive.getName());
    }
    if (!fileToArchive.canRead()) {
      throw new RuntimeException("Cannot read file: " + fileToArchive.getName());
    }

    if (null == archiveName) {
      archiveName = fileToArchive.getAbsoluteFile().getParent() + File.separator +
      fileToArchive.getName() + JFastLZ.DEFAULT_ARCHIVE_EXTENSION;
    }

    if (LOG.isInfoEnabled()) {
      LOG.info("packing file: " + fileToArchive.getAbsolutePath());
    }

    InputStream is = null;
    try {
      is = new BufferedInputStream(new FileInputStream(fileToArchive), blockSize);

      packStream(is, fileToArchive.length(), fileToArchive.getName(), null, jfastLZlevel, archiveName);
    } finally {
      try { is.close(); } catch (Exception ignore) {}
    }

  }


  /**
   * Packs input stream to output stream.
   *
   * If output stream is null, creates buffered FileOutputStream from the archiveName.
   *
   * Writes magic file header before calling
   * {@link #packStream(InputStream, long, String, OutputStream, JFastLZLevel)}.
   *
   * @param is
   * @param inputSize
   * @param shownName
   * @param os
   * @param jfastLZLevel
   * @param archiveName
   * @throws IOException
   */
  public void packStream(InputStream is, long inputSize, String shownName, OutputStream os, JFastLZLevel jfastLZLevel,
      String archiveName) throws IOException {

    boolean writeFile = false;
    try {

      if (null == os) {
        writeFile = true;
        File archiveFile = new File(archiveName);
        if (archiveFile.exists()) {
          throw new RuntimeException("archive already exists: " + archiveFile.getAbsolutePath());
        }
        if (LOG.isInfoEnabled()) {
          LOG.info("archive file: " + archiveFile.getAbsolutePath());
        }
        os = new BufferedOutputStream(new FileOutputStream(archiveFile), blockSize);
      }

      writeMagic(os);

      packStream(is, inputSize, shownName, os, jfastLZLevel);
    } finally {
      if (writeFile) {
        try { os.close(); } catch (Exception ignore) {}
      }
    }

  }


  /**
   * Packs input stream to output stream.
   *
   * Wraps input stream in {@link BufferedInputStream} to enable mark()/reset() on stream.
   *
   * @see JFastLZ#fastlzCompress(JFastLZLevel, byte[], int, byte[]).
   *
   * @param is
   * @param inputSize
   * @param shownName
   * @param os
   * @param jfastLZLevel Defaults to {@link #DEFAULT_JFASTLZLEVEL} if null
   * @throws IOException
   */
  public void packStream(InputStream is, long inputSize, String shownName, OutputStream os,
      JFastLZLevel jfastLZLevel) throws IOException {

    if (null == is) {
      throw new IllegalArgumentException("InputStream is null");
    }
    if (inputSize <= 0) {
      throw new IllegalArgumentException("inputSize is invalid (<=0)");
    }
    if (null == os) {
      throw new IllegalArgumentException("OutputStream and is null");
    }

    // To support mark/reset(), for peeking at the magic
    if (!(is instanceof BufferedInputStream)) {
      is = new BufferedInputStream(is, blockSize);
    }

    if (!is.markSupported()) {
      throw new RuntimeException("Error, mark not supported in InputStream.");
    }

    if (null == jfastLZLevel) {
      jfastLZLevel = DEFAULT_JFASTLZLEVEL;
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("compress level: " + jfastLZLevel.getLevel());
    }

    JFastLZ jfastlz = new JFastLZ();

    is.mark(JFastLZ.SIXPACK_MAGIC.length);

    /* already a fastlz archive? */
    if (jfastlz.detectMagic(is)) {
      throw new IllegalArgumentException("File is already a FastLZ archive");
    }

    // Reset back to input stream beginning
    is.reset();

    byte[] buffer = new byte[blockSize];
    byte[] result = new byte[blockSize*2];

    long checksum = 1l;

    long totalRead = 0l;
    long totalCompressed = 0l;
    int chunkSize;

    buffer[0] = (byte) (inputSize & 255);
    buffer[1] = (byte) ((inputSize >> 8) & 255);
    buffer[2] = (byte) ((inputSize >> 16) & 255);
    buffer[3] = (byte) ((inputSize >> 24) & 255);
    /* because fsize is only 32-bit */
    buffer[4] = 0;
    buffer[5] = 0;
    buffer[6] = 0;
    buffer[7] = 0;
    buffer[8] = (byte) ((shownName.length()+1) & 255);
    buffer[9] = (byte) ((shownName.length()+1) >> 8);

    byte[] shownNameBytes = Arrays.copyOfRange(shownName.getBytes(), 0, shownName.length()+1);

    checksum = JFastLZ.updateAdler32(checksum, buffer, 10);
    checksum = JFastLZ.updateAdler32(checksum, shownNameBytes, shownName.length()+1);

    writeChunkHeader(os, 1, 0, 10+shownName.length()+1, checksum, 0);

    // fwrite(buffer, 10, 1, f);
    os.write(buffer, 0, 10);

    //fwrite(shown_name, strlen(shown_name)+1, 1, f);
    os.write(shownNameBytes);

    totalCompressed = 16 + 10 + shownName.length()+1;


    int compressMethod;

    /* read file and place in archive */
    for(;;){

      compressMethod = 1;

      //size_t bytes_read = fread(buffer, 1, BLOCK_SIZE, in);
      int bytesRead = is.read(buffer);

      if(bytesRead <= 0) {
        break;
      }

      totalRead += bytesRead;

      /* too small, don't bother to compress */
      if(bytesRead < 32) {
        compressMethod = 0;
      }

      /* write to output */
      switch(compressMethod) {
        /* FastLZ */
        case 1:
          chunkSize = jfastlz.fastlzCompress(jfastLZLevel, buffer, 0, bytesRead, result, 0, 0);
          checksum = JFastLZ.updateAdler32(1L, result, chunkSize);
          writeChunkHeader(os, 17, 1, chunkSize, checksum, bytesRead);

          //fwrite(result, 1, chunk_size, f);
          os.write(result, 0, chunkSize);

          totalCompressed += 16;
          totalCompressed += chunkSize;
          break;

        /* uncompressed, also fallback method */
        case 0:
        default:
          checksum = 1L;
          checksum = JFastLZ.updateAdler32(checksum, buffer, bytesRead);
          writeChunkHeader(os, 17, 0, bytesRead, checksum, bytesRead);

          //fwrite(buffer, 1, bytes_read, f);
          os.write(buffer, 0, bytesRead);

          totalCompressed += 16;
          totalCompressed += bytesRead;
          break;
      }
    }

    if(totalRead != inputSize) {
      throw new RuntimeException("Error: reading file to archive, " +
      		"read bytes (" + totalRead + ") != file size (" + inputSize + ")");
    }

  }


  private void writeMagic(OutputStream os) throws IOException {
    os.write(JFastLZ.SIXPACK_MAGIC);
  }

  private void writeChunkHeader(OutputStream os, int id, int options, long size,
      long checksum, long extra) throws IOException {
      byte[] buffer = new byte[16];

      buffer[0] = (byte) id;
      buffer[1] = (byte) (id >> 8);
      buffer[2] = (byte) options;
      buffer[3] = (byte) (options >> 8);
      buffer[4] = (byte) size;
      buffer[5] = (byte) (size >> 8);
      buffer[6] = (byte) (size >> 16);
      buffer[7] = (byte) (size >> 24);
      buffer[8] = (byte) checksum;
      buffer[9] = (byte) (checksum >> 8);
      buffer[10] = (byte) (checksum >> 16);
      buffer[11] = (byte) (checksum >> 24);
      buffer[12] = (byte) extra;
      buffer[13] = (byte) (extra >> 8);
      buffer[14] = (byte) (extra >> 16);
      buffer[15] = (byte) (extra >> 24);

      //fwrite(buffer, 16, 1, f);
      os.write(buffer);
    }

  private static final boolean SAFE_PACK = true;

  private void writeChunkHeader(byte[] out, int outOffset, int outLength, int id,
      int options, long size, long checksum, long extra) throws IOException {

      out[outOffset + 0] = (byte) id;
      out[outOffset + 1] = (byte) (id >> 8);
      out[outOffset + 2] = (byte) options;
      out[outOffset + 3] = (byte) (options >> 8);
      out[outOffset + 4] = (byte) size;
      out[outOffset + 5] = (byte) (size >> 8);
      out[outOffset + 6] = (byte) (size >> 16);
      out[outOffset + 7] = (byte) (size >> 24);
      out[outOffset + 8] = (byte) checksum;
      out[outOffset + 9] = (byte) (checksum >> 8);
      out[outOffset + 10] = (byte) (checksum >> 16);
      out[outOffset + 11] = (byte) (checksum >> 24);
      out[outOffset + 12] = (byte) extra;
      out[outOffset + 13] = (byte) (extra >> 8);
      out[outOffset + 14] = (byte) (extra >> 16);
      out[outOffset + 15] = (byte) (extra >> 24);
//      outOffset+= 16;
    }

  public int pack(byte[] in, int inOffset, int inLength,
      byte[] out, int outOffset, int outLength) throws IOException {

    return pack(in, inOffset, inLength, out, outOffset, outLength,
        inLength < (1024*64) ? JFastLZLevel.One : JFastLZLevel.Two);
  }


  public int pack(byte[] in, int inOffset, int inLength,
      byte[] out, int outOffset, int outLength, JFastLZLevel jfastLZLevel) throws IOException {

    if (null == in) {
      throw new IllegalArgumentException("in buffer is null");
    }

    if (null == out) {
      throw new IllegalArgumentException("out buffer is null");
    }

    if (null == jfastLZLevel) {
      jfastLZLevel = DEFAULT_JFASTLZLEVEL;
    }

    if (LOG.isDebugEnabled()) {
      LOG.debug("compress level: " + jfastLZLevel.getLevel());
    }

    if ((inOffset | inLength | (inOffset) | (in.length - (inOffset + inLength))) < 0) {
      throw new IndexOutOfBoundsException();
    } else if (inLength == 0) {
      return 0;
    }
    if ((outOffset | outLength | (outOffset) | (out.length - (outOffset + outLength))) < 0) {
      throw new IndexOutOfBoundsException();
    } else if (outLength == 0) {
      return 0;
    }

    int blockInputLength = Math.min(blockSize, inLength);

    // main header + nain header value + 1 compressed chunk header
    final int outputLengthNeedAtLeast = 16+10+16;

    if (outLength < outputLengthNeedAtLeast) {
      throw new ArrayIndexOutOfBoundsException("Not enough output buffer length for input. " +
      		"Need at least: " + outputLengthNeedAtLeast);
    }

    JFastLZ jfastlz = new JFastLZ();

    long checksum = 1l;

    int totalRead = 0;
    int totalWrite = 0;
    long totalCompressed = 0l;
    int chunkSize;

    int inputSize = inLength;

    out[outOffset+16+0] = (byte) (inputSize & 255);
    out[outOffset+16+1] = (byte) ((inputSize >> 8) & 255);
    out[outOffset+16+2] = (byte) ((inputSize >> 16) & 255);
    out[outOffset+16+3] = (byte) ((inputSize >> 24) & 255);
    // .....
    out[outOffset+16+8] = (byte) ((1) & 255);
    out[outOffset+16+9] = (byte) ((1) >> 8);

    totalWrite += 10;

    checksum = JFastLZ.updateAdler32(1l, out, outOffset+16, 10);

    writeChunkHeader(out, outOffset, outLength, 1, 0, 10, checksum, 0);
    totalWrite += 16;

    totalCompressed = 16 + 10;

    int compressMethod;

    /* read file and place in archive */
    for(;;){

      compressMethod = 1;

      if(totalRead >= inLength) {
        break;
      }

      // If block length is > than what's left in the input,
      // use the length of what's left
      if (blockInputLength > inLength-totalRead) {
        blockInputLength = inLength-totalRead;
      }

      /* too small, don't bother to compress */
      if(blockInputLength < 32) {
        compressMethod = 0;
      }

      /* write to output */
      switch(compressMethod) {
        /* FastLZ */
        case 1:

          if (SAFE_PACK && (outLength-totalWrite) < 16) {
            throw new ArrayIndexOutOfBoundsException("Not enough output length left for header (and compressed data)");
          }
          if (SAFE_PACK && (inLength-totalRead) < blockInputLength) {
            throw new ArrayIndexOutOfBoundsException("Not enough input length left for compressed block");
          }
          // +16 in order to write header before it later
          chunkSize = jfastlz.fastlzCompress(jfastLZLevel, in, inOffset+totalRead, blockInputLength,
              out, outOffset+totalWrite+16, outLength);
          totalRead += blockInputLength;

          checksum = JFastLZ.updateAdler32(1L, out, outOffset+totalWrite+16, chunkSize);
          // write header just before the compressed data
          writeChunkHeader(out, outOffset+totalWrite, outLength, 17, 1, chunkSize, checksum, blockInputLength);
          totalWrite+= chunkSize+16;

          totalCompressed += chunkSize+16;
          break;

        /* uncompressed, also fallback method */
        case 0:
        default:
          checksum = 1L;
          checksum = JFastLZ.updateAdler32(checksum, in, inOffset+totalRead, blockInputLength);

          if (SAFE_PACK && outLength-totalWrite < 16) {
            throw new ArrayIndexOutOfBoundsException("Not enough output length left for chunk header");
          }
          writeChunkHeader(out, outOffset+totalWrite, outLength, 17, 0, blockInputLength, checksum, blockInputLength);
          totalWrite += 16;

          if (SAFE_PACK && inLength-totalRead < blockInputLength) {
            throw new ArrayIndexOutOfBoundsException("Not enough input length left for compressed block");
          }

          if (SAFE_PACK && outLength-totalWrite < blockInputLength) {
            throw new ArrayIndexOutOfBoundsException("Not enough output length left for compressed block");
          }

          System.arraycopy(in, inOffset+totalRead, out, outOffset+totalWrite, blockInputLength);
          totalRead += blockInputLength;
          totalWrite += blockInputLength;

          totalCompressed += 16;
          totalCompressed += blockInputLength;
          break;
      }
    }
    if(totalRead != inLength) {
      throw new RuntimeException("Error: reading data to archive, " +
          "read bytes (" + totalRead + ") != in length (" + inLength + ")");
    }
    return totalWrite;



  }

}
