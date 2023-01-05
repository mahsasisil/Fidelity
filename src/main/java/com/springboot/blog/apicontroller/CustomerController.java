package com.springboot.blog.apicontroller;

import com.springboot.blog.dto.CustomerDTO;
import com.springboot.blog.service.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomersService customersService;

    public CustomerController(CustomersService customersService) {

        this.customersService = customersService;
    }

    // create blog post rest api
    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<CustomerDTO> createPost(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customersService.createPost(customerDTO), HttpStatus.CREATED);
    }

    // get all customers
    //http://localhost:8080/api/posts/1
    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customersService.getAllPosts();
    }

    // get customer by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(customersService.getPostById(id));
    }

    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updatePost(@RequestBody CustomerDTO customerDTO, @PathVariable(name = "id") long id){

       CustomerDTO postResponse = customersService.updatePost(customerDTO, id);

       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        customersService.deletePostById(id);

        return new ResponseEntity<>("CustomerEntity entity deleted successfully.", HttpStatus.OK);
    }
}
