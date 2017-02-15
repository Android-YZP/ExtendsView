# ExtendsView
自定义View---对原生控件进行扩展
###1.对原生控件进行扩展
- 在super.onDraw调用之前来实现自己的逻辑,对于父控件来说就是绘制对象前后
- 以上就是通过改变控件的绘制行为来创建自定义View的
- 重写父控件的一些方法来改变一些属性
###2.创建复合控件
- 创建attrs.xml文件写出需要的属性
- 文字,颜色,背景
- 可以通过一下代码获得XML中的属性

        //获得属性集
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        //获得单个属性值
        typedArray.getColor(R.styleable.TopBar_leftTextColor,0);

- 初始化界面
- 定义使用者所要用的接口
- 暴露一个方法给调用者来注册接口回调

         public void setOnTopbarClickListener(topbarClickListener mlistener) {
            this.mlistener = mlistener;
         }

###3.重写View来实现全新的控件
- 通常重写他的onDraw(),onMeasure()方法来实现绘制逻辑,
- 通常重写他的onTouchEvent()方法来实现交互逻辑.

###4.重写ViewGroup来实现全新的父控件


##5.事件拦截机制
  >事件传递: 父控件--->子控件--->控件
  >事件的处理顺序: 控件--->子控件--->父控件

- 主要复写 以下三个方法


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
