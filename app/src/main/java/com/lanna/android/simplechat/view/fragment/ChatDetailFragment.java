package com.lanna.android.simplechat.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.databinding.FragmentChatDetailBinding;
import com.lanna.android.simplechat.model.ChatMessage;
import com.lanna.android.simplechat.view.adapter.BaseRecyclerAdapter;
import com.lanna.android.simplechat.view.adapter.ChatAdapter;
import com.lanna.android.simplechat.viewmodel.ChatViewModel;

/**
 * Created by lanna on 4/19/17.
 */

public class ChatDetailFragment extends Fragment implements BaseRecyclerAdapter.OnItemClickListener<ChatMessage> {

    private FragmentChatDetailBinding binding;
    private ChatViewModel viewModel;
    private ChatAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ChatAdapter(this);
        viewModel = new ChatViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatDetailBinding.inflate(inflater);
        setupRecyclerView(binding.recyclerView);
        
        binding.setViewModel(viewModel);

        viewModel.startChatFlow();

        return binding.getRoot();
    }

    public void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration((int) getResources().getDimension(R.dimen.line_normal)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(View view, int position, ChatMessage data) {

    }


    private class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}
