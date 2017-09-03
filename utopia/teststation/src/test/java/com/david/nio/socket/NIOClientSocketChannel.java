package com.david.nio.socket;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClientSocketChannel {
	// @Test
	// public void test1(){
	// ByteBuffer buffer = ByteBuffer.wrap("Client 1 ".getBytes());
	// while(buffer.hasRemaining()){
	// System.out.println((char)buffer.get());
	// }
	//
	// }
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 2; i++) {
			// 1.创建SocketChannel
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress("localhost", 8888));
			// 2.连接服务器
			// Client 写
			ByteBuffer buffer = ByteBuffer.wrap(("Client "+i).getBytes());
//			buffer.flip();
			Thread.sleep(1000);
			socketChannel.write(buffer);
			socketChannel.shutdownOutput();
			// Client 读
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
			System.out.println(new String(baos.toByteArray()));
		}
	}
}
