package pl.potoczak.chatroom.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.UserRepository;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test should return user by username")
    void shouldReturnUserByUsername() {
        //given
        User user = new User("test");
        given(userRepository.findUserByUsername("test")).willReturn(user);
        //when
        User userFinded = userService.findUserByUsername("test");
        //then
        assertEquals("test", userFinded.getUsername());
    }

    @Test
    void shouldReturnUserAfterSave() {
        //given
        given(userRepository.save(new User())).willReturn(new User());
        //when
        User user = userService.saveUser(new User());
        //then
        assertTrue(user instanceof User);
    }
}