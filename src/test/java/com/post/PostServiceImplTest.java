package com.post;
import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.repository.PostRepository;
import com.post.services.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;


import static jakarta.persistence.GenerationType.UUID;
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

    @Test
    void getPostByIdTest() {
        // Given
        String postId = "any-random-uuid-string";
        Post mockPost = new Post();
        mockPost.setId(postId);
        mockPost.setTitle("Sample Title");

        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // When

        PostDto postDto = postService.getPostById(postId);


        // Then
        assertNotNull(postDto, "PostDto should not be null");
        assertEquals(mockPost.getTitle(), postDto.getTitle(), "PostDto title should match");
        verify(postRepository, times(1)).findById(postId);
    }

}
