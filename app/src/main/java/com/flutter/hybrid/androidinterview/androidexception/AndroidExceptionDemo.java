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
 *   GC 相关
 *    -ClassLoader：动态加载class文件到内存中（java虚拟机不会一次性加载全部的class文件）
 *      -BootStrap ClassLoader（启动类加载器底层为C++编写）
 *      -Extension ClassLoader（其他类加载器java编写）
 *      -App ClassLoader      （其他类加载器java编写）
 *      双亲委托模型：自定义类加载器--》应用程序类加载器--》扩展类加载器--》启动类加载器
 *          原理：当一个类（class)没有加载到内存，会从自定义类加载器开始查找，而自定义类加载器会先去父类查找，也就是应用程序类加载器，
 *              同理，应用程序类加载器会先从父类扩展类加载器中查找是否有改class文件，扩展类加载器又先去启动类加载器中查找是否存在该
 *              class文件（先从父类中查找class文件）、
 *     Java 堆/栈
 *       内存分配策略
 *          1、静态存储区（方法区）：主要存放静态变量、全局static数据和常量
 *          2、栈区：方法体内的局部变量都在栈区上创建（效率高，容量有限），存放对像引用和基本类型变量
 *          3、堆区（动态内存分配区）：在程序运行时直接new出来的内存，这部分内存由java垃圾回收器来自动管理，存取速度比栈慢一些
 *      反射：在运行状态中，对于任意一个类，都可以知道该类的所有属性和方法；
 *                       对于任意一个对象，都可以调用它的任意一个方法和属性。
 *           获取对象的方式：1、person.getClass();2、Class.forName("Person");3、Persion.class;(Person person)
 *
 *
 *
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
