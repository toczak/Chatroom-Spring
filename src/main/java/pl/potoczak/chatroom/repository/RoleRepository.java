package pl.potoczak.chatroom.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.chatroom.entity.security.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String role_admin);
}
