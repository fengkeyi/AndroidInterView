package keyi.feng.changegrayskin.activity.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import keyi.feng.changegrayskin.view.grayview.GrayFrameLayout;

/**
 * https://mp.weixin.qq.com/s/8fTWLYaPhi0to47EUmFd7A
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (name.equals("FrameLayout")) {
            int count = attrs.getAttributeCount();
            for (int i = 0; i < count; i++) {
                String attributeName = attrs.getAttributeName(i);
                String attributeValue = attrs.getAttributeValue(i);
                if (attributeName.equals("id")) {
                    int id = Integer.parseInt(attributeValue.substring(1));
                    String idVal = getResources().getResourceName(id);
                    if ("android:id/content".equals(idVal)) {
                        GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
                        TypedValue a = new TypedValue();
                        getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
                        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                            // windowBackground is a color
                            int color = a.data;
                            grayFrameLayout.setBackgroundColor(color);
                        } else {
                            // windowBackground is not a color, probably a drawable
                            Drawable c = getResources().getDrawable(a.resourceId);
                            grayFrameLayout.setBackground(c);
                        }
                        //设置状态栏颜色
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.GRAY);
                        return grayFrameLayout;
                    }
                }
            }
        }
        return super.onCreateView(name, context, attrs);
    }



}
