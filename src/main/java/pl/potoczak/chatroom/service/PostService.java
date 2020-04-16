package pl.potoczak.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.potoczak.chatroom.entity.Post;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.PostRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {

    @Value("${limit.post}")
    private int limitPost;

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
     return postRepository.findAllWithLimit(limitPost);
    }

    public Post savePost(String message, User user) {
        Post post = new Post();
        post.setUser(user);
        post.setText(message);
        post.setDate(new Timestamp(System.currentTimeMillis()));
        return postRepository.save(post);
    }
}
