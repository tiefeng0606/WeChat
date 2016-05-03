package com.example.administrator.weichart.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.administrator.weichart.R;
import com.example.administrator.weichart.activity.utils.LogUtils;

public class MyView extends View {
    private Canvas mCanvas;//创建画布
    private Bitmap mBitmap;//创建一个bitmap供画布使用
    private Rect mBitmapRect;//创建画bitmap的矩形区域
    private int mBitmapWidth;//图的宽度
    private int mBitmapHeight;//图的高度

    private Bitmap mBottomBitmap;//底图
    private Paint mBottomBitmapPaint;//底图画笔

    private Bitmap mTopBitmap;//覆盖图
    private Paint mTopBitmapPaint;//覆盖图画笔


    private Paint mTextPaint;//字体画笔
    private float mTextSize;//字体大小
    private String mText;//字体
    private Rect mTextBounds;//字体占用大小
    private int xText;//画字体的起点X
    private int yText;//画字体的起点y
    private int mAlpha;//定义透明度值

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //从xml定义属性中拿到对应数值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.MyView_tiefengtext:
                    mText = ta.getString(index);
                    break;
                case R.styleable.MyView_tiefengtextsize:
                    mTextSize = ta.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyView_bottomBitmap:
                    BitmapDrawable bottomDrawable = (BitmapDrawable) ta.getDrawable(index);
                    mBottomBitmap = bottomDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
                    break;
                case R.styleable.MyView_topBitmap:
                    BitmapDrawable topDrawable = (BitmapDrawable) ta.getDrawable(index);
                    mTopBitmap = topDrawable.getBitmap();
                    break;
            }
        }
        //拿到图的宽高
        mBitmapWidth = mBottomBitmap.getWidth();
        mBitmapHeight = mBottomBitmap.getHeight();
        //初始化字体画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        mTextPaint.setTextSize(mTextSize);
        //初始化底图和覆盖图画笔
        mBottomBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到测量宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化bitmap的绘制区域
        mBitmapRect = new Rect(width / 2 - mBitmapWidth / 2, height / 2 - mBitmapHeight / 2 - mTextBounds.height() / 2,
                width / 2 + mBitmapHeight / 2, height / 2 + mBitmapHeight / 2 - mTextBounds.height() / 2);
        xText = width / 2-mTextBounds.width()/2 ;
        yText = mBitmapRect.bottom + mTextBounds.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas);
        drawText(canvas);
    }
    private void drawText(Canvas canvas) {
        mTextPaint.setColor(0x3c3c3c);
        mTextPaint.setAlpha(255 - mAlpha);
        canvas.drawText(mText, xText, yText, mTextPaint);
        mTextPaint.setColor(0x00FF00);
        mTextPaint.setAlpha(mAlpha);
        canvas.drawText(mText, xText, yText, mTextPaint);


    }

    private void drawBitmap(Canvas canvas) {
        //初始化画布
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        //画底图
        mBottomBitmapPaint.setAlpha(255 - mAlpha);
        mCanvas.drawBitmap(mBottomBitmap, null, mBitmapRect, mBottomBitmapPaint);
        //画覆盖图

        mTopBitmapPaint.setAlpha(mAlpha);
        mCanvas.drawBitmap(mTopBitmap, null, mBitmapRect, mTopBitmapPaint);
        LogUtils.E("alpha==" + mAlpha);

        //将底图和覆盖图的综合效果绘制出来
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void setAlpha(float alpha) {
        int ceil = (int) Math.ceil(255 * alpha);
        mAlpha = ceil;
        invalidate();
    }

}