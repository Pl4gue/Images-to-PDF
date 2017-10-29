package swati4star.createpdf.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author David Wu (david10608@gmail.com)
 *         Created on 29.10.17.
 *         Item decoration which draws drop shadow of each item views.
 */

public class ViewFilesDividerItemDecoration extends RecyclerView.ItemDecoration {
    final int height;
    final Bitmap shadowBmp;
    private Paint mPaint;

    public ViewFilesDividerItemDecoration(Context context) {
        height = ScreenUtils.convertDIPToPixels(context, 10);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowBmp = ShadowBitmap.sharedInstance(context).bmp;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = height;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int dividerTop = child.getBottom() + params.bottomMargin;
            c.drawBitmap(shadowBmp, 0.0f, dividerTop, mPaint);
        }
    }
}
