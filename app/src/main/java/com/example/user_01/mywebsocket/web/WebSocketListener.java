package com.example.user_01.mywebsocket.web;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.user_01.mywebsocket.WebSocketApplication;
import com.example.user_01.mywebsocket.web.events.FirstEvent;
import com.example.user_01.mywebsocket.web.events.SecondEvent;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketListener extends okhttp3.WebSocketListener {

    private static final String TAG = "WebSocketListener";
    private WebSocketApplication application;

    public WebSocketListener(WebSocketApplication applicationContext) {
        super();
        this.application = applicationContext;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.d(TAG, "onOpen: ");
    }

    /**
     * Receives abstract event. Determines this event specifically.
     */
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.d(TAG, "onMessage: " + text);
        if (text.equals("1")) {
            EventBus.getDefault().post(new FirstEvent(text));
        } else {
            EventBus.getDefault().post(new SecondEvent(text));
        }
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
        if (application.isInForeground()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(application, "No internet connection. Trying to reconnect...", Toast.LENGTH_SHORT).show();
                    application.webSocketReconnect();
                }
            });
        }
    }
}
