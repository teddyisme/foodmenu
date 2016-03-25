package com.lixinsheng.foodmenu.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lixinsheng on 16/3/21.
 */
public class MyRecycleView extends RecyclerView {
    private boolean canLoadMore = false;


    public MyRecycleView(Context context) {
        super(context);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void setCanLoadMore() {
        addOnScrollListener(new OnScrollListener() {
            //用来标记是否正在向最后一个滑动，既是否向右滑动或向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    if (lastVisibleItem >= (totalItemCount - 5) && isSlidingToLast) {
                        onScrollBottomListener.onBottom();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;

            }
        });
    }


    public void setOnScrollBottomListener(onScrollBottomListener onScrollBottomListener) {
        this.onScrollBottomListener = onScrollBottomListener;
        this.canLoadMore = true;
        setCanLoadMore();
    }

    private onScrollBottomListener onScrollBottomListener;

    public interface onScrollBottomListener {
        void onBottom();
    }
}
