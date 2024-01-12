1. 创建一个服务

```java
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
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());
}
```

2. 客户端绑定服务时，发送一条消息

```java
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Messenger messenger;


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = Message.obtain(null, 1);
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client.");
            msg.setData(data);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }
}
```

大概步骤：

1. 创建一个 Handler，用于接收客户端发送的消息
2. 创建一个 Messenger，用于接收 Handler
3. 由于 Messenger 底层是使用 Binder 实现的，在 onBind 方法中返回 messenger.getBinder() 即可
4. 客户端只需要正常调用 bindService 函数，然后在 onServiceConnected 中发送消息即可
如果需要客户端接收服务端的消息，同样的道理，在客户端创建一个 Handler，然后创建一个 Messenger，然后在 onServiceConnected 中将服务端返回的 IBinder 转换成 Messenger，然后将 Messenger 传递给服务端，服务端就可以通过这个 Messenger 向客户端发送消息了。
```java
// 将客户端的 replyMessenger 通过 msg 传递给服务端，当服务端拿到这个 msg.replayTo 时，就知道需要发给哪个客户端了
msg.replyTo = replyMessenger;
```