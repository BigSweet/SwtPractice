package cn.swt.m.firstcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/9/19
 */
public class DragView extends View {
    Bitmap mBitmap;
    PointF mPointF = new PointF(0, 0);
    RectF mRectF;
    Matrix mMatrix;
    boolean caDrag;
    Paint mPaint;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outHeight = 800 / 2;
        options.outWidth = 960 / 2;
        mBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.doge, options);
        mRectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mMatrix = new Matrix();
    }


    public final int INVAIL_ID = -1;
    int mPointId;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionIndex = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (mRectF.contains(event.getX(), event.getY())) {
                    mPointId = event.getPointerId(0);
                    caDrag = true;
                    mPointF.set(event.getX(), event.getY());
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                mPointId = event.getPointerId(actionIndex);
                mPointF.set(event.getX(actionIndex), event.getY(actionIndex));
                break;
            case MotionEvent.ACTION_UP:
                mPointId = INVAIL_ID;
                caDrag = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (mPointId == event.getPointerId(actionIndex)) {
                    final int newPointIndex = actionIndex == 0 ? 1 : 0;
                    mPointId = event.getPointerId(newPointIndex);
                    mPointF.set(event.getX(newPointIndex), event.getY(newPointIndex));
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (caDrag) {
                    int index = event.findPointerIndex(mPointId);
                    mMatrix.postTranslate(event.getX(index) - mPointF.x, event.getY(index) - mPointF.y);
                    mPointF.set(event.getX(index), event.getY(index));
                    mRectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                    mMatrix.mapRect(mRectF);

                    invalidate();
                }
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

    }
}
