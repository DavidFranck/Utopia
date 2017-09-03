package com.david.bio.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BIOSocketClientBasic {
	public static void main(String[] args) throws Exception {
		//����һ��socket
		Socket socket = new Socket();
		//��������
		socket.connect(new InetSocketAddress("192.168.0.75", 6666));
		
		// ����Ϊ��·
		//1.��server��������
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print("����Client2  Wanghh");
		/***************************�ǵ�flush  shutdown***************************************/
		//��ջ�����
		pw.flush();
		//��ʾ��������
		socket.shutdownOutput();
		//2.�����Ӧ
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		StringBuilder sb = new StringBuilder();
		String len = null;
		while((len = br.readLine()) != null){
			sb.append(len);
		}
		System.out.println("Client 1 ����ӦΪ"+sb.toString());
		br.close();
		pw.close();
		socket.close();
	}
}
