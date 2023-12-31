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

    private TextView mTvHello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);

        init();
    }

    private void init() {
        mTvHello = findViewById(R.id.hello_textview);
        mTvHello.setText(stringFromJNI());
    }

    /*
     * A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public native String stringFromJNI();

    /*
     * This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
//    external fun unimplementedStringFromJNI(): String?

}
