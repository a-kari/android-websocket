package com.example.user_01.mywebsocket;

import android.app.Application;
import android.util.Log;

import com.example.user_01.mywebsocket.utils.BackgroundManager;
import com.example.user_01.mywebsocket.web.WebSocketHelper;

/**
 * Created by akari on 04.06.2018.
 */

public class WebSocketApplication extends Application {
    
    private static final String TAG = "WebSocketApplication";
    private WebSocketHelper webSocketHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        
        // Open websocket.
        webSocketHelper = new WebSocketHelper(this);
        webSocketHelper.open();
        
        // Listen to app go to foreground go to background events.
        BackgroundManager.get(this).registerListener(new BackgroundListener());
    }

    public void sendWebSocketMessage(String message) {
        if (webSocketHelper != null) {
            webSocketHelper.sendMessage(message);
        }
    }

    /**
     * Close websocket on app background. Open websocket on app foreground.
     * Why do we need this? Because server shouldn't keep connection with device
     * where the app went to background (it will be too expensive for server).
     */
    private class BackgroundListener implements BackgroundManager.Listener {
        @Override
        public void onBecameForeground() {
            if (webSocketHelper != null) {
                Log.d(TAG, "onBecameForeground: ");
                webSocketHelper.open();
            }
        }

        @Override
        public void onBecameBackground() {
            Log.d(TAG, "onBecameBackground: ");
            if (webSocketHelper != null) {
                webSocketHelper.close();
            }
        }
    }
}
