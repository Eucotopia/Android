package com.pvt.binder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Intent intent;
    private IBookManager iBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 启动服务
        startService(new Intent(this, IRemoteService.class));
        intent = new Intent();
        intent.setAction("com.pvt.binder.IRemoteService");
        intent.setComponent(new ComponentName("com.pvt.binder", "com.pvt.binder.IRemoteService"));
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iBookManager = IBookManager.Stub.asInterface(service);
                Log.i(TAG, "onCreate onServiceConnected" + iBookManager);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iBookManager = null;
            }
        }, Context.BIND_AUTO_CREATE);
        Button viewById = findViewById(R.id.button);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> bookList = iBookManager.getBookList();
                    Log.i(TAG, "onCreate: bookList" + bookList);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button viewById1 = findViewById(R.id.stop);

        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

    }


}