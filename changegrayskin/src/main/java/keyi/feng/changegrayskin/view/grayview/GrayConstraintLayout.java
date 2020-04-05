package keyi.feng.changegrayskin.view.grayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GrayConstraintLayout extends ConstraintLayout {

    private Paint mPaint = new Paint();

    public GrayConstraintLayout(Context context) {
        this(context,null);
    }

    public GrayConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
}
