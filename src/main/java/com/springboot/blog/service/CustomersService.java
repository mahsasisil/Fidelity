package com.springboot.blog.service;

import com.springboot.blog.dto.PostDTO;

import java.util.List;

public interface CustomersService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePostById(long id);
}
