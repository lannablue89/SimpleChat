package com.lanna.android.simplechat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanna on 6/1/16.
 *
 */

public abstract class BaseRecyclerAdapter<Item, ViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<ViewHolder> {

    public interface OnItemClickListener<ItemModel> {
        void onItemClicked(View view, int position, ItemModel data);
    }
    protected OnItemClickListener<Item> onItemClickListener;

    protected List<Item> mItems = new ArrayList<>();


    public BaseRecyclerAdapter() {}

    public BaseRecyclerAdapter(OnItemClickListener<Item> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<Item> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> mItemModels) {
        this.mItems = mItemModels;
    }

    public void setItemsAndNotify(List<Item> items) {
        setItems(items);
        notifyDataSetChanged();
    }

    public void setItemsAndNotifyEach(List<Item> items) {
        if (items == null || items.isEmpty()) {
            if (!mItems.isEmpty()) {
                mItems.clear();
                notifyDataSetChanged();
//                LogUtils.d("improve-list-notify", this, "setItemsAndNotify: notify all clear");
            }
            return;
        }

        if (mItems.isEmpty()) {
            mItems.addAll(items);
            notifyDataSetChanged();
//            LogUtils.d("improve-list-notify", this, "setItemsAndNotify: notify all from empty");
        }

        Item item;
        int inputCount = items.size();
        int maxCount = Math.max(mItems.size(), inputCount);
        for (int i = 0; i < maxCount; i++) {
            if (i < inputCount) {
                item = items.get(i);
                if (i >= mItems.size()) {
                    mItems.add(item);
                    notifyItemInserted(i);
                    notifyItemChanged(i-1);
//                    LogUtils.d("improve-list-notify", this, "setItemsAndNotify: insert at " + i);
                }
                else if (!item.equals(mItems.get(i))) {
//                    LogUtils.d("improve-list-notify", "setItemsAndNotify: update at " + i + "\nfrom: " + mItems.get(i) + "\nto: " + item);
                    mItems.remove(i);
                    mItems.add(i, item);
                    notifyItemChanged(i);
                    notifyItemChanged(i-1);
                }
            }
            else if (i < mItems.size()) {
                mItems.remove(i);
                notifyItemRemoved(i);
                notifyItemChanged(i-1);
//                LogUtils.d("improve-list-notify", this, "setItemsAndNotify: remove at " + i);
            }
        }
    }

//    protected abstract DiffUtil.Callback newDiffCallback(List<Item> oldList, List<Item> newList);

    @Override
    public int getItemCount() {
        return (mItems == null) ? 0 : mItems.size();
    }

    public Item getItem(int position) {
        return (mItems == null || position < 0 || position >= mItems.size()) ? null : mItems.get(position);
    }
}
