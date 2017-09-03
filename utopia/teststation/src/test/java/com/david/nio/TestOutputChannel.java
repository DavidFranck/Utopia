package com.david.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class TestOutputChannel {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("hello.txt");
		FileChannel channel = fos.getChannel();
//		ByteBuffer buffer = ByteBuffer.wrap("Hello Autumn".getBytes());
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("Hello Autumn".getBytes());
		buffer.flip();
		channel.write(buffer);
		fos.close();
		channel.close();
	}
}
