package pl.potoczak.chatroom.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.chatroom.entity.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Integer> {
    List<Post> findAllByOrderByDate();
    List<Post> findAllByOrderByDateAsc();

}
