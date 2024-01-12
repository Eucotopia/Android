package com.pvt.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * @author LIWEI
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Messenger messenger;


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 通过服务端返回的IBinder对象创建一个Messenger对象
            messenger = new Messenger(service);
            // 创建一个Message对象，并向该对象中写入what为1的值
            Message msg = Message.obtain(null, 1);
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client.");
            msg.setData(data);
            // 将客户端自己的Messenger通过Message的replyTo参数传递给服务端
            msg.replyTo = replyMessenger;

            try {
                messenger.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private static Messenger replyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    Bundle data = message.getData();
                    String reply = data.getString("reply");
                    Log.i(TAG, "receive msg from Server:" + reply);
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }
}