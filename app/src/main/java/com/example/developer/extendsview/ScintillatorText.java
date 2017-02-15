package com.example.developer.extendsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Developer on 2017/2/15.
 */

public class ScintillatorText extends TextView {
    private int mViewWidth;
    private int mTranslate;
    private Paint mPaint = new Paint();
    private LinearGradient mLinearGradient;
    private Matrix mLinearMatrix;

    public ScintillatorText(Context context) {
        super(context);
    }

    public ScintillatorText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(
                        0, 0, mViewWidth,0,
                        new int[]{
                                Color.BLUE, 0x00ff00
                                , Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mLinearMatrix = new Matrix();
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLinearMatrix!=null){
            mTranslate +=mViewWidth/10;
            if (mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mLinearMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mLinearMatrix);
            postInvalidateDelayed(100);//延迟100秒重新绘制
        }



    }
}
