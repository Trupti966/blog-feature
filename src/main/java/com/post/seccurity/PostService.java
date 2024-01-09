package com.post.seccurity;

import com.post.entity.Post;
import com.post.payload.PostDto;

public interface PostService {

    public PostDto createPost(Post post);
    public void updatePost(Post post);
    public void getPostById(String postId);
    public void getAllPosts();
    public void deletePostById(String postId);

}
