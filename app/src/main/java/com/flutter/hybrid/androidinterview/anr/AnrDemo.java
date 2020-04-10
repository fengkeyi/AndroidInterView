package com.flutter.hybrid.androidinterview.anr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/***
 *  TODO
 *   ANR(Application not response)
 *   原因：主线程做了耗时操作，（BrocastReceiver onReceiver 10秒、activity，Service生命周期 5秒，）
 *   解决：
 *      1、使用AsyncTask/HandlerThread处理耗时操作（IO，文件读写，请求网络数据）
 *      2、activity/Service/BrocastReceiver不要做耗时操作
 *   OOM：android系统会为每个程序分配一个Dalvik虚拟机实例，当程序当前占用的内存加上新申请的内存资源超过
 *      Dalvik虚拟机的最大内存限制的时候就会抛出（Out of memory）异常（常见情况是ImageView加载大图Bitmap的时候）。
 *      内存溢出/内存抖动/内存泄漏
 *      有关Bitmap优化：
 *          图片压缩显示优化、滑动的时候不要加载大图；
 *          inBitmap属性，加载Bitmap包含两部分内存，一个是Java虚拟机，一部分是C区域
 *          使用LRU换成机制
 *      避免在OnDraw方法里执行对象创建
 *      Bitmap：
 *       1、Bitmap.recycle 收回的是native部分的内存，回收并不会立即执行，不可逆，只是标记为“dead"状态
 *       2、LRU（最近最少使用，优先清除出队列）
 *          2.1 LinkedHashMap，put，remove，trimToSize（清除队尾元素，也就是最近最少使用的对象）
 *       3 计算InSampleSize
 *       4 缩略图
 *       5 三级缓存（内存（LRU），磁盘（本地），网络）
 *   UI卡顿
 *      60fps（60帧/秒）-->16ms
 *      过多绘制（View过渡绘制，层级太深，绘制背景多层），大量的GC也会引起。
 *          1、UI线程中做了轻微的耗时操作，导致UI线程卡顿；
 *          2、布局Layout过于复杂，无法再16ms内完成渲染；
 *          3、同一时间动画的执行次数过多，导致CPU和GPU复杂过重；
 *          4、View的过渡绘制，导致某些像素在同一帧时间内被绘制多次，从而使CPU和GPU负载过重；
 *          5、View频繁出发Measure、Layout,导致Measure、layout累计耗时过多及整个View频繁的重新渲染；
 *          6、内存频繁出发GC过多，导致暂时阻塞渲染操作；
 *          7、冗余资源及逻辑等导致加载和执行缓慢；
 *     优化
 *      布局优化（include，merge，Viewstube）
 *      布局背景和图片等内存分配优化
 *    内存泄漏：某个不再使用的对象由于被其他实例引用
 *          检测工具：LeakCanary
 *          案例：单例（Activity.Context-->Application.Context）->长生命周期对象持有短生命周期对象的引用
 *                VEBView-->单独进程加载网页，system.exist(0)
 *    内存优化
 *      1、当Service完成任务后，尽量停止它。（使用IntentService代替）
 *      2、在UI不可见的时候，释放掉一些只有UI使用的资源
 *      3、在系统内存紧张的时候，尽可能多的释放掉一些非重要的资源（onTrimMemory）
 *      4、避免滥用Bitmap导致的内存浪费
 *      5、使用多进程
 *    冷启动：应用启动前，系统中没有该应用的任何进程信息（会走APplication oncreate）
 *    优化：减少Oncreate方法的工作量（懒加载，子线程加载）
 *          不要在APplication参与业务的操作
 *          不要在application中进行耗时操作
 *          不要以静态变量的方式在application中保存数据
 *          View的布局层次不宜过深
 *          Shareprefrence不能跨进程同步，不宜存储大文件数据
 *    热启动：按返回键退出，马上有重新启动应用
 *
 *
 */
public class AnrDemo {

    /**
     * TODO 计算 inSampleSize
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculteInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //raw width and height
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            if (width > height) {
                inSampleSize = Math.round((float) height / reqHeight);
            } else {
                inSampleSize = Math.round((float) width / reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * TODO 计算缩略图
     * @param path
     * @param maxWidth
     * @param maxHeight
     * @param autoRotate
     * @return
     */
    public static Bitmap thumbnail(String path, int maxWidth, int maxHeight, boolean autoRotate) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        ////只读取图片信息，不加载到内存中
        /**
         * If set to true, the decoder will return null (no bitmap), but
         * the <code>out...</code> fields will still be set, allowing the caller to
         * query the bitmap without having to allocate the memory for its pixels.
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int inSampleSize = calculteInSampleSize(options, maxWidth, maxHeight);
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }


}
