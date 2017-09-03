package com.david.bio.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOSocketClientBasic {
	public static void main(String[] args) throws Exception {
		//创建一个socket
		Socket socket = new Socket();
		//设置连接
		socket.connect(new InetSocketAddress("192.168.0.75", 6666));
		
		// 以下为套路
		//1.向server发起请求
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print("我是Client2  Wanghh");
		/***************************记得flush  shutdown***************************************/
		//清空缓冲区
		pw.flush();
		//表示流结束了
		socket.shutdownOutput();
		//2.获得响应
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		StringBuilder sb = new StringBuilder();
		String len = null;
		while((len = br.readLine()) != null){
			sb.append(len);
		}
		System.out.println("Client 1 的响应为"+sb.toString());
		br.close();
		pw.close();
		socket.close();
	}
}
