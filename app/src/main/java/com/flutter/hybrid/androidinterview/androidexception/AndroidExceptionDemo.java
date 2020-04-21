package com.flutter.hybrid.androidinterview.androidexception;

import android.util.Log;

/***
 * TODO
 *  异常处理机制
 *      1、Java虚拟机用方法调用栈（method invocation stack）来跟踪每个线程中一系列的方法调用过程
 *      2、如果执行方法的过程抛出异常，则JAVA虚拟机必须找找捕获该异常的Catch代码块（方法栈中向上查找，栈顶元素为正在执行的方法）
 *      3、当Java虚拟机追溯到方法栈的栈底时，如果还没有找到处理该异常的代码块，打印当前栈的信息，
 *          判断当前线程是否为主线程，不是主线程就终止当前线程
 *      finally/final/finalize
 *      finalize:垃圾回收器（GC）回到对象使用，当前对象没有索引的时候
 *      final：修饰符，不可变
 *      finally：try-catch代码块回收资源
 */
public class AndroidExceptionDemo {

    /***
     * TODO
     *  finally 不被执行的唯一情况是先执行了用于终止程序的SYstem.exist()方法
     */
    public void test1() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // finally
        }
    }


    /***
     * TODO
     *  finally 会在return语句之前执行
     */
    public int test2() {
        try {
            System.exit(0);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Log.i("test2", "finally 会在return语句之前执行");
            // finally
        }
        return 0;
    }

    /***
     * TODO
     *  finally 代码块虽然在return语句之前执行，但是finally代码块不能通过重新给变量赋值来改变return语句的返回值
     */
    public int test3() {
        int a = 0;
        try {
            a =3;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            a = 4;
        }
        return a;
    }


    /***
     * TODO
     *  不要在finally代码块中写return语句，覆盖try-catch中的return语句，并可能会导致丢失异常
     */
    public int test4() {
        int a = 0;
        try {
            a =3;
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            a = 4;
        }
        return a;
    }

}
