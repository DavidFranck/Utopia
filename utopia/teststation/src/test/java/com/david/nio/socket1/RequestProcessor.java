package com.david.nio.socket1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//用线程池管理 I/O 可以增加并发  非阻塞IO下 开启线程不会使线程因为 等待IO资源 而挂起 浪费系统资源
public class RequestProcessor {
	private static ExecutorService threadPool = Executors
			.newFixedThreadPool(10);

	public static void processRequest(final SelectionKey key) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("处理读");
					SocketChannel socketChannel = (SocketChannel) key.channel();
					// Server读 都是套路
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int len = 0;
					while (true) {
						buffer.clear();
						len = socketChannel.read(buffer);
						if (len == -1)
							break;
						buffer.flip();
						while (buffer.hasRemaining()) {
							baos.write(buffer.get());
						}
					}
					// 把baos放入selectKey中 因为不能在这注册 只能调用attach()方法
					key.attach(baos);
					//把需要改成写事件的SelectionKey放入写队列中
					NIOServerSocketChannel.addWriteQueue(key);
					System.out.println("Server receive :"+ new String(baos.toByteArray()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
