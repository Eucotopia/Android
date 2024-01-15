package com.pvt.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

/**
 * @author LIWEI
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("content://com.ryg.chapter_2.book.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().delete(uri, null, null);
    }
}