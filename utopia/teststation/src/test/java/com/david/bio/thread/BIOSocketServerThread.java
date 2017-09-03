package com.david.bio.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOSocketServerThread {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 1 ����
		ServerSocket serverSocket = new ServerSocket();
		// 2 �󶨶˿�
		serverSocket.bind(new InetSocketAddress(8888));
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		while (true) {
			// 3 ��ȡ������
			final Socket socket = serverSocket.accept();
			/*
			 * �����߳� �������� ��Ϊ ÿ���пͻ��˷�������Ҫ����һ���߳� 
			 * ��run ���� �ڵ� IO �п�����Ϊ������������
			 * �װ״���һ���߳� ���� �˷�cpu���ڴ���Դ
			 */
			// new Thread(){
			// @Override
			// public void run() {
			//
			// }
			// }.start();
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					// try {
					// if(new Random().nextInt(100)%2 == 0){
					// Thread.sleep(100);
					// }else{
					// Thread.sleep(1000);
					// }
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					System.out.println("Server receive : " + /*sb.toString()
							+ " in thread: "*/			
							" id:  "+ Thread.currentThread().getId() + "  name: "+ Thread.currentThread().getName());
					try {
						// ����˶�
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
						String row;
						StringBuilder sb = new StringBuilder();
						while ((row = br.readLine()) != null) {
							sb.append(row);
						}
						// �����д
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
						pw.print(" Server apend " + sb.toString());
						pw.flush();
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
			});
		}
	}
}
