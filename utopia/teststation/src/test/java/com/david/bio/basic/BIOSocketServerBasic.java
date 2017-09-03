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
		// 1.����ServerSocket
		ServerSocket serverSocket = new ServerSocket();
		// 2.���ö˿�
		serverSocket.bind(new InetSocketAddress(6666));
		while (true) {
			// 3.��ȡ������ accept
			Socket socket = serverSocket.accept();
			// ����Ϊ��·
			// a.��ȡ�û����� �൱��web�е� request
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String len = null;
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}
			System.out.println("���ܵ��ͻ��˵�����Ϊ" + sb.toString());
			// b.��ͻ�����Ӧ �൱��web�еĵ�response
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println("��ȷ��һ���Լ��϶����������" + sb.toString() + "ô��");
			/***************************�ǵ�flush******************************/
			pw.flush();
			//����
			br.close();
			pw.close();
			socket.close();
		}
//			serverSocket.close(); ����
	}
}
