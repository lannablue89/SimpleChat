package com.lanna.android.simplechat.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

/**
 * Created by lanna on 6/29/16.
 *
 */

public class ListViewModel<T> implements ViewModel {

    public final ObservableList<T> items = new ObservableArrayList<>();

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public void onDestroy() { }

}
