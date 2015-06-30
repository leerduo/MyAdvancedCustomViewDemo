package me.chenfuduo.myviewdraghelperusage.drag;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/6/29.
 */
public class DragLayout extends FrameLayout {


    private ViewDragHelper dragHelper;

    private ViewGroup blueView;
    private ViewGroup pinkView;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //创建ViewDragHelper的静态工厂方法有两个，一个带float sensitivity参数，一个不带，这个参数的默认值是1.0f
        //第一个参数是父view，也就是这里的DragLayout，给个this即可
        //第二个参数(若有),是灵敏度，默认值是1.0f
        //第三个参数是回调接口
        //ViewDragHelper.create(this,1.0f,mCallback);
        dragHelper = ViewDragHelper.create(this, mCallback);
    }

    /**
     * 该接口默认实现了public boolean tryCaptureView(View child, int pointerId)方法
     * 当然还需要实现很多其他的方法
     */
    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        /**
         * 根据返回结果决定当前child是否可以拖拽
         * @param child 当前被拖拽的view
         * @param pointerId 区分多点触摸的id
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.e("Test", "child view：" + child.toString());
            //return child == blueView;
            return true;
        }

        /**
         * 根据建议值修正将要移动到的位置(水平)
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            super.clampViewPositionHorizontal(child, left, dx);
            //向左  负的
            Log.e("Test","left:" + left + "  dx:" + dx);
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            super.clampViewPositionVertical(child, top, dy);
            //向上  负的
            Log.e("Test","top:" + top + "  dy:" + dy);
            return top;
        }
    };

    //下面重写的方法[onInterceptTouchEvent,onTouchEvent]可以理解为传递触摸事件(没有重写dispatchTouchEvent)

    /*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }*/

    /**
     * 将事件拦截下来,相当于把自定义控件的事件交给ViewDragHelper去处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //传递给ViewDragHelper
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * 将拦截下来的事件做处理
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //对多点触摸有点问题
        try {
            dragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回true，持续接收事件
        return true;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        blueView = (ViewGroup) getChildAt(0);
        pinkView = (ViewGroup) getChildAt(1);
    }
}
