package com.example.developer.extendsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Developer on 2017/2/15.
 */

public class ExtendsView extends TextView {
    private Paint mPaint1;
    private Paint mPaint2;
    public ExtendsView(Context context) {
        super(context);
    }

    public ExtendsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);

        //绘制2个叠加的矩形
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();//画完之后储存一下
        //绘制文字前平移10个像素
        canvas.translate(10,0);

        //在系统绘制之前来改造一下,绘制的文字
        super.onDraw(canvas);
        canvas.restore();
    }
}
