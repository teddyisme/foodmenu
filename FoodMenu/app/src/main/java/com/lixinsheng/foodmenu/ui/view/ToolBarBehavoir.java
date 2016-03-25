package com.lixinsheng.foodmenu.ui.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lixinsheng on 16/3/21.
 */
public class ToolBarBehavoir extends AppBarLayout.Behavior {

    public ToolBarBehavoir(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        alfarSet(child,dyConsumed,target);
    }

    private void alfarSet(View child, int dy, View target) {
        int top = target.getScrollY();
        int childH = child.getHeight();
        float rate = (float) top / childH;
        if (rate < 1) {
            child.setAlpha(1 - rate);
        } else {
            child.setAlpha(0);
        }

    }
}
