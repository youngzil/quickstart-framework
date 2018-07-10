/**
 *
 */
package org.jfastlz;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * JFastLZ - Java port of FastLZ. http://www.fastlz.org/
 *
 * Original C version: Copyright (C) 2007 Ariya Hidayat (ariya@kde.org)
 *
 */
public class JFastLZ {

  //private final Log LOG = LogFactory.getLog(getClass());

  protected static final byte[] SIXPACK_MAGIC = { (byte) 137, '6', 'P', 'K', 13,
      10, 26, 10 };

  protected static final String DEFAULT_ARCHIVE_EXTENSION = ".flz";

  /*
   * Always check for bound when decompressing. Generally it is best to leave it
   * defined/true.
   */
  protected static final boolean FASTLZ_SAFE = true;

  // Prevent accessing more than 8-bit at once, except on x86 architectures.
  protected static final boolean FASTLZ_STRICT_ALIGN = true;

  protected static final int MAX_DISTANCE = 8191;

  protected static final int MAX_FARDISTANCE = (65535+MAX_DISTANCE-1);

  private static final int ADLER32_BASE = 65521;

  private static final int HASH_LOG = 13;
  // 8192
  private static final int HASH_SIZE = (1<< HASH_LOG);
  private static final int HASH_MASK = (HASH_SIZE-1);

  private static final int MAX_COPY = 32;
  private static final int MAX_LEN = 264;  /* 256 + 8 */

  //#define HASH_FUNCTION(v,p) { v = FASTLZ_READU16(p); v ^= FASTLZ_READU16(p+1)^(v>>(16-HASH_LOG));v &= HASH_MASK; }
  private static int hashFunction(byte[] p, int offset) {
    int v = JFastLZ.readU16(p, offset);
    v ^= JFastLZ.readU16(p, offset+1) ^ (v>>(16-HASH_LOG));
    v &= (HASH_MASK);

    return v;
  }

  protected static long updateAdler32(long checksum, byte[] buf, int len) {
    // const unsigned char* ptr = (const unsigned char*)buf;
    int ptr = 0;
    long s1 = checksum & 0xffff;
    long s2 = (checksum >> 16) & 0xffff;

    while (len > 0) {
      long k = len < 5552 ? len : 5552;
      len -= k;

      while (k >= 8) {
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
        k -= 8;
      }

      while (k-- > 0) {
        s1 += buf[ptr++] & 0xff;
        s2 += s1;
      }
      s1 = (s1 % ADLER32_BASE) & 0xffffffff;
      s2 = (s2 % ADLER32_BASE) & 0xffffffff;
    }
    return (s2 << 16) + (s1 & 0xffffffff);
  }

  public static int readU16(byte[] data) {
    return readU16(data, 0);
  }

  public static int readU16(byte[] data, int offset) {
    if (offset+1 >= data.length) {
      return data[offset] & 0xFF;
    }
    return (data[offset] & 0xFF) + ((data[offset+1] & 0xFF) << 8);
  }

  public static long readU32(byte[] data) {
    return readU32(data, 0);
  }

  protected static long readU32Fast(byte[] data, int offset) {
    return (data[offset] & 0xFF) + ((data[offset+1] & 0xFF) << 8) +
      ((data[offset+2] & 0xFF) << 16) + ((data[offset+3] & 0xFF) << 24);
  }

  public static long readU32(byte[] data, int offset) {
    // Using BigInteger b/c of unsigned longs.
    // prepend 0x00 to remove unsigned crapness.
    BigInteger a = new BigInteger(new byte[]{0x00,data[offset]});
    BigInteger b = new BigInteger(new byte[]{0x00,data[offset+1]});
    BigInteger c = new BigInteger(new byte[]{0x00,data[offset+2]});
    BigInteger d = new BigInteger(new byte[]{0x00,data[offset+3]});
    b = b.shiftLeft(8);
    c = c.shiftLeft(16);
    d = d.shiftLeft(24);

    return a.longValue() + b.longValue() + c.longValue() + d.longValue();
  }


  protected boolean detectMagic(InputStream is) throws IOException {
    byte[] header = new byte[JFastLZ.SIXPACK_MAGIC.length];

    if (is.read(header) < JFastLZ.SIXPACK_MAGIC.length) {
      // Could not read enough bytes to check the header for magic bytes
      return false;
    }

    for (int i = 0; i < JFastLZ.SIXPACK_MAGIC.length; i++) {
      if (header[i] != JFastLZ.SIXPACK_MAGIC[i]) {
        return false;
      }
    }

    return true;
  }

  public JFastLZLevel getLevel(byte b) {
    int level = (b >> 5) + 1;
    return JFastLZLevel.evaluateLevel(level);
  }

  //--------

  public int fastlzCompress(byte[] input, int inOffset, int inLength,
      byte[] output, int outOffset, int outLength) throws IOException {
    return fastlzCompress(inLength < (1024*64) ? JFastLZLevel.One : JFastLZLevel.Two,
        input, inOffset, inLength, output, outOffset, outLength);
  }

  public int fastlzCompress(JFastLZLevel jfastLZLevel, byte[] input, int inOffset, int inLength,
      byte[] output, int outOffset, int outLength) throws IOException {

    int ip = 0;
    int ipBound = ip + inLength - 2;
    int ipLimit = ip + inLength - 12;

    int op = 0;

    // const flzuint8* htab[HASH_SIZE];
    int[] htab = new int[HASH_SIZE];
    // const flzuint8** hslot;
    int hslot = 0;
    // flzuint32 hval;
    // int OK b/c address starting from 0
    int hval = 0;
    // flzuint32 copy;
    // int OK b/c address starting from 0
    int copy;

    /* sanity check */
    if (inLength < 4) {
      if (inLength != 0) {
        // *op++ = length-1;
        output[outOffset + op++] = (byte) (inLength-1);
        ipBound++;
        while(ip <= ipBound) {
          output[outOffset + op++] = input[inOffset + ip++];
        }
        return inLength+1;
      }
      // else
      return 0;

    }

    /* initializes hash table */
    //  for (hslot = htab; hslot < htab + HASH_SIZE; hslot++)
    for (hslot = 0; hslot < HASH_SIZE; hslot++) {
      //*hslot = ip;
      htab[hslot] = ip;
    }

    /* we start with literal copy */
    copy = 2;
    output[outOffset + op++] = MAX_COPY-1;
    output[outOffset + op++] = input[inOffset + ip++];
    output[outOffset + op++] = input[inOffset + ip++];

    /* main loop */
    while(ip < ipLimit) {
      int ref = 0;

      long distance = 0;

      /* minimum match length */
      // flzuint32 len = 3;
      // int OK b/c len is 0 and octal based
      int len = 3;

      /* comparison starting-point */
      int anchor = ip;

      boolean matchLabel = false;

      /* check for a run */
      if (jfastLZLevel == JFastLZLevel.Two) {
        //if(ip[0] == ip[-1] && FASTLZ_READU16(ip-1)==FASTLZ_READU16(ip+1))
        if(input[inOffset + ip] == input[inOffset + ip-1] &&
            JFastLZ.readU16(input, inOffset + ip-1) == JFastLZ.readU16(input, inOffset + ip+1)) {
          distance = 1;
          ip += 3;
          ref = anchor - 1 + 3;

          /*
           * goto match;
           */
          matchLabel = true;
        }
      }
      if (!matchLabel) {
        /* find potential match */
        // HASH_FUNCTION(hval,ip);
        hval = hashFunction(input, inOffset+ip);
        // hslot = htab + hval;
        hslot = hval;
        // ref = htab[hval];
        ref = htab[hval];

        /* calculate distance to the match */
        distance = anchor - ref;

        /* update hash table */
        //*hslot = anchor;
        htab[hslot] = anchor;

        /* is this a match? check the first 3 bytes */
        if (distance == 0 ||
            (jfastLZLevel == JFastLZLevel.One ?
                (distance >= JFastLZ.MAX_DISTANCE) : (distance >= JFastLZ.MAX_FARDISTANCE)) ||
           input[inOffset + ref++] != input[inOffset + ip++] ||
           input[inOffset + ref++] != input[inOffset + ip++] ||
           input[inOffset + ref++] != input[inOffset + ip++]) {
          /*
           * goto literal;
           */
          output[outOffset + op++] = input[inOffset + anchor++];
          // ip = anchor;
          ip = anchor;
          copy++;
          if(copy == MAX_COPY){
            copy = 0;
            output[outOffset + op++] = MAX_COPY-1;
          }
          continue;

        }

        if (jfastLZLevel == JFastLZLevel.Two) {
          /* far, needs at least 5-byte match */
          if(distance >= JFastLZ.MAX_DISTANCE) {
            if(input[inOffset + ip++] != input[inOffset + ref++] ||
                input[inOffset + ip++] != input[inOffset + ref++]) {
              /*
               * goto literal;
               */
              output[outOffset + op++] = input[inOffset + anchor++];
              // ip = anchor;
              ip = anchor;
              copy++;
              if(copy == MAX_COPY){
                copy = 0;
                output[outOffset + op++] = MAX_COPY-1;
              }
              continue;
            }
            len += 2;
          }

        }
      } // end if(!matchLabel)
      /*
       * match:
       */

      /* last matched byte */
      ip = anchor + len;

      /* distance is biased */
      distance--;

      if(distance == 0) {
        /* zero distance means a run */
        //flzuint8 x = ip[-1];
        byte x = input[inOffset + ip-1];
        while(ip < ipBound){
          if(input[inOffset + ref++] != x) break; else ip++;
        }

      } else {
        for(;;) {
          /* safe because the outer check against ip limit */
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          while(ip < ipBound) {
            if(input[inOffset + ref++] != input[inOffset + ip++]) break;
          }
          break;
        }
      }

      /* if we have copied something, adjust the copy count */
      if(copy != 0) {
        /* copy is biased, '0' means 1 byte copy */
        // *(op-copy-1) = copy-1;
        output[outOffset + op-copy-1] = (byte) (copy-1);

      } else {
        /* back, to overwrite the copy count */
        op--;
      }

      /* reset literal counter */
      copy = 0;

      /* length is biased, '1' means a match of 3 bytes */
      ip -= 3;
      len = ip - anchor;

      /* encode the match */
      if (jfastLZLevel == JFastLZLevel.Two) {
        if(distance < JFastLZ.MAX_DISTANCE) {
          if(len < 7) {
            output[outOffset + op++] = (byte) ((len << 5) + (distance >>> 8));
            output[outOffset + op++] = (byte) (distance & 255);
          } else {
            output[outOffset + op++] = (byte) ((7 << 5) + (distance >>> 8));
            for(len-=7; len >= 255; len-= 255) {
              output[outOffset + op++] = (byte) 255;
            }
            output[outOffset + op++] = (byte) len;
            output[outOffset + op++] = (byte) (distance & 255);
          }
        } else {
          /* far away, but not yet in the another galaxy... */
          if(len < 7) {
            distance -= JFastLZ.MAX_DISTANCE;
            output[outOffset + op++] = (byte) ((len << 5) + 31);
            output[outOffset + op++] = (byte) 255;
            output[outOffset + op++] = (byte) (distance >>> 8);
            output[outOffset + op++] = (byte) (distance & 255);
          } else {
            distance -= JFastLZ.MAX_DISTANCE;
            output[outOffset + op++] = (byte) ((7 << 5) + 31);
            for(len-=7; len >= 255; len-= 255) {
              output[outOffset + op++] = (byte) 255;
            }
            output[outOffset + op++] = (byte) len;
            output[outOffset + op++] = (byte) 255;
            output[outOffset + op++] = (byte) (distance >>> 8);
            output[outOffset + op++] = (byte) (distance & 255);
          }
        }
      } else {
        if(len > MAX_LEN-2) {
          while(len > MAX_LEN-2) {
            output[outOffset + op++] = (byte) ((7 << 5) + (distance >>> 8));
            output[outOffset + op++] = (byte) (MAX_LEN - 2 - 7 - 2);
            output[outOffset + op++] = (byte) (distance & 255);
            len -= MAX_LEN-2;
          }
        }

        if(len < 7){
          output[outOffset + op++] = (byte) ((len << 5) + (distance >>> 8));
          output[outOffset + op++] = (byte) (distance & 255);
        } else {
          output[outOffset + op++] = (byte) ((7 << 5) + (distance >>> 8));
          output[outOffset + op++] = (byte) (len - 7);
          output[outOffset + op++] = (byte) (distance & 255);
        }
      }

      /* update the hash at match boundary */
      //HASH_FUNCTION(hval,ip);
      hval = hashFunction(input, inOffset+ip);
      //htab[hval] = ip++;
      htab[hval] = ip++;

      //HASH_FUNCTION(hval,ip);
      hval = hashFunction(input, inOffset+ip);
      //htab[hval] = ip++;
      htab[hval] = ip++;

      /* assuming literal copy */
      output[outOffset + op++] = MAX_COPY-1;

      continue;

      // Moved to be inline, with a 'continue'
      /*
       * literal:
       *
        output[outOffset + op++] = input[inOffset + anchor++];
        ip = anchor;
        copy++;
        if(copy == MAX_COPY){
          copy = 0;
          output[outOffset + op++] = MAX_COPY-1;
        }
      */
    }

    /* left-over as literal copy */
    ipBound++;
    while(ip <= ipBound) {
      output[outOffset + op++] = input[inOffset + ip++];
      copy++;
      if(copy == MAX_COPY) {
        copy = 0;
        output[outOffset + op++] = MAX_COPY-1;
      }
    }

    /* if we have copied something, adjust the copy length */
    if(copy != 0) {
      //*(op-copy-1) = copy-1;
      output[outOffset + (op-copy-1)] = (byte) (copy-1);
    } else {
      op--;
    }

    if (jfastLZLevel == JFastLZLevel.Two) {
      /* marker for fastlz2 */
      //*(flzuint8*)output |= (1 << 5);
      output[outOffset + 0] |= (1 << 5);
    }

    // return op - (flzuint8*)output;
    return op - 0;

  }

  //--------

  public int fastlzDecompress(byte[] input, int inOffset, int inLength, byte[] output, int outOffset, int maxout) {
    //int level = ((*(const flzuint8*)input) >> 5) + 1;
    JFastLZLevel level = getLevel(input[inOffset+0]);

    return fastlzDecompress(input, inOffset, inLength, output, outOffset, maxout, level);

  }

  public int fastlzDecompress(byte[] input, int inOffset, int inLength,
      byte[] output, int outOffset, int maxout, JFastLZLevel fastLZLevel) {

    if (null == fastLZLevel) {
      throw new IllegalArgumentException("invalid FastLZ level, null");
    }
    if (fastLZLevel != JFastLZLevel.One && fastLZLevel != JFastLZLevel.Two) {
      throw new UnsupportedOperationException("Unsupported FastLZ level: " + fastLZLevel);
    }

    // const flzuint8* ip = (const flzuint8*) input;
    int ip = 0;
    // const flzuint8* ip_limit  = ip + length;
    int ipLimit = inLength;
    // flzuint8* op = (flzuint8*) output;
    int op = 0;
    // flzuint8* op_limit = op + maxout;
    int opLimit = maxout;
    // flzuint32 ctrl = (*ip++) & 31;
    long ctrl = input[inOffset + ip++] & 31;

    int loop = 1;
    do {

      //  const flzuint8* ref = op;
      int ref = op;
      // flzuint32 len = ctrl >> 5;
      long len = ctrl >> 5;
      // flzuint32 ofs = (ctrl & 31) << 8;
      long ofs = (ctrl & 31) << 8;

      if(ctrl >= 32) {
        len--;
        // ref -= ofs;
        ref = (int) (ref - ofs);

        int code;
        if (len == 7-1) {
          if (fastLZLevel == JFastLZLevel.One) {
            // len += *ip++;
            len += (input[inOffset + ip++] & 0xFF);
          } else {
            do {
              code = (input[inOffset + ip++] & 0xFF);
              len += code;
            } while (code == 255);
          }

        }
        if (fastLZLevel == JFastLZLevel.One) {
          //  ref -= *ip++;
          ref = ref - (input[inOffset + ip++] & 0xFF);
        } else {
          code = (input[inOffset + ip++] & 0xFF);
          ref = ref - code;

          /* match from 16-bit distance */
//          if(FASTLZ_UNEXPECT_CONDITIONAL(code==255))
//          if(FASTLZ_EXPECT_CONDITIONAL(ofs==(31 << 8)))
          if (code == 255 && ofs == (31 << 8)) {
            ofs = ((input[inOffset + ip++]) & 0xFF) << 8;
            ofs += (input[inOffset + ip++] & 0xFF);

            ref = (int) (op - ofs - JFastLZ.MAX_DISTANCE);
          }

        }

        if (JFastLZ.FASTLZ_SAFE) {
          // if the output index + length of block(?) + 3(?) is over the output limit?
          if (op + len + 3 > opLimit) {
            return 0;
          }

          // if (FASTLZ_UNEXPECT_CONDITIONAL(ref-1 < (flzuint8 *)output))
          // if the address space of ref-1 is < the address of output?
          // if we are still at the beginning of the output address?
          if (ref-1 < 0) {
            return 0;
          }
        }

        if(ip < ipLimit) {
          ctrl = (input[inOffset + ip++] & 0xFF);
        } else {
          loop = 0;
        }


        if(ref == op) {
          /* optimize copy for a run */
          // flzuint8 b = ref[-1];
          byte b = output[outOffset + ref-1];
          output[outOffset + op++] = b;
          output[outOffset + op++] = b;
          output[outOffset + op++] = b;
          while(len != 0) {
            output[outOffset + op++] = b;
            --len;
          }

        } else {
          // Moved. See below.
          if (!JFastLZ.FASTLZ_STRICT_ALIGN) {
            // const flzuint16* p;
            //int p;
            //  flzuint16* q;
            //int q;
          }
          /* copy from reference */
          ref--;

          // *op++ = *ref++;
          output[outOffset + op++] = output[outOffset + ref++];
          output[outOffset + op++] = output[outOffset + ref++];
          output[outOffset + op++] = output[outOffset + ref++];

          // Never runs b/c FASTLZ_STRICT_ALIGN === true
          if (!JFastLZ.FASTLZ_STRICT_ALIGN) {
            // const flzuint16* p;
            int p;
            //  flzuint16* q;
            int q;

            /* copy a byte, so that now it's word aligned */
            if((len & 1) != 0) {
              output[outOffset + op++] = output[outOffset + ref++];
              len--;
            }

            /* copy 16-bit at once */
            //  q = (flzuint16*) op;
            q = op;
            op += len;
            // p = (const flzuint16*) ref;
            p = ref;
            for(len>>=1; len > 4; len-=4) {
              // *q++ = *p++;
              output[outOffset + q++] = output[outOffset + p++];
              output[outOffset + q++] = output[outOffset + p++];
              output[outOffset + q++] = output[outOffset + p++];
              output[outOffset + q++] = output[outOffset + p++];
            }
            while(len != 0) {
              output[outOffset + q++] = output[outOffset + p++];
              --len;
            }
          } else {
            while(len != 0) {
              output[outOffset + op++] = output[outOffset + ref++];
              --len;
            }
          }

        }
      } else {
        ctrl++;
        if (JFastLZ.FASTLZ_SAFE) {
          if (op + ctrl > opLimit) {
            return 0;
          }
          if (ip + ctrl > ipLimit) {
            return 0;
          }
        }

        //*op++ = *ip++;
        output[outOffset + op++] = input[inOffset + ip++];

        for(--ctrl; ctrl != 0; ctrl--) {
          // *op++ = *ip++;
          output[outOffset + op++] = input[inOffset + ip++];
        }


        loop = (ip < ipLimit ? 1 : 0);
        if(loop != 0) {
          //  ctrl = *ip++;
          ctrl = input[inOffset + ip++] & 0xFF;
        }

      }


    // while(FASTLZ_EXPECT_CONDITIONAL(loop));
    } while((loop != 0));

    //  return op - (flzuint8*)output;
    return op - 0;

  }


  // BETA

  public static long updateAdler32(long checksum, byte[] buf, int off, int len) {
    // const unsigned char* ptr = (const unsigned char*)buf;
    int ptr = 0;
    long s1 = checksum & 0xffff;
    long s2 = (checksum >> 16) & 0xffff;

    while (len > 0) {
      long k = len < 5552 ? len : 5552;
      len -= k;

      while (k >= 8) {
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
        k -= 8;
      }

      while (k-- > 0) {
        s1 += buf[off + ptr++] & 0xff;
        s2 += s1;
      }
      s1 = (s1 % ADLER32_BASE) & 0xffffffff;
      s2 = (s2 % ADLER32_BASE) & 0xffffffff;
    }
    return (s2 << 16) + (s1 & 0xffffffff);
  }

}
