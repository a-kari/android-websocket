package com.example.user_01.mywebsocket.web.events;

/**
 * Created by akari on 04.06.2018.
 */

public class SecondEvent {
    private String text;

    public SecondEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
