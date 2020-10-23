# OkHttpSample

## OkhttpClient
OkhttpClient实现了Call.Factory和WebSocket.Factory

使用了建造者模式，责任链模式

OkhttpClient应该被共享，这样有利于减少内存和线程分配


## Okhttp请求流程

### 创建OkhttpClient
```
public final OkHttpClient okHttpClient = new OkHttpClient();
```
### 构造Request

```
Request.Builder builder = new Request.Builder();
builder.url("https://www.baidu.com")
        .get()
        .addHeader("hello", "world")
        .cacheControl(CacheControl.FORCE_NETWORK)
        .tag("first");
Request request = builder.build();
```
### 创建Call
```
Call call = okHttpClient.newCall(request);
```
newCall 实际上是创建一个RealCall 对象

#### 同步请求和异步请求

- call.execute() 同步请求 必须在子线程中执行

- call.enqueue(callback) 异步请求，可以在主线程中执行

##### 同步请求
把call加入到一个双向队列中，通过getResponseWithInterceptorChain方法, 由RealInterceptorChain来处理网络请求。

##### 异步请求
创建一个AsyncCall并把它加入到一个双向队列中，当调用AsyncCall的executeOn方法时添加到线程池中执行。在run方法中执行getResponseWithInterceptorChain()来做网络请求，未发生异常则回调onResponse，发生异常则回调onFailure

## Dispatcher
okhttp的线程调度
最大请求64个
每个host请求最大5个

## Interceptor
拦截器执行顺序
1. interceptors  用户添加的拦截器
2. RetryAndFollowUpInterceptor  重试和追踪拦截器  从失败中恢复请求和追踪重定向
3. BridgeInterceptor 桥接拦截器   链接application代码到网络代码
4. CacheInterceptor  缓存拦截器   为请求提供缓存 并把响应结果写到缓存中去
5. ConnectInterceptor   连接拦截器   打开目标服务器 进行后续的Interceptor
6. networkInterceptors 用户添加的网络拦截器
7. CallServerInterceptor  调用服务拦截器   是拦截链中最后一个拦截器 把网络发送到服务端


## websocket 待研究