package pl.potoczak.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllByOrderByDate(){
     return postRepository.findAllByOrderByDate();
    }

    public List<Post> findAllByOrderByDateAsc(){
        return postRepository.findAllByOrderByDateAsc();
    }

    public Iterable<Post> findAll(){
        return postRepository.findAll();
    }

    public void savePost(String message, User user) {
        Post post = new Post();
        post.setUser(user);
        post.setText(message);
        post.setDate(new Timestamp(System.currentTimeMillis()));
        postRepository.save(post);
    }
}
