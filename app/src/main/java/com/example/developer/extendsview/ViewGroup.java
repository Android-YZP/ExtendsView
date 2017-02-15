package com.example.developer.extendsview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Developer on 2017/2/15.
 * 自定义ViewGroup
 */

public class ViewGroup extends RelativeLayout {

    private String mTitle;
    private float mTitleTestSize;
    private int mTitleTextColor;

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private Button mLButton;
    private Button mRButton;
    private TextView mTitleView;
    private LayoutParams mLeftParams;

    private topbarClickListener mlistener;

    public ViewGroup(Context context) {
        super(context);
    }

    public ViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initArrts(context, attrs);
        initView(context);

    }

    private void initView(Context context) {
        mLButton = new Button(context);
        mRButton = new Button(context);
        mTitleView = new TextView(context);

        //赋值
        mLButton.setTextColor(mLeftTextColor);
        mLButton.setText(mLeftText);
        mLButton.setBackground(mLeftBackground);

        mRButton.setTextColor(mRightTextColor);
        mRButton.setText(mRightText);
        mRButton.setBackground(mRightBackground);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTestSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLButton, mLeftParams);

        LayoutParams mRightParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mRButton, mRightParams);

        LayoutParams mTitleParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

    }

    private void initArrts(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitle = typedArray.getString(R.styleable.TopBar_title);
        mTitleTestSize = typedArray.getDimension(R.styleable.TopBar_titleTestSize, 0);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);

        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = typedArray.getString(R.styleable.TopBar_rightText);
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        //调用recycle方法来避免重新创建时候的错误
        typedArray.recycle();//资源回收

        //暴露接口给调用者
        mRButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.rightClick();
            }
        });
        mLButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.leftClick();
            }
        });
    }

    //暴露一个方法给调用者来注册接口回调
    //通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mlistener) {
        this.mlistener = mlistener;
    }

    //定义一个接口给调用者使用
    public interface topbarClickListener {
        void leftClick();
        void rightClick();
    }


}
