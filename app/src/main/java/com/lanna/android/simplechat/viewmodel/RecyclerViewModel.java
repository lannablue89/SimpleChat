package com.lanna.android.simplechat.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by lanna on 6/29/16.
 *
 */

public class RecyclerViewModel<T, Adapter extends BaseRecyclerAdapter> implements ViewModel {

    public Adapter adapter;
    public final ObservableList<T> items = new ObservableArrayList<>();


    public RecyclerViewModel(Adapter adapter) {
        this.adapter = adapter;
    }


    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public void onDestroy() { }

}
