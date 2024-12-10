package me.longluo.hellojni;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class HelloJniActivity extends AppCompatActivity {

    private TextView mTvHello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);

        init();
    }

    private void init() {
        mTvHello = findViewById(R.id.hello_textview);
        mTvHello.setText(JniLibrary.getCoreJniVersion() + ", " + JniLibrary.getJniDynamicRegister());
    }

}
