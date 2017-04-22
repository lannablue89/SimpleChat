package com.lanna.android.simplechat.util;

import android.databinding.BindingAdapter;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;
import com.lanna.android.simplechat.view.custom_view.CustomAvatar;

import java.util.List;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class BindingConfigs {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "setSelection"}, requireAll = false)
    public static void bindItemsToList(RecyclerView rv, List items, int selection) {
        if (rv.getAdapter() != null) {
//            LogUtils.i("rv", "setItemsAndNotify: " + items.size());
            ((BaseRecyclerAdapter) rv.getAdapter()).setItemsAndNotifyEach(items);
        }

        if (selection >= 0) {
            if (rv.getLayoutManager() instanceof LinearLayoutManager) {
                int firstVisiblePos = ((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();
                int lastVisiblePos = ((LinearLayoutManager)rv.getLayoutManager()).findLastVisibleItemPosition();
                LogUtils.d("rv", "set selection: " + selection + " in " + "{" + firstVisiblePos + "," + lastVisiblePos + "}");
                if (lastVisiblePos >= selection - 2) {
                    rv.smoothScrollToPosition(selection);
                }
            } else {
                rv.smoothScrollToPosition(selection);
            }
        }
    }

//    @BindingAdapter("bind:onClick")
//    public static void bindOnClick(View view, final Runnable runnable) {
//        view.setOnClickListener(v -> runnable.run());
//    }


//    @BindingConversion
//    public static int convertBooleanToVisibility(boolean visible) {
//        return visible ? View.VISIBLE : View.GONE;
//    }


    @BindingAdapter({"android:background"})
    public static void characterBackground(View view, @DrawableRes int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter({"bind:selected"})
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    @BindingAdapter({"bind:paddingLeft"})
    public static void setPaddingLeft(View view, @DimenRes int paddingLeft) {
        view.setPadding(
                (int) view.getResources().getDimension(paddingLeft), view.getPaddingTop(),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"bind:paddingTop"})
    public static void setPaddingTop(View view, @DimenRes int paddingTop) {
        view.setPadding(
                view.getPaddingLeft(), (int) view.getResources().getDimension(paddingTop),
                view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"bind:paddingRight"})
    public static void setPaddingRight(View view, @DimenRes int paddingRight) {
        view.setPadding(
                view.getPaddingLeft(), view.getPaddingTop(),
                (int) view.getResources().getDimension(paddingRight), view.getPaddingBottom());
    }

    @BindingAdapter({"bind:paddingBottom"})
    public static void setPaddingBottom(View view, @DimenRes int paddingBottom) {
        view.setPadding(
                view.getPaddingLeft(), view.getPaddingTop(),
                view.getPaddingRight(), (int) view.getResources().getDimension(paddingBottom));
    }

    @BindingAdapter({"bind:color"})
    public static void setColor(CustomAvatar vAvatar, int color) {
        vAvatar.setColor(color);
    }
}
