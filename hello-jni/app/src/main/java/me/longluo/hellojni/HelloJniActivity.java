package me.longluo.hellojni;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class HelloJniActivity extends AppCompatActivity {

    /*
     * this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/me.longluo.hellojni/lib/libhello-jni.so
     * at the installation time by the package manager.
     */
    static {
        System.loadLibrary("hello-jni");
    }

    private TextView mTvJniHello;

    private TextView mTvJniResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);

        init();
    }

    private void init() {
        mTvJniHello = findViewById(R.id.tv_hello_jni);
        mTvJniHello.setText(stringFromJNI());

        mTvJniResult = findViewById(R.id.tv_jni_result);
        mTvJniResult.setText(String.valueOf(add(7, 9)));
    }

    /*
     * A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public native String stringFromJNI();

    public native int add(int a, int b);

}
