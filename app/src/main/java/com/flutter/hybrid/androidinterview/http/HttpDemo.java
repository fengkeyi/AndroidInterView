package com.flutter.hybrid.androidinterview.http;

/***
 * TODO
 *  HTTP
 *      URI:Uniform resource identify,统一资源标识符，用来唯一标识一个资源
 *       如：file：//192.168.1.100:123456/b/c/d.txt
 *          由访问资源命名机制（file）+存放资源的主机名（192.168.1.100:123456）+资源自身的名称（b/c/d.txt）
 *          三部分组成，着重强调与资源
 *      URL:uniform resource locator，统一资源定位器，它是一种具体的URI，即URL可以用来表示一个资源，而且还指明
 *          了如何locate这个资源
 *      如：http://www.baidu.com
 *    URL是一个具体的URI，URI强调的是资源，URL强调的是路径
 *    HTTP特点：简单快读，无连接，无状态、
 *          refere:http://www/baidu.com/ --》从哪个网页连接过来的
 *          if-none-match：006cad83rew-343 --》每次向服务器请求数据的是，服务器会返回一个Etag；当客户端再次向
 *             服务器请求数据的时候带上这个Etag，假如服务器返回304表示可以直接用本地缓存，不用再次从服务器获取
 *          Proxy-Connection keep-alive -->表示当客户端向服务器请求数据完成时候保持改连接，当再次向服务器请求
 *              数据的时候使用该链接即可，不用在创建新的连接。
*     Cookie和Session区别：
 *    1、Cookie和Session都是会话技术，Cookie是运行在客户端，Session是运行在服务器端。
 *    2、Cookie有大小限制以及浏览器在存cookie的个数也有限制，Session是没有大小限制和服务器的内存大小有关。
 *    3、Cookie有安全隐患，通过拦截或本地文件找得到你的cookie后可以进行攻击。
 *    4、Session是保存在服务器端上会存在一段时间才会消失，如果session过多会增加服务器的压力。
 *    HTTPS
 *      在HTTP和TCP之间加了SSL/TLS来为上层的安全保驾护航，主要用到对称加密，不对称加密，证书等技术
 *      进行客户端和服务器之间的数据加密传输，最终达到保证整个通信的安全性。
 *    TCP/IP网络模型
 *      物理层/实体层 010101电子信号
 *      链路层 广播
 *      网络层  主机到主机的通讯 能够区分哪些MAC地址属于同一个子网络，ARP协议：发出一个数据包，它所在的子网络的每一台主机都会收到这个数据包、都会收到这个数据包，读取数据包中的IP和自身IP对比，如果一样则返回自身MAC地址
 *      传输层  端口到端口的通讯
 *      应用层  规定应用程序的数据格式
 *    DNS  域名转换成IP地址（www.baidu.com-->10.20.35.221）
 *     本地hosts文件--》本地DNS解析器缓存--》本地DNS服务器---》。。。--》13台跟服务器
 *
 *
 *
 *
 */
public class HttpDemo {

}
