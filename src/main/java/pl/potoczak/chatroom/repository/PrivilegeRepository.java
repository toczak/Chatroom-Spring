package pl.potoczak.chatroom.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.chatroom.entity.security.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege,Long> {
    Privilege findByName(String name);
}
