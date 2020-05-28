package keyi.feng.changegrayskin.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import keyi.feng.changegrayskin.IRemoteGetBitmap;
import keyi.feng.changegrayskin.R;
import keyi.feng.changegrayskin.activity.base.BaseActivity;
import keyi.feng.changegrayskin.databinding.ActivityBinderBinding;

/***
 * 较大的 bitmap 直接通过 Intent 传递容易抛异常是因为 Intent 启动组件时，
 * 系统禁掉了文件描述符 fd 机制 , bitmap 无法利用共享内存，只能拷贝到 Binder 映射的缓冲区，
 * 导致缓冲区超限, 触发异常; 而通过 putBinder 的方式，
 * 避免了 Intent 禁用描述符的影响，bitmap 写 parcel 时的 allowFds 默认是 true ,
 * 可以利用共享内存，所以能高效传输图片。
 */
public class BinderActivity extends BaseActivity {

    private static final String TAG = "BinderActivity";

    public static final String KEY_BINDER_IMG = "key_BinderActivity_binder_img";
    public static final String KEY_BINDER = "key_BinderActivity_binder";

    private ActivityBinderBinding binderBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binderBinding = DataBindingUtil.setContentView(this, R.layout.activity_binder);
        initImg(getIntent().getBundleExtra(KEY_BINDER));
    }

    private void initImg(Bundle bundle) {
        if (bundle.containsKey(KEY_BINDER_IMG)) {
            IBinder binder = bundle.getBinder(KEY_BINDER_IMG);
            IRemoteGetBitmap iRemoteGetBitmap = IRemoteGetBitmap.Stub.asInterface(binder);
            try {
                Bitmap bitmap = iRemoteGetBitmap.getBitmap();
                binderBinding.imgShowBinderimg.setImageBitmap(bitmap);
                Log.i(TAG, "bitmap size:" + bitmap.getByteCount() / 1024 / 1024);
                binderBinding.executePendingBindings();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.toString());
            }
        }
    }

}
