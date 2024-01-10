package com.post.services;

import com.post.entity.Post;
import com.post.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(Post post);
    public PostDto updatePost(Post post);
    public PostDto getPostById(String postId);
    public List<PostDto> getAllPosts();
    public String deletePostById(String postId);

}
