package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.service.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class PostController {

    private CustomersService customersService;

    public PostController(CustomersService customersService) {

        this.customersService = customersService;
    }

    // create blog post rest api
    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>(customersService.createPost(postDTO), HttpStatus.CREATED);
    }

    // get all customers
    //http://localhost:8080/api/posts/1
    @GetMapping
    public List<PostDTO> getAllPosts(){
        return customersService.getAllPosts();
    }

    // get customer by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(customersService.getPostById(id));
    }

    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable(name = "id") long id){

       PostDTO postResponse = customersService.updatePost(postDTO, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        customersService.deletePostById(id);

        return new ResponseEntity<>("CustomerEntity entity deleted successfully.", HttpStatus.OK);
    }
}
