package com.david.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	public static void main(String[] args) throws Exception {
//		copyFile("1.jpg", "6.jpg");
		copyFile("hello.txt", "hello1.txt");
	}
	public static void copyFile(String src ,String desc) throws Exception{
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(desc);
		FileChannel readChannel = fis.getChannel();
		FileChannel writeChannel = fos.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int len = 0;
		while(true){
			buffer.clear();
			len = readChannel.read(buffer);
			if(len==-1) break;
			buffer.flip();
			while(buffer.hasRemaining()){
				writeChannel.write(buffer);
			}
		}
		fis.close();
		fos.close();
		readChannel.close();
		writeChannel.close();
	}
}
