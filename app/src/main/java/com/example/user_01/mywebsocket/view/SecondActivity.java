package com.example.user_01.mywebsocket.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.user_01.mywebsocket.R;
import com.example.user_01.mywebsocket.WebSocketApplication;
import com.example.user_01.mywebsocket.web.events.SecondEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by akari on 04.06.2018.
 */

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * Register MainActivity for EventBus events.
     *
     * Send two messages to websocket with delay.
     * This will cause an answer that will be handled in WebSocketListener.
     * Then WebSocketListener will generate an event that will be handled in
     * this Activity (only SecondEvent will be handled).
     */
    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        final WebSocketApplication application = (WebSocketApplication) getApplicationContext();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                application.sendWebSocketMessage("1");
            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                application.sendWebSocketMessage("2");
            }
        }, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * SecondActivity will handle only SecondEvent.
     */
    @Subscribe
    public void onWebSocketEvent(final SecondEvent event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SecondActivity.this, event.getText() + " handled.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
