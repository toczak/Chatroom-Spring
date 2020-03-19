package pl.potoczak.chatroom.entity;

public class ChatMessage {

    private String text;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ChatMessage() {
    }
}

