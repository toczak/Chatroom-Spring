package pl.potoczak.chatroom.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.potoczak.chatroom.entity.Post;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.PostRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void shouldReturnPostAfterSave() {
        //given
        given(postRepository.save(any(Post.class))).willReturn(new Post("Message test"));
        //when
        Post post = postService.savePost("Message test",new User());
        //then
        assertEquals("Message test", post.getText());
    }
}