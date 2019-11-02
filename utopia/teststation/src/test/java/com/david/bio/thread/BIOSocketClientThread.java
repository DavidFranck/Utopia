package com.david.bio.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOSocketClientThread {
	public static void main(String[] args) throws Exception {
		// socket
		//
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		threadPool.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					Socket socket = null;
					try {
						socket = new Socket();
						socket.connect(new InetSocketAddress("192.168.0.75", 8888));
						//
						PrintWriter pw = new PrintWriter(socket
								.getOutputStream());
						pw.print("Client " + Thread.currentThread().getName()
								+ " is sending");
						pw.flush();
						socket.shutdownOutput();
						//
						BufferedReader br = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
						StringBuilder sb = new StringBuilder();
						String row = null;
						while ((row = br.readLine()) != null) {
							sb.append(row);
						}
						System.out.println("Client"
								+ Thread.currentThread().getName()
								+ " is receive");
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
		});
	}
}
