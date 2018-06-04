package com.example.user_01.mywebsocket;

import android.app.Application;

import com.example.user_01.mywebsocket.web.WebSocketListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketApplication extends Application {

    private OkHttpClient      webSocketClient;
    private WebSocket         webSocket;
    private WebSocketListener webSocketListener;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create & open websocket.
        webSocketClient   = new OkHttpClient();
        Request request   = new Request.Builder().url("ws://echo.websocket.org").build();
        webSocketListener = new WebSocketListener(this);
        webSocket         = webSocketClient.newWebSocket(request, webSocketListener);
    }
}
