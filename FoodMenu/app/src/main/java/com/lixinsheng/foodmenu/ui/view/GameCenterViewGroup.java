package com.lixinsheng.foodmenu.ui.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.lixinsheng.foodmenu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixinsheng on 16/2/22.
 */
public class GameCenterViewGroup extends ViewGroup {

    private List<String> names = new ArrayList<>();

    private OnMenuItemClickListener onMenuItemClickListener;

    public interface OnMenuItemClickListener {
        void onClick(View view, int pos);
    }

    public GameCenterViewGroup(Context context) {
        super(context);
    }

    public GameCenterViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(MeasureSpec.UNSPECIFIED,
                    MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!names.isEmpty()) {
            int count = getChildCount();
            int groupWidth = getMeasuredWidth();
            int groupHeight = getMeasuredHeight();

            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();

                int cl = groupWidth / 2 - cWidth / 2;
                int ct = groupHeight / 2 - cHeight / 2;
                int cr = groupWidth / 2 + cWidth / 2;
                int cb = groupHeight / 2 + cWidth / 2;

                switch (i) {
                    case 0:
                        child.layout((int) (cl - (cWidth / 2 + cWidth * 0.5f * 0.3f)),
                                (int) (ct + (cHeight / 2 + cHeight * 0.5f * 0.3f)),
                                (int) (cr - (cWidth / 2 + cWidth * 0.5f * 0.3f)),
                                (int) (cb + (cHeight / 2 + cHeight * 0.5f * 0.3f)));

                        break;
                    case 1:
                        child.layout((int) (cl + cWidth / 2 + cWidth * 0.5f * 0.25f),
                                (int) (ct + cHeight / 2 + cHeight * 0.5f * 0.25f),
                                (int) (cr + cWidth / 2 + cWidth * 0.5f * 0.25f),
                                (int) (cb + cHeight / 2 + cHeight * 0.5f * 0.25f));

                        break;
                    case 2:
                        child.layout((int) (cl - (cWidth / 2 + cWidth * 0.5f * 0.4f)),
                                (int) (ct - (cHeight / 2 + cHeight * 0.5f * 0.4f)),
                                (int) (cr - (cWidth / 2 + cWidth * 0.5f * 0.4f)),
                                (int) (cb - (cHeight / 2 + cHeight * 0.5f * 0.4f)));

                        break;
                    case 3:
                        child.layout((int) (cl + (cWidth / 2 + cWidth * 0.5f * 0.25f)),
                                (int) (ct - (cHeight / 2 + cHeight * 0.5f * 0.25f)),
                                (int) (cr + (cWidth / 2 + cWidth * 0.5f * 0.25f)),
                                (int) (cb - (cHeight / 2 + cHeight * 0.5f * 0.25f)));

                        break;
                    case 4:
                        child.layout(cl, ct, cr, cb);
                        break;
                }

                setChildsText((TextView) child, i);

                if (i == 0) {
                    Animation transition_animation = new TranslateAnimation((cWidth / 2 + cWidth * 0.5f * 0.3f), 0,
                            -(cHeight / 2 + cHeight * 0.5f * 0.3f), 0);
                    loadAnimation(child, 0.9f, transition_animation);
                    ((GradientDrawable) child.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.blue2));
                } else if (i == 1) {
                    Animation transition_animation = new TranslateAnimation(cWidth / 2 + cWidth * 0.5f * 0.25f, 0,
                            cHeight / 2 + cHeight * 0.5f * 0.25f, 0);
                    loadAnimation(child, 0.8f, transition_animation);
                } else if (i == 2) {
                    Animation transition_animation = new TranslateAnimation(-(cWidth / 2 + cWidth * 0.5f * 0.4f), 0,
                            -(cHeight / 2 + cHeight * 0.5f * 0.4f), 0);
                    loadAnimation(child, 1.1f, transition_animation);
                    ((GradientDrawable) child.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.blue));
                } else if (i == 3) {
                    Animation transition_animation = new TranslateAnimation((cWidth / 2 + cWidth * 0.5f * 0.25f), 0,
                            -(cHeight / 2 + cHeight * 0.5f * 0.25f), 0);
                    loadAnimation(child, 0.9f, transition_animation);
                    ((GradientDrawable) child.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.green));
                } else if (i == 4) {
                    loadAnimation(child, 1f, null);
                    ((GradientDrawable) child.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.orang));
                }


                setItemOnClickListener(child, i);
            }
        }

    }


    private void setItemOnClickListener(final View child, final int i) {
        child.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuItemClickListener.onClick(child, i);
            }
        });

    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    private void loadAnimation(View child, Float scaletimes, Animation transition_animation) {
        AnimationSet animset = new AnimationSet(true);
        Animation animation = scaleBigAnim(500, scaletimes);
        animation.setStartOffset(100 / 2);
        animset.setInterpolator(new OvershootInterpolator(2F));
        child.setClickable(true);
        child.setFocusable(true);
        animset.addAnimation(animation);
        animset.setFillAfter(true);
        if (transition_animation != null) {
            transition_animation.setDuration(800);
            animset.addAnimation(transition_animation);
        }
        child.startAnimation(animset);
        ((GradientDrawable) child.getBackground()).setColor(ContextCompat.getColor(getContext(), R.color.red));

    }

    private void setChildsText(TextView child, int position) {
        Log.i("OIO",position+"");
        child.setText(names.get(position));
    }

    public void setTexts(List<String> names) {
        Log.i("OIO",names.toString());
        if (names != null && names.size() != 0 && names.size() == 5) {
            Log.i("OIO", "coming~" );
            this.names.clear();
            this.names.addAll(names);
//            invalidate();
            requestLayout();
        }
    }


    private Animation scaleBigAnim(int durationMillis, float scal) {
        Animation anim = new ScaleAnimation(
                0f, scal,
                0f, scal,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(durationMillis);
        anim.setFillAfter(true);
        return anim;
    }


}
