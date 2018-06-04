package com.example.user_01.mywebsocket.web;

import android.util.Log;

import com.example.user_01.mywebsocket.WebSocketApplication;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketListener extends okhttp3.WebSocketListener {

    private static final String TAG = "WebSocketListener";
    private WebSocketApplication applicationContext;

    public WebSocketListener(WebSocketApplication applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.d(TAG, "onOpen: ");

        webSocket.send("1");
        webSocket.send("2");
        webSocket.send("3");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.d(TAG, "onMessage: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        Log.d(TAG, "onMessage: " + bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        Log.d(TAG, "onClosing: ");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.d(TAG, "onClosed: ");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.d(TAG, "onFailure: ");
    }
}