package pl.potoczak.chatroom.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.chatroom.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
