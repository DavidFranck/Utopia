package com.david.nio.socket1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClientSocketChannel {
    public static void main(String[] args) throws Exception {
        for (int j = 0; j < 1; j++) {
            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        // 1.创建SocketChannel
                        SocketChannel socketChannel = SocketChannel.open();
                        socketChannel.connect(new InetSocketAddress("localhost", 8888));
    //			socketChannel.connect(new InetSocketAddress("127.0.0.1", 2222));
                        // 2.连接服务器
                        // Client 写
                        ByteBuffer buffer = ByteBuffer.wrap(("Client " + i).getBytes());
                        // buffer.flip();
//                        Thread.sleep(100);
                        socketChannel.write(buffer);
                        socketChannel.shutdownOutput();
                        // Client 读
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        while (true) {
                            buffer.clear();
                            len = socketChannel.read(buffer);
                            if (len == -1)
                                break;
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                baos.write(buffer.get());
                            }
                        }
                        System.out.println(new String(baos.toByteArray()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
