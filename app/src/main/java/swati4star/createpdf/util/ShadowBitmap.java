package swati4star.createpdf.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author David Wu (david10608@gmail.com)
 *         Created on 29.10.17.
 */

public class ShadowBitmap {
    private static ShadowBitmap _instance;
    public final Bitmap bmp;
    private final Context mContext;

    private ShadowBitmap(Context context) {
        mContext = context;
        int bmpWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int bmpHeight = ScreenUtils.convertDIPToPixels(mContext, 10);
        Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xFFDFDFDF);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(ScreenUtils.convertDIPToPixels(mContext, 1));
        bmp = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);
        Canvas bmpCanvas = new Canvas(bmp);
        int offset = ScreenUtils.convertDIPToPixels(mContext, 2);
        Paint shadowPaint = new Paint();
        shadowPaint.setStrokeWidth(offset);
        float dy = (offset * 2.0f) / 3.0f;
        bmp.eraseColor(0xFFE5EBF1);
        bmpCanvas.drawLine(0, bmpHeight, bmpWidth, bmpHeight, linePaint);
        shadowPaint.setShadowLayer(offset, 0, dy, 0xff999999);
        bmpCanvas.drawLine(0.0f, -offset, bmpWidth, -offset, shadowPaint);
    }

    public static ShadowBitmap sharedInstance(Context context) {
        if (_instance == null) {
            synchronized (ShadowBitmap.class) {
                if (_instance == null)
                    _instance = new ShadowBitmap(context);
            }
        }
        return _instance;
    }
}
