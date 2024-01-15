1. 在 build.gradle 中添加 buildFeatures{aidl=true}
2. 创建 IBookManager.aidl ，里面定义函数，如果该 aidl 中依赖 Parcelable 的类，那么同时创建该类的 aidl 文件，并 import
3. 创建服务，并实现 IBookManager.Stub 类，重写 aidl 中定义的函数，在 onBinder 中返回 Stub 对象
4. 在 AndroidManifest.xml 中注册服务
5. 创建客户端，绑定服务，获取 Binder 对象，调用 aidl 中定义的函数