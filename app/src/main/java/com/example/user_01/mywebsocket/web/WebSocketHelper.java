package com.example.user_01.mywebsocket.web;

import com.example.user_01.mywebsocket.WebSocketApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketHelper {

    private WebSocketApplication application;
    private Request request;
    private OkHttpClient webSocketClient;
    private WebSocket webSocket;
    private WebSocketListener webSocketListener;

    public static final int NORMAL_CLOSURE_STATUS = 1000;

    /**
     * Create websocket.
     */
    public WebSocketHelper(WebSocketApplication application) {
        this.application       = application;
        this.webSocketClient   = new OkHttpClient();
        this.request           = new Request.Builder().url("ws://echo.websocket.org").build();
        this.webSocketListener = new WebSocketListener(application);
    }

    public void open() {
        webSocket = webSocketClient.newWebSocket(request, webSocketListener);
    }

    public void close() {
        if (webSocket != null) {
            webSocket.cancel();
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
    }

    public void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }
}
