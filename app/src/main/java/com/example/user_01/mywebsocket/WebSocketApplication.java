package com.example.user_01.mywebsocket;

import android.app.Application;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketApplication extends Application {
    private static final String TAG = "WebSocketApplication";

    private OkHttpClient      webSocketClient;
    private WebSocket         webSocket;
    private WebSocketListener webSocketListener;

    @Override
    public void onCreate() {
        super.onCreate();

        webSocketClient   = new OkHttpClient();
        Request request   = new Request.Builder().url("ws://echo.websocket.org").build();
        webSocketListener = new MyWebSocketListener();
        webSocket         = webSocketClient.newWebSocket(request, webSocketListener);

        // Now websocket is opened (created socket is opened by default).
    }

    // Probably have not to be static, because Application context will be alive anyway.
    private final class MyWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.d(TAG, "onOpen: ");
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
}
