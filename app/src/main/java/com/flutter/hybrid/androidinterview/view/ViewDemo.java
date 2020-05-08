package com.flutter.hybrid.androidinterview.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/***
 * TODO
 *  View 的绘制
 *  Activity
 *      PhoneWindow（Window的唯一子类）
 *          DecorView
 *              TitleView：ActionBarContainer，ActionBar
 *              ContentView：FrameLayout->RelativeLayout(setContentView)
 *  步骤：
 *  1、measure：判断是否需要重新计算View的大小（确定宽和高，测量自上而下，起始点ViewRootImpl，也就是DecorView，
 *              View的子类通过重新onMeasure方法测量）
 *  2、layout：判读是否需要重新计算View的位置(确定view在父容器中的位置)
 *  3、draw：判断是否需要重新绘制View
 *      draw 步骤：
 *       *1. Draw the background （drawBackground(canvas);）
 *       2. If necessary, save the canvas' layers to prepare for fading
 *       *3. Draw view's content（onDraw(canvas);绘制View本身）
 *       *4. Draw children（dispatchDraw(canvas);绘制子View）
 *       5. If necessary, draw the fading edges and restore layers
 *       6. Draw decorations (scrollbars for instance)（onDrawForeground(canvas);）
 *
 */
public class ViewDemo {
    //decorview window 测量过来 mesureSpec
    private View view;
    private ViewGroup viewGroup;
    //缓存机制
    /**
     * TODO
     *  item元素避免半透明（涉及大量乘法运算操作）
     *  开启硬件加速
     *  回收机制：
     *  recyclerview-》LayoutManager.recycleChildren 回收
     *  复用机制：
     *  Recycler#getViewForPosition
     *
     *  link:https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650830500&idx=1&sn=7503d4f3821435a72233bc2fab7fcdc8&chksm=80b7a23ab7c02b2c3b0030dcd8678d17a8908d03d5d2c4489314d6b2cb2f280e0359ea5f37ad&mpshare=1&scene=1&srcid=0429NERyieM9SDUQxkIJ7nE2&sharer_sharetime=1588139003849&sharer_shareid=a2256cd305676666fdd17905ad0d52b9&key=498a3c4fcbb47b96653a1e5a5314552ab390abd2eb20d2fbead37dca2587c7fd77ef98d155a9390a547d091c19db61c6ea60504ffa1f9b0d39374e974c5039e048a57820dfe047ccbfd7994c6696c1cf&ascene=1&uin=Mjg0MzYyOTU4Mw%3D%3D&devicetype=Windows+7&version=62070158&lang=zh_CN&exportkey=Aep%2BMMjy1c2mI%2BFL2GuwOzo%3D&pass_ticket=AN3S87Tn3uPcMm8hvg4N0Yl5Cdexl2a4dJN%2FmlGgR5VUTd8Uktjb%2B%2FrT%2Bae73CAk
     *  TODO
     *   2.Bitmap
     *      Bitmap的内存计算方式
     *      在已知图片的长和宽的像素的情况下，影响内存大小的因素会有资源文件位置和像素点大小
     *          像素点大小
     *          常见的像素点有：ARGB_8888：4个字节;ARGB_4444、ARGB_565：2个字节
     *          不同dpi对应存放的文件夹
     *          ldpi    mdpi    hdpi    xhdpi   xxhdpi  xxxhdpi
     *          120     160     160-240 240-320 320-480 480-640
     *          比如一个一张图片的像素为180*180px，dpi(设备独立像素密度)为320，如果它仅仅存放在drawable-hdpi，则有
     *              横向像素点 = 180 * 320/240 + 0.5f = 240 px
     *              纵向像素点 = 180 * 320/240 + 0.5f = 240 px
     *          如果它仅仅存放在drawable-xxhdpi，则有：
     *              横向像素点 = 180 * 320/480 + 0.5f = 120 px
     *              纵向像素点 = 180 * 320/480 + 0.5f = 120 px
     *          所以，对于一张180*180px的图片，设备dpi为320，资源图片仅仅存在drawable-hdpi，像素点大小为ARGB_4444，最后生成的文件内存大小为：
     *              内存大小 = 240 * 240 * 2 = 115200byte 约等于 112.5kb
     *      # Bitmap的高效加载
     *          1/获取需要的长和宽，一般获取控件的长和宽。
     *          2/设置BitmapFactory.Options中的inJustDecodeBounds为true，可以帮助我们在不加载进内存的方式获得Bitmap的长和宽。
     *          3/对需要的长和宽和Bitmap的长和宽进行对比，从而获得压缩比例，放入BitmapFactory.Options中的inSampleSize属性。
     *          4/设置BitmapFactory.Options中的inJustDecodeBounds为false，将图片加载进内存，进而设置到控件中。
     *
     */
    private RecyclerView recyclerView;



}
