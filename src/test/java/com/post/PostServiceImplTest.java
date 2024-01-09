package com.post;
import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.repository.PostRepository;
import com.post.seccurity.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() {
        // create a post object for testing
        Post post = new Post();
        post.setId("1");
        post.setTitle("Test Title");
        post.setContent("Test Content");

        // mock the behaviour of PostRepository save() method
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // Create a PostDto object to return
        PostDto postDtoResult = new PostDto();
        postDtoResult.setTitle(post.getTitle());
        postDtoResult.setContent(post.getContent());

        // Mock the behaviour of ModelMapper map() method
        when(modelMapper.map(any(), any())).thenReturn(postDtoResult);

        // call the method being tested
        PostDto postDto = postService.createPost(post);

        // Verify that the save method of postRepository is called once with any Post object
        verify(postRepository, times(1)).save(any(Post.class));

        assertNotNull(postDto); // Ensure the PostDto object is not null
        assertNotNull(postDto.getTitle()); // Ensure the title in PostDto is not null
        assertEquals("Test Title", postDto.getTitle()); // Ensure it matches the expected title
        assertEquals("Test Content", postDto.getContent());
    }

}
