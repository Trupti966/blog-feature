package com.post.services.impl;

import com.post.entity.Post;
import com.post.exception.ResourceNotFoundException;
import com.post.payload.PostDto;
import com.post.repository.PostRepository;
import com.post.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PostDto createPost(Post post) {

        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        PostDto postDto = mapToDto(savedPost);
        return postDto;
    }

    @Override
    public PostDto updatePost(Post post) {
        return null;
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with postId" + postId)
        );

        PostDto postDto = mapToDto(post);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public String deletePostById(String postId) {
        return null;
    }

    private PostDto mapToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }

}