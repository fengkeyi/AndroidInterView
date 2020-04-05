package keyi.feng.changegrayskin.view.grayview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


/**
 * 在 App中，我们对于颜色的处理很多时候会采用颜色矩阵，是一个4*5的矩阵，原理是这样的：
 *
 * [ a, b, c, d, e,
 *     f, g, h, i, j,
 *     k, l, m, n, o,
 *     p, q, r, s, t ]
 *
 * 应用到一个具体的颜色[R, G, B, A]上，最终颜色的计算是这样的：
 *
 * R’ = a*R + b*G + c*B + d*A + e;
 * G’ = f*R + g*G + h*B + i*A + j;
 * B’ = k*R + l*G + m*B + n*A + o;
 * A’ = p*R + q*G + r*B + s*A + t;
 *
 * 像灰度这样的效果，我们可以通过ColorMatrix饱和度 API来操作
 * setSaturation(float sat)
 */
public class GrayImageView extends AppCompatImageView {

    private Paint mPaint;

    public GrayImageView(Context context) {
        this(context,null);

    }

    public GrayImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        mPaint = new Paint();
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }



    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
