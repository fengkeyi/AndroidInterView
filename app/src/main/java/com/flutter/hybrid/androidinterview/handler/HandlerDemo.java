package com.flutter.hybrid.androidinterview.handler;

import android.os.AsyncTask;

import java.util.logging.Handler;

/***
 *  TODO
 *      THreadLocal:当前线程存储的数据仅能从当前线程去除
 *      MessageQueue：具有时间优先级的消息队列，单链表
 *      Looper：消息循环队列，看是否有新的消息到来
 *      Handler：具体处理逻辑的地方（发送消息和处理消息）
 *      Looper在主线程中死循环为什么没有导致界面的卡死？
 *      导致卡死的是在Ui线程中执行耗时操作导致界面出现掉帧，甚至ANR，Looper.loop()这个操作本身不会导致这个情况。
 *      有人可能会说，我在点击事件中设置死循环会导致界面卡死，同样都是死循环，不都一样的吗？Looper会在没有消息的时候阻塞当前线程，释放CPU资源，等到有消息到来的时候，再唤醒主线程。
 *      App进程中是需要死循环的，如果循环结束的话，App进程就结束了。
 *      建议阅读：
 *      《Android中为什么主线程不会因为Looper.loop()里的死循环卡死？》
 *      https://www.zhihu.com/question/34652589
 */
public class HandlerDemo {

    public void simple() {
        // Message Handler MessageQueue ThreadLocal Looper
        Handler handler;
        //FutureTask Excutor
        AsyncTask asyncTask;
    }


}
