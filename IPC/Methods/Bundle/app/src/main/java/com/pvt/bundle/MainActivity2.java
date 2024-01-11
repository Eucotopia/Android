package com.pvt.bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {
    private final static String TAG = MainActivity2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle user = getIntent().getBundleExtra("user");
        int key1 = user.getInt("key1");
        String key2 = user.getString("key2");
        Log.i(TAG, "onCreate: key1" + key1);
        Log.i(TAG, "onCreate: key2" + key2);
    }
}