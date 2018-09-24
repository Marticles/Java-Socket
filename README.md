# Java Socket编程

这次学习的是服务端与客户端之间的Socket通信，最后完成一个Socket实现多线程下的局域网实时聊天的小demo。

## 基于TCP的的Socket通信

服务器端的流程大致如下：
1. 创建ServerSocket对象，绑定监听端口
	```java
	ServerSocket serverSocket = new ServerSocket(port);
	```
2. 通过accept()方法监听客户端请求
	```java
	Socket socket = serverSocket.accept();
	```
3. 建立连接后通过Input Stream读取客户端发送的请求信息
	```java
	inputStream = socket.getInputStream();
	```
4. 通过Output Stream向客户端发送响应信息
	```java
	outputStream = socket.getOutputStream();
	```
5. 关闭资源

客户端的流程大致如下：
1. 创建Socket对象，设定连接服务器地址与端口号
	```java
	Socket socket = new Socket("localhost", port);
	```
2. 建立连接后，通过Output Stream向服务端发送请求信息
	```java
	OutputStream outputStream = socket.getOutputStream();
	```
3. 通过Input Stream获取服务器响应的信息
	```java
	InputStream inputStream = socket.getInputStream();
	```
4. 关闭资源

## 多线程完成TCP通信

Java中有四种实现线程池的方式，我使用的cachedThreadPool(缓存线程池)，用来实现服务器与多客户端间的通信，流程如下：

1. 服务器端创建ServerSocket对象，绑定监听端口，循环调用accept()方法监听客户端请求
2. 客户端创建一个socket对象与服务器建立连接
3. 服务器创建一个线程池，当接收到客户端的有新的请求时在该线程池内新建一个线程并创建socket与该客户端连接
4. 完成通信后释放连接，空余线程将被保留一段时间(cachedThreadPool为60s)，服务器继续等待新的连接

## 基于UDP的Socket通信

服务端流程：
1. 创建DatagramSocket，指定端口号
	```java
	DatagramSocket socket = new DatagramSocket(port);
	```
2. 创建DatagramPacket
   ```java
   byte[] data = new byte[1024]; // 1024个字节
   DatagramPacket packet = new DatagramPacket(data, data.length);
   ```
3. 接受客户端发送数据
   ```java
   socket.receive(packet);
   ```
4. 读取数据
   ```java
   String info = new String(data, 0, packet.getLength()); //bytes转为String
   ```

客户端流程：
1. 定义连接服务器信息与发送信息
	```java
   InetAddress address = InetAddress.getByName("localhost");
   int port = port;
   byte[] data = "test info from client".getBytes();
   ```
2. 创建DatagramPacket，包含将要发送的信息
	```java
   DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
   ```
3. 创建DatagramSocket
	```java
   DatagramSocket socket = new DatagramSocket();
   ```
4. 发送数据
	```java
   socket.send(packet);
   ```

## Socket实现局域网实时聊天

服务端与客户端的流程参考上面，但是有两个点需要注意。

一是服务端需要保证当一个客户端发送了新消息时，其他所有客户端上都应显示出来，即需要服务端做一个转发，同时服务端需要开启多线程处理来自每个客户端的消息。

二是需要用一个id区分每个客户端，显示的消息中如果是自己发的应该显示的是“来自自己”，其他人的消息来源则用id号标识。


