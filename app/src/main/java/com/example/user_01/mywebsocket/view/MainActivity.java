package com.example.user_01.mywebsocket.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user_01.mywebsocket.R;
import com.example.user_01.mywebsocket.WebSocketApplication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Send message to websocket (this will cause answer).
        ((WebSocketApplication) getApplicationContext()).sendWebSocketMessage("Hello, world!");
    }
}
