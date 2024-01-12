package com.pvt.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author LIWEI
 */
public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Bundle data = message.getData();
                    Log.i(TAG, "receive msg from Bundle:" + data.toString());
                    String msg = data.getString("msg");
                    Log.i(TAG, "receive msg from Client:" + msg);
                    Messenger replyTo = message.replyTo;
                    Message replyMsg = Message.obtain(null, 2);
                    Bundle replyData = new Bundle();
                    replyData.putString("reply", "hello, this is server.");
                    replyMsg.setData(replyData);
                    try {
                        replyTo.send(replyMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());
}
