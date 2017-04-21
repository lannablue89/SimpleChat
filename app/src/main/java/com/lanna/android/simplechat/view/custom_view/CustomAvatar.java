package com.lanna.android.simplechat.view.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by lanna on 4/22/17.
 */

public class CustomAvatar extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;

    public CustomAvatar(Context context) {
        super(context);
        init();
    }

    public CustomAvatar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomAvatar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
    }

    public void setColor(int color) {
        paint.setColor(color);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int radius = canvas.getWidth()/2;
        canvas.drawCircle(radius, radius, radius, paint);

        super.onDraw(canvas);
    }
}
