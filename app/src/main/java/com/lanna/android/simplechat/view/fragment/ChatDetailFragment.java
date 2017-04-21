package com.lanna.android.simplechat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ChatViewModel(new ChatAdapter(this));
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onItemClicked(View view, int position, ChatMessage data) {

    }
}
