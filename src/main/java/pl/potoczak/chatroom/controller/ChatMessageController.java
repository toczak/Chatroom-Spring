package pl.potoczak.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pl.potoczak.chatroom.entity.ChatMessage;
import pl.potoczak.chatroom.entity.Post;
import pl.potoczak.chatroom.service.PostService;
import pl.potoczak.chatroom.service.UserService;

import java.security.Principal;

@Controller
public class ChatMessageController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage get(Principal principal, ChatMessage chatMessage) {
        if (!chatMessage.getText().trim().isEmpty()) {
            Post post = postService.savePost(chatMessage.getText(), userService.findUserByUsername(principal.getName()));
            return new ChatMessage(post.getUser().getUsername(),post.getText(),post.getDate());
        }
        return null;
    }
}
