package com.springboot.blog.service;

import com.springboot.blog.dto.CustomerDTO;

import java.util.List;

public interface CustomersService {

    CustomerDTO getPostByFirstName(String firstName);

    CustomerDTO createPost(CustomerDTO customerDTO);

    List<CustomerDTO> getAllPosts();

    CustomerDTO getPostById(long id);

    CustomerDTO updatePost(CustomerDTO customerDTO, long id);

    void deletePostById(long id);

}
