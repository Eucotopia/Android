package com.pvt.fileshard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author LIWEI
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("a.txt")));
            objectOutputStream.writeObject(new User("Faker", 27));
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}