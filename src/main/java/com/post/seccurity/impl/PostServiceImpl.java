package com.post.seccurity.impl;

import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.repository.PostRepository;
import com.post.seccurity.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public void updatePost(Post post) {

    }

    @Override
    public void getPostById(String postId) {

    }

    @Override
    public void getAllPosts() {

    }

    @Override
    public void deletePostById(String postId) {

    }


    private PostDto mapToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }

}
