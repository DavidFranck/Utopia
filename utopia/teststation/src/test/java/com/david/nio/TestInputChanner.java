package com.david.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestInputChanner {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("hello.txt");
		FileChannel channel = fis.getChannel();
		//缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int len = 0;
		//写到这个局部变量中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while(true){
			//每次运行结束后clear 重置
			buffer.clear();
			len = channel.read(buffer);
			if(len==-1)break;
			//read 后flip一下 
			buffer.flip();
			while(buffer.hasRemaining()){
				baos.write(buffer.get());
			}
		}
		System.out.println(new String(baos.toByteArray()));
		channel.close();
		fis.close();
	}
}
