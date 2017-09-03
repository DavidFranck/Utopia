package com.david.nio.socket;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerSocketChannel {
	public static void main(String[] args) throws Exception {
		//1.创建serverSocketChannel
		ServerSocketChannel ssc = ServerSocketChannel.open();
		//2.绑定端口
		ssc.bind(new InetSocketAddress(8888));
		//3.设置通道为非阻塞
		ssc.configureBlocking(false);
		//4.创建通道选择器
		Selector selector = Selector.open();
		//5.注册事件类型(在通道上注册)
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
			System.out.println("开始监听。。。");
			//6.获取可用的IO通道
			int num = selector.select();
			//判断当前是否有可用的IO通道
			if(num >0){
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				//遍历keys
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					//删除当前的key 以免下次遍历的时候出现问题（2个元素） 正常为1个
					iterator.remove();
					if(key.isAcceptable()){
//						Thread.sleep(5000);
						ServerSocketChannel  serverSocketChannel= (ServerSocketChannel) key.channel();
						SocketChannel socketChannel = serverSocketChannel.accept();
						System.out.println("开始处理请求。。。"+socketChannel.getRemoteAddress());
						//注册读
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					}else if(key.isReadable()){
						SocketChannel socketChannel = (SocketChannel) key.channel();
						//Server读 套路
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int len = 0;
						while(true){
							buffer.clear();
							len = socketChannel.read(buffer);
							if(len == -1) break;
							buffer.flip();
							while(buffer.hasRemaining()){
								baos.write(buffer.get());
							}
						}
						System.out.println("Server receive :"+new String(baos.toByteArray()));
						//注册的时候放附件
						socketChannel.register(selector, SelectionKey.OP_WRITE,baos);
					}else if(key.isWritable()){
						SocketChannel socketChannel = (SocketChannel)key.channel();
						//Server写 套路
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						//取出附件
						ByteArrayOutputStream att = (ByteArrayOutputStream) key.attachment();
						buffer.put(att.toByteArray());
						buffer.put("服务器追加".getBytes());
//						buffer.put("----服务器追加----".getBytes());
						buffer.flip();
 						socketChannel.write(buffer);
						socketChannel.close();
					}
				}
			}
		}
	}
}
