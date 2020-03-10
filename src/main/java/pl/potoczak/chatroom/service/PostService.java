package pl.potoczak.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.potoczak.chatroom.entity.Post;
import pl.potoczak.chatroom.repository.PostRepository;

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

}
