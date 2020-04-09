package keyi.feng.changegrayskin.view.grayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

public class GrayButton extends AppCompatButton {

    private Paint mPaint = new Paint();

    public GrayButton(Context context) {
        this(context,null);
    }

    public GrayButton(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
