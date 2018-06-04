package com.example.user_01.mywebsocket.web;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.user_01.mywebsocket.WebSocketApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketHelper {
    private static final String TAG = "WebSocketHelper";

    private WebSocketApplication application;
    private Request request;
    private OkHttpClient webSocketClient;
    private WebSocket webSocket;
    private WebSocketListener webSocketListener;
    private long time;

    public static final int NORMAL_CLOSURE_STATUS = 1000;

    /**
     * Create websocket.
     */
    public WebSocketHelper(WebSocketApplication application) {
        this.application       = application;
        this.webSocketClient   = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
        this.request           = new Request.Builder().url("ws://echo.websocket.org").build();
        this.webSocketListener = new WebSocketListener(application);
        this.time              = 0;
    }

    public void open() {
        webSocket = webSocketClient.newWebSocket(request, webSocketListener);
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
    }

    public void reconnect() {
        Log.d(TAG, "reconnect: " + (long) Math.pow(2, time));
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                open();
            }
        }, (long) Math.pow(2, time++) * 1000);
    }

    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }
}
