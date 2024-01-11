package com.pvt.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User faker = new User("Faker", 27);
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("user", faker);
        startActivity(intent);
    }
}