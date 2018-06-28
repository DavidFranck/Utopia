package com.david.bio.basic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class BIOSocketServerBasic {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 1.创建ServerSocket
		ServerSocket serverSocket = new ServerSocket();
		// 2.设置端口
		serverSocket.bind(new InetSocketAddress(6666));
		while (true) {
			// 3.获取请求报文 accept
			Socket socket = serverSocket.accept();
			// 以下为套路
			// a.读取用户请求 相当于web中的 request
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String len = null;
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}
			System.out.println("接受到客户端的数据为" + sb.toString());
			// b.向客户端响应 相当于web中的的response
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println("你确定一定以及肯定你输入的是" + sb.toString() + "么？");
			/***************************记得flush******************************/
			pw.flush();
			//关流
			br.close();
			pw.close();
			socket.close();
		}
//			serverSocket.close(); 不关
	}
}
