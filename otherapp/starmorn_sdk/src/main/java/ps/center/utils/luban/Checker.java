package ps.center.utils.luban;

import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.jar:ps/center/utils/luban/Checker.class */
enum Checker {
    SINGLE;

    private static final String TAG = "Luban";
    private static final List<String> format = new ArrayList();
    private static final String JPG = ".jpg";
    private static final String JPEG = ".jpeg";
    private static final String PNG = ".png";
    private static final String WEBP = ".webp";
    private static final String GIF = ".gif";
    private final byte[] JPEG_SIGNATURE = {-1, -40, -1};

    Checker() {
    }

    static {
        format.add(JPG);
        format.add(JPEG);
        format.add(PNG);
        format.add(WEBP);
        format.add(GIF);
    }

    boolean isJPG(InputStream is) {
        return isJPG(toByteArray(is));
    }

    int getOrientation(InputStream is) {
        return getOrientation(toByteArray(is));
    }

    private boolean isJPG(byte[] data) {
        if (data == null || data.length < 3) {
            return false;
        }
        byte[] signatureB = {data[0], data[1], data[2]};
        return Arrays.equals(this.JPEG_SIGNATURE, signatureB);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bd, code lost:
    
        if (r9 <= 8) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c0, code lost:
    
        r0 = pack(r7, r8, 4, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ce, code lost:
    
        if (r0 == 1229531648) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d5, code lost:
    
        if (r0 == 1296891946) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d8, code lost:
    
        android.util.Log.e(ps.center.utils.luban.Checker.TAG, "Invalid byte order");
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e1, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e6, code lost:
    
        if (r0 != 1229531648) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e9, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ee, code lost:
    
        r11 = r0;
        r0 = pack(r7, r8 + 4, 4, r11) + 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0103, code lost:
    
        if (r0 < 10) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0109, code lost:
    
        if (r0 <= r9) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0116, code lost:
    
        r8 = r8 + r0;
        r9 = r9 - r0;
        r12 = pack(r7, r8 - 2, 2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x012d, code lost:
    
        r0 = r12;
        r12 = r12 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0132, code lost:
    
        if (r0 <= 0) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0138, code lost:
    
        if (r9 < 12) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x014b, code lost:
    
        if (pack(r7, r8, 2, r11) != 274) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01a3, code lost:
    
        r8 = r8 + 12;
        r9 = r9 - 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x014e, code lost:
    
        r0 = pack(r7, r8 + 8, 2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x015e, code lost:
    
        switch(r0) {
            case 1: goto L67;
            case 2: goto L75;
            case 3: goto L69;
            case 4: goto L75;
            case 5: goto L75;
            case 6: goto L71;
            case 7: goto L75;
            case 8: goto L73;
            default: goto L75;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x018c, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x018e, code lost:
    
        return 180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0192, code lost:
    
        return 90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0195, code lost:
    
        return 270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0199, code lost:
    
        android.util.Log.e(ps.center.utils.luban.Checker.TAG, "Unsupported orientation");
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01a2, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x010c, code lost:
    
        android.util.Log.e(ps.center.utils.luban.Checker.TAG, "Invalid offset");
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0115, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ed, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01ac, code lost:
    
        android.util.Log.e(ps.center.utils.luban.Checker.TAG, "Orientation not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b5, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getOrientation(byte[] r7) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: ps.center.utils.luban.Checker.getOrientation(byte[]):int");
    }

    String extSuffix(InputStreamProvider input) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input.open(), null, options);
            return options.outMimeType.replace("image/", ".");
        } catch (Exception e) {
            return JPG;
        }
    }

    boolean needCompress(int leastCompressSize, String path) {
        if (leastCompressSize > 0) {
            File source = new File(path);
            return source.exists() && source.length() > ((long) (leastCompressSize << 10));
        }
        return true;
    }

    private int pack(byte[] bytes, int offset, int length, boolean littleEndian) {
        int step = 1;
        if (littleEndian) {
            offset += length - 1;
            step = -1;
        }
        int value = 0;
        while (true) {
            int i = length;
            length--;
            if (i > 0) {
                value = (value << 8) | (bytes[offset] & 255);
                offset += step;
            } else {
                return value;
            }
        }
    }

    private byte[] toByteArray(InputStream is) {
        if (is == null) {
            return new byte[0];
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        while (true) {
            try {
                try {
                    int read = is.read(data, 0, data.length);
                    if (read == -1) {
                        break;
                    }
                    buffer.write(data, 0, read);
                } catch (Exception e) {
                    byte[] bArr = new byte[0];
                    try {
                        buffer.close();
                    } catch (IOException e2) {
                    }
                    return bArr;
                }
            } catch (Throwable th) {
                try {
                    buffer.close();
                } catch (IOException e3) {
                }
                throw th;
            }
        }
        try {
            buffer.close();
        } catch (IOException e4) {
        }
        return buffer.toByteArray();
    }
}