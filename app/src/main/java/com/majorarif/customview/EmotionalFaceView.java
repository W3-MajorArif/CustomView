package com.majorarif.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class EmotionalFaceView extends View {


    private static int DEFAULT_FACE_COLOR = Color.YELLOW;
    private static int DEFAULT_EYES_COLOR = Color.BLACK;
    private static int DEFAULT_MOUTH_COLOR = Color.BLACK;
    private static int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static float DEFAULT_BORDER_WIDTH = 4.0f;

    public static long HAPPY = 0L;
    public static long SAD = 1L;

    private Paint paint = new Paint();
    private Path mouthPath = new Path();
    private int size = 0;

    private int faceColor = DEFAULT_FACE_COLOR;
    private int eyesColor = DEFAULT_EYES_COLOR;
    private int mouthColor = DEFAULT_MOUTH_COLOR;
    private int borderColor = DEFAULT_BORDER_COLOR;
    private float borderWidth = DEFAULT_BORDER_WIDTH;


    private long happinessState = HAPPY;

    public void setHappinessState(long happinessState) {
        Log.i("button click", "setter called");
        this.happinessState = happinessState;
        invalidate();
    }

    public EmotionalFaceView(Context context) {
        super(context);
        init(null, 0);
    }

    public EmotionalFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EmotionalFaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes


        paint.setAntiAlias(true);
        setupAttributes(attrs, defStyle);



    }


    private void setupAttributes(AttributeSet attrs, int defStyle) {
        // Obtain a typed array of attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.EmotionalFaceView, defStyle, 0);


        //ta.getColor(com.majorarif.customviews.R.styleable.ValueBar_baseColor, Color.BLACK);

        // Extract custom attributes into member variables
        happinessState = typedArray.getInt(R.styleable.EmotionalFaceView_state, (int) HAPPY);
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR);
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR);
        mouthColor = typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR);
        borderColor = typedArray.getColor(R.styleable.EmotionalFaceView_borderColor,
                DEFAULT_BORDER_COLOR);
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth,
                DEFAULT_BORDER_WIDTH);

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle();
    }


    @Override
     protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        size = Math.min(getMeasuredWidth(), getMeasuredHeight());

        setMeasuredDimension(size, size);
    }

//    @Override
//     protected Bundle onSaveInstanceState() {
//        Bundle bundle = new Bundle();
//
//        bundle.putLong("happinessState", happinessState);
//        bundle.putParcelable("superState", super.onSaveInstanceState());
//
//        return bundle;
//    }

//    @Override
//    protected void onRestoreInstanceState(Percelable state) {
//        Object viewState = state;
//
//        if (typeof(viewState) == Bundle.class) {
//            happinessState = viewState.getLong("happinessState", HAPPY)
//            viewState = viewState.getParcelable("superState")
//        }
//
//        super.onRestoreInstanceState(viewState)
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;


        drawFaceBackground(canvas);
        drawEyes(canvas);
        drawMouth(canvas);
    }

    private void drawFaceBackground(Canvas canvas) {


        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        float radius = size / 2f;
        canvas.drawCircle(size / 2f, size / 2f, radius, paint);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);
    }

    private void drawEyes(Canvas canvas) {
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        RectF leftEyeRect = new RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f);
        canvas.drawOval(leftEyeRect, paint);

        RectF rightEyeRect = new RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f);

        canvas.drawOval(rightEyeRect, paint);
    }
    private void drawMouth(Canvas canvas) {
        // Clear
        mouthPath.reset();

        mouthPath.moveTo(size * 0.22f, size * 0.7f);

        if (happinessState == HAPPY) {
            // Happy mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f);
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f);
        } else {
            // Sad mouth path
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f);
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f);
        }

        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);

        // Draw mouth path
        canvas.drawPath(mouthPath, paint);
    }


}
