package com.david.bio.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOSocketClientThread1 {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 50; i++) {
			Socket socket = null;
			try {
				// 创建了一个socket
				socket = new Socket();
				// 创建连接
				socket.connect(new InetSocketAddress("192.168.0.75", 8888));
				// 客户端写
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.print("Client " + i
						+ " is sending");
				pw.flush();
				socket.shutdownOutput();
				// 客户端读
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String row = null;
				while ((row = br.readLine()) != null) {
					sb.append(row);
				}
				System.out.println("Client" + i + " is receive");
				pw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
