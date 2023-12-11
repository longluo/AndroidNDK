package me.longluo.hellojnicallback;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello-jnicallback");
    }

    private TextView mTvJniMsg;

    private TextView mTvTicks;

    private Button mBtnStart;

    private Button mBtnEnd;

    private int hour = 0;

    private int minute = 0;

    private int second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mTvJniMsg = findViewById(R.id.tv_helloJni_msg);
        mTvTicks = findViewById(R.id.tv_ticks);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnEnd = findViewById(R.id.btn_end);
    }

    private void initData() {
        mTvJniMsg.setText(stringFromJNI());

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = minute = second = 0;
                startTicks();
            }
        });

        mBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTicks();
            }
        });
    }

    /*
     * A function calling from JNI to update current timer
     */
    @Keep
    private void updateTimer() {
        ++second;
        if (second >= 60) {
            ++minute;
            second -= 60;
            if (minute >= 60) {
                ++hour;
                minute -= 60;
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String ticks = "" + MainActivity.this.hour + ":" +
                        MainActivity.this.minute + ":" +
                        MainActivity.this.second;
                mTvTicks.setText(ticks);
            }
        });
    }

    public native String stringFromJNI();

    public native void startTicks();

    public native void stopTicks();
}
