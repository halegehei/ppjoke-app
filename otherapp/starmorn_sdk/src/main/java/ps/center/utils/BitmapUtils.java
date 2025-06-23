package ps.center.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import androidx.annotation.IntRange;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.jar:ps/center/utils/BitmapUtils.class */
public class BitmapUtils {
    @Deprecated
    public static Bitmap rsBlur(Context context, Bitmap source, @IntRange(from = 1, to = 25) int radius) {
        if (radius < 1) {
            return source;
        }
        if (radius > 25) {
            radius = 25;
        }
        RenderScript renderScript = RenderScript.create(context);
        Allocation input = Allocation.createFromBitmap(renderScript, source);
        Allocation output = Allocation.createTyped(renderScript, input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(source);
        renderScript.destroy();
        return source;
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (horizontal && vertical) {
            matrix.postScale(-1.0f, -1.0f);
        }
        if (horizontal) {
            matrix.postScale(1.0f, -1.0f);
        }
        if (vertical) {
            matrix.postScale(-1.0f, 1.0f);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical, float rotate) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (horizontal) {
            matrix.postScale(1.0f, -1.0f);
        }
        if (vertical) {
            matrix.postScale(-1.0f, 1.0f);
        }
        if (horizontal && vertical) {
            matrix.postScale(-1.0f, -1.0f);
        }
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);
    }

    public static Bitmap uniformScaleWidth(Bitmap bitmap, float expectWidth) {
        float scale;
        if (bitmap == null) {
            return null;
        }
        if (expectWidth <= 0.0f || bitmap.getWidth() == expectWidth) {
            return bitmap;
        }
        float bitmapWidth = bitmap.getWidth();
        if (bitmapWidth > expectWidth) {
            scale = -((bitmapWidth - expectWidth) / bitmapWidth);
        } else {
            scale = (expectWidth - bitmapWidth) / bitmapWidth;
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scale), (int) (bitmap.getHeight() * scale), false);
        bitmap.recycle();
        return scaledBitmap;
    }

    public static String toBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                try {
                    baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    baos.flush();
                    baos.close();
                    byte[] bitmapBytes = baos.toByteArray();
                    result = Base64.encodeToString(bitmapBytes, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                    if (baos != null) {
                        try {
                            baos.flush();
                            baos.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            if (baos != null) {
                try {
                    baos.flush();
                    baos.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return result;
        } catch (Throwable th) {
            if (baos != null) {
                try {
                    baos.flush();
                    baos.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                    throw th;
                }
            }
            throw th;
        }
    }

    public static Bitmap base64ToBitmap(String base64String) {
        String base64 = base64String;
        if (base64.contains(",")) {
            base64 = base64String.split(",")[1];
        }
        byte[] decode = Base64.decode(base64, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        return getRoundedCornerBitmap(bitmap, roundPx, 255, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, int alpha) {
        return getRoundedCornerBitmap(bitmap, roundPx, alpha, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, int alpha, Bitmap.Config config) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setAlpha(alpha);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return output;
    }

    public static boolean saveBitmap(Bitmap bitmap, File saveToFile) {
        try {
            if (saveToFile.getParentFile() != null && !saveToFile.getParentFile().exists()) {
                saveToFile.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(saveToFile);
            if (saveToFile.getName().endsWith(".png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } else if (saveToFile.getName().endsWith(".jpeg") || saveToFile.getName().endsWith(".jpg")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            } else if (saveToFile.getName().endsWith(".webp")) {
                bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Deprecated
    public static boolean saveBitmap(Bitmap bitmap, String saveDir, String fileName) {
        if (saveDir.endsWith("/")) {
            saveDir = saveDir.substring(0, saveDir.length() - 1);
        }
        return saveBitmap(bitmap, new File(saveDir, fileName));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.UNKNOWN ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter) {
        float ratio = Math.min(maxImageSize / realImage.getWidth(), maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());
        return Bitmap.createScaledBitmap(realImage, width, height, filter);
    }
}