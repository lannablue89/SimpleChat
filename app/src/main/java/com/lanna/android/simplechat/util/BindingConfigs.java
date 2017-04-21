package com.lanna.android.simplechat.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;

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
            ((BaseRecyclerAdapter) rv.getAdapter()).setItemsAndNotify(items);
        }

        if (selection >= 0) {
            if (rv.getLayoutManager() instanceof LinearLayoutManager) {
//                LogUtils.d("rv", "set selection: " + selection);// + " in " + "{" + firstVisiblePos + "," + lastVisiblePos + "}");
                rv.smoothScrollToPosition(selection);
            } else {
                rv.scrollToPosition(selection);
            }
        }
    }

//    @BindingAdapter("bind:onClick")
//    public static void bindOnClick(View view, final Runnable runnable) {
//        view.setOnClickListener(v -> runnable.run());
//    }

}
