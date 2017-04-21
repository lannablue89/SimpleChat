package com.lanna.android.simplechat.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;

/**
 * Created by lanna on 4/19/17.
 *
 */

public class BindingConfigs {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"items", "setAdapter", "setSelection"}, requireAll = false)
    public static <T> void bindItemsToList(RecyclerView rv, ObservableList<T> items, RecyclerView.Adapter adapter, ObservableInt selection) {
        if (rv.getAdapter() == null) {
            LogUtils.i("rv", "setAdapter: ");
            rv.setAdapter(adapter);
        }
        if (rv.getAdapter() != null) {
            LogUtils.i("rv", "setItemsAndNotify: " + items.size());
            ((BaseRecyclerAdapter) rv.getAdapter()).setItemsAndNotify(items);
        }

        int sel = selection == null ? -1 : selection.get();
        if (sel >= 0) {
            if (rv.getLayoutManager() instanceof LinearLayoutManager) {
                    LogUtils.d("rv", "set selection: " + sel);// + " in " + "{" + firstVisiblePos + "," + lastVisiblePos + "}");
                    rv.smoothScrollToPosition(sel);
//                }
            } else {
                rv.scrollToPosition(sel);
            }
        }
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }

}
