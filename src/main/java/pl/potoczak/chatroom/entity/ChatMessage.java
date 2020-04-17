package pl.potoczak.chatroom.entity;

import java.sql.Timestamp;

public class ChatMessage {

    private String username;
    private String text;
    private Timestamp date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ChatMessage() {
    }

    public ChatMessage(String user, String text, Timestamp date) {
        this.text = text;
        this.username = user;
        this.date = date;
    }
}

