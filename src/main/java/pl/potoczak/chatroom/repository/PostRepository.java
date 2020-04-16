package pl.potoczak.chatroom.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.chatroom.entity.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface PostRepository extends CrudRepository<Post,Long> {
    @Modifying
    @Query(value= "SELECT * FROM (SELECT * FROM Post p ORDER BY p.date " +
            "DESC LIMIT ?) AS p_sub ORDER by p_sub.date ASC", nativeQuery = true)
    List<Post> findAllWithLimit(int limit);
}
