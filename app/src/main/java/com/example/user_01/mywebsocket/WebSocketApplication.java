package com.example.user_01.mywebsocket;

import android.app.Application;

import com.example.user_01.mywebsocket.web.WebSocketHelper;
import com.example.user_01.mywebsocket.web.WebSocketListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketApplication extends Application {

    private WebSocketHelper webSocketHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        webSocketHelper = new WebSocketHelper(this);
        webSocketHelper.open();
    }

    public void sendWebSocketMessage(String message) {
        webSocketHelper.sendMessage(message);
    }
}
