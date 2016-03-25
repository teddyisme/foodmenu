package com.lixinsheng.foodmenu.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lixinsheng on 16/3/21.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter {
    private OnRecyclerViewListener onRecyclerViewListener;
    public ArrayList<T> list;
    public Context mContext;
    public View itemView;

    public BaseRecycleAdapter(ArrayList<T> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insert(T data, int position) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public abstract class RecyclerViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public int position;

        public RecyclerViewHolderBase(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return null != onRecyclerViewListener && onRecyclerViewListener.onItemLongClick(position);
        }
    }

    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }


    public static class LineItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDrawable;

        public LineItemDecoration(Context context, int resId) {
//            mDrawable = context.getResources().getDrawable(resId);
            mDrawable = ContextCompat.getDrawable(context, resId);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                //以下计算主要用来确定绘制的位置
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);


//            outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
        }
    }
}
