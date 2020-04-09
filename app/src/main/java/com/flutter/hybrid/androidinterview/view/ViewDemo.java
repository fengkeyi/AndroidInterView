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
 *  1、measure：判断是否需要重新计算View的大小（确定宽和高，测量自上而下，起始点ViewRootImpl，也就是DecorView，View的子类通过重新onMeasure方法测量）
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
    private RecyclerView recyclerView;



}
