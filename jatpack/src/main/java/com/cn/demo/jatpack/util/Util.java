package com.cn.demo.jatpack.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.bean.DeviceGroup;
import com.cn.demo.jatpack.manager.Constant;

import java.util.Locale;

public class Util {

    public static String getDes(String key) {
        String language = Locale.getDefault().getLanguage();
        if (!Constant.DES_AIMBOT_CN.containsKey(key)) {
            return key;
        }
        if (language.equals("zh")) {
            return Constant.DES_AIMBOT_CN.get(key);
        } else {
            return Constant.DES_AIMBOT_EN.get(key);
        }
    }

    public static int getUnormalCount(DeviceGroup group) {
        int count = 0;
        for (int i = 0; i < group.getDevices().size(); i++) {
            if (!group.getDevices().get(i).isNormal()) {
                count++;
            }
        }
        return count;
    }

    public static void setCount(Context context, AppCompatTextView textView, int resid, int count) {
        String mMessage = context.getString(resid,count);
        int start = mMessage.indexOf(" ");
        int end = mMessage.lastIndexOf(" ");

        int red = ContextCompat.getColor(context, R.color.error_red);
        int fontSize = context.getResources().getDimensionPixelSize(R.dimen.error_count_size);

        SpannableStringBuilder style = new SpannableStringBuilder(mMessage);

        //设置字体颜色
        style.setSpan(new ForegroundColorSpan(red), start, end,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置字体大小
        style.setSpan(new AbsoluteSizeSpan(fontSize), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }


}
