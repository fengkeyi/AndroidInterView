package keyi.feng.changegrayskin.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.RemoteException;

import java.io.IOException;
import java.io.InputStream;

import keyi.feng.changegrayskin.IRemoteGetBitmap;
import keyi.feng.changegrayskin.R;
import keyi.feng.changegrayskin.activity.base.BaseActivity;

import static keyi.feng.changegrayskin.activity.BinderActivity.KEY_BINDER;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            sendImg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendImg() throws IOException {
        InputStream stream = getAssets().open("img2.jpg");
        final Bitmap bitmap = BitmapFactory.decodeStream(stream);
        Bundle bundle = new Bundle();
        bundle.putBinder(BinderActivity.KEY_BINDER_IMG, new IRemoteGetBitmap.Stub() {
            @Override
            public Bitmap getBitmap() throws RemoteException {
                return bitmap;
            }
        });
        Intent intent = new Intent(MainActivity.this, BinderActivity.class);
        intent.putExtra(KEY_BINDER, bundle);
        startActivity(intent);
    }

}

