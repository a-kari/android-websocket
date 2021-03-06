package com.example.user_01.mywebsocket.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user_01.mywebsocket.R;
import com.example.user_01.mywebsocket.WebSocketApplication;
import com.example.user_01.mywebsocket.web.events.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Register MainActivity for EventBus events.
     *
     * Send two messages to websocket with delay.
     * This will cause an answer that will be handled in WebSocketListener.
     * Then WebSocketListener will generate an event that will be handled in
     * this Activity (only FirstEvent will be handled).
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

    /**
     * Unregister from events receiving. Because if not, it will cause memory leak.
     */
    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * MainActivity will handle only FirstEvent.
     */
    @Subscribe
    public void onWebSocketEvent(final FirstEvent event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, event.getText() + " handled.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
