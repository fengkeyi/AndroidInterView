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
