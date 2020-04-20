package com.flutter.hybrid.androidinterview.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;


/***
 * TODO
 *  事件分发通过Wait-Notify方式
 *  synchronized 只有当前线程可以访问变量，其他线程不可以访问，当前线程改变变量之后，其他线程会去更新当前变量的值
 *  Volatile 通知java虚拟机当前变量的值的不确定的，需要从主内存中获取
 *  Volatile （java虚拟在不同线程中有自己的内存变量拷贝，主内存只有一份，
 *            可能其他线程读取没有valatile变量时值不一致，因为线程改变变量之后没有及时更新主内存的值）
 *  假如一个线程修改了volatile关键字变量的值，其他线程会被通知修改，保证了所有线程对象拿到的值都是从主内存中获取。
 *  synchronized和volatile的区别
 *  volatile 只可以改变一个变量的值，性能消耗更大，synchronize 可以同时改变多个变量的值
 *  volatile 只可以使用在变量上，synchronize可以作用在类上，方法和变量上
 *  sleep/wait sleep阻塞线程执行不会改变锁的持有情况，wait是锁的机制，需要锁来控制
 *  多线程之线程池
 *      1、降低资源消耗，2、提高响应速度；3、提高线程的可管理性
 *
 */
public class NioServer {

    private Selector selector;

    public void initListen(int port) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = (Iterator<SelectionKey>) selector.keys();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel channel = socketChannel.accept();
                    socketChannel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("data from Server NIO").getBytes()));
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {

                }
            }
        }
    }

}
