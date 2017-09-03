package com.david.nio.socket1;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NIOServerSocketChannel {
	// 写队列
	private static List<SelectionKey> writeQueue = new ArrayList<>();
	// 主线程定义selector
	private static Selector selector = null;

	public static void addWriteQueue(SelectionKey key) {
		synchronized (writeQueue) {
			writeQueue.add(key);
			// 中断selector select()方法的阻塞 打断select()方法 返回0
			selector.wakeup();
		}
	}

	public static void main(String[] args) throws Exception {
		// 1.创建serverSocketChannel
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// 2.绑定端口
		ssc.bind(new InetSocketAddress(8888));
		// 3.设置通道为非阻塞
		ssc.configureBlocking(false);
		// 4.创建通道选择器
		selector = Selector.open();
		// 5.注册事件类型(在通道上注册)
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			System.out.println("开始监听。。。");
			// 6.获取可用的IO通道
			int num = selector.select();
			// 判断当前是否有可用的IO通道
			if (num > 0) {
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				// 遍历keys
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					// 删除当前的key 以免单线程情况下 下次遍历的时候出现问题（2个元素） 正常为1个
					iterator.remove();
					if (key.isAcceptable()) {
						// Thread.sleep(5000);
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
								.channel();
						SocketChannel socketChannel = serverSocketChannel
								.accept();
						System.out.println("开始处理请求。。。"
								+ socketChannel.getRemoteAddress());
						// 注册 读
						// 设置为非阻塞
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (key.isReadable()) {
						 /*
						  * 请求取消此键的通道到其选择器的注册。
						  * 一旦返回，该键就是无效的，并且将被添加到其选择器的 已取消键集  中。
						  * 在进行下一次选择操作时，将从所有选择器的键集中移除该键。 
						  * 进而取消读事件的监控
						  */
						key.cancel();
						RequestProcessor.processRequest(key);
					} else if (key.isWritable()) {
						System.out.println("处理写");
						key.cancel();
						ResponseProcessor.processResponse(key);
					}
				}
				/*
				 * 当读过后 需要修改事件为写  selector.wakeup();会中断selector的阻塞 
				 * 返回0 进入下面的代码块 把selectionKey注册为OP_WRITE
				 */
			} else {
				synchronized (writeQueue) {
					while (writeQueue.size() > 0) {
						// 弹出最先进队列的元素 FIFO(first in first out)
						SelectionKey key = writeQueue.remove(0);
						// 注册写事件
						SocketChannel socketChannel = (SocketChannel) key
								.channel();
						socketChannel.register(selector, SelectionKey.OP_WRITE,
								key.attachment());
					}
				}
			}
		}
	}
}
