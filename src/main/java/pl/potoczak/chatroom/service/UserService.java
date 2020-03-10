package pl.potoczak.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public boolean checkPasswordMatching(User user) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
