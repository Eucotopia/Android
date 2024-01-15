package com.pvt.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * @author LIWEI
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IBookManager iBookManager = IBookManager.Stub.asInterface(service);
                try {
                    List<Book> books = iBookManager.getBooks();
                    Log.i(TAG, "onServiceConnected: " + books.toString());
                    iBookManager.addBook(new Book("Java", 50));
                    List<Book> books1 = iBookManager.getBooks();
                    Log.i(TAG, "onServiceConnected: " + books1.toString());
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}