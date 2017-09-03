package com.david.nio.socket1;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResponseProcessor {
	private static ExecutorService threadPool = Executors
			.newFixedThreadPool(10);

	public static void processResponse(final SelectionKey key) {
		threadPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					// Server写 套路
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					// 取出附件（实现值的传递）
					ByteArrayOutputStream att = (ByteArrayOutputStream) key
							.attachment();
					buffer.put(att.toByteArray());
					buffer.put("服务器追加".getBytes());
					// buffer.put("----服务器追加----".getBytes());
					buffer.flip();
					socketChannel.write(buffer);
					socketChannel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
