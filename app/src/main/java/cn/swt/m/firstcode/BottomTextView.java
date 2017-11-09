package cn.swt.m.firstcode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/10/12
 */
public class BottomTextView extends TextView {
    private Context mContext;

    public BottomTextView(Context context) {
        super(context);
    }

    public BottomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private int sroke_width = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //  将边框设为黑色
        paint.setColor(mContext.getResources().getColor(R.color.bottom_line));
        canvas.drawLine(0, this.getHeight() - sroke_width, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
        super.onDraw(canvas);
    }
}
