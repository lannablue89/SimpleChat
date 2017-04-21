package com.lanna.android.simplechat.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lanna.android.simplechat.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanna on 6/29/16.
 *
 */

public class ListViewModel<T>  extends BaseObservable implements ViewModel {

    public final List<T> items = new ArrayList<>();

    @Bindable
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyPropertyChanged(BR.items);
    }

    @Override
    public void onDestroy() { }

}
