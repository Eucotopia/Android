Binder 跨进程通信
> 1. 定义一个 aidl 函数
> 2. 定义一个服务（AndroidManifest 中注册），该服务通过 Binder 对象实现 aidl 中的函数，并且通过 onBinder 返回该对象
> 3. 在主活动中，需要通过 bindService 绑定远程服务，其中第二个参数会返回一个 aidl 对象，我们可以通过该对象与远程服务进行通信