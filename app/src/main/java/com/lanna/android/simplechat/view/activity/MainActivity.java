package com.lanna.android.simplechat.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lanna.android.simplechat.R;
import com.lanna.android.simplechat.util.LogUtils;
import com.lanna.android.simplechat.view.service.ChatService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService();
    }

    public void startService() {
        LogUtils.v(this, "startService: ");
        startService(new Intent(getBaseContext(), ChatService.class));
    }
}
