package com.lanna.android.simplechat.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;

/**
 * Created by lanna on 4/19/17.
 */

public class BindingConfigs {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "setAdapter"}, requireAll = false)
    public  static <T> void bindItemsToList(RecyclerView rv, ObservableList<T> items, RecyclerView.Adapter adapter) {
        if (adapter != null) {
            rv.setAdapter(adapter);
            ((BaseRecyclerAdapter) adapter).setItemsAndNotify(items);
        }
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

}
