package com.cn.demo.jatpack.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.app.App;
import com.cn.demo.jatpack.databinding.DialogCommentBinding;


public class WarnDialog{

    private DialogCommentBinding commentBinding;

    private AlertDialog mWarnDialog;

    private Context mContext;

    public static WarnDialog getInstance() {
        return Instance.INSTANCE;
    }

    private static class Instance{
        private static final WarnDialog INSTANCE = new WarnDialog(App.getAppContext());
    }

    private WarnDialog(Context context) {
        mContext = context.getApplicationContext();
        init();
    }


    public WarnDialog setContent(int resid) {
        commentBinding.setContent(mContext.getString(resid));
        commentBinding.executePendingBindings();
        return this;
    }

    public WarnDialog setDialogTitle(int resid) {
        commentBinding.setTitle(mContext.getString(resid));
        commentBinding.executePendingBindings();
        return this;
    }

    private void init() {
        commentBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_comment, null, false);
        mWarnDialog = new AlertDialog.Builder(mContext)
                .setView(commentBinding.getRoot()).create();
        mWarnDialog.setCanceledOnTouchOutside(false);
        mWarnDialog.setCancelable(false);

        Window window = mWarnDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = mContext.getResources().getDimensionPixelSize(R.dimen.dialog_comment_width);
        params.height = mContext.getResources().getDimensionPixelSize(R.dimen.dialog_comment_higth);
        window.setAttributes(params);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        commentBinding.dialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWarnDialog.dismiss();
            }
        });
    }

    public void show() {
        mWarnDialog.show();
    }



}
