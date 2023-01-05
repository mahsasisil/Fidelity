package com.springboot.blog.service.impl;

import com.springboot.blog.repository.CustomersRepository;
import com.springboot.blog.entity.CustomerEntity;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.dto.PostDTO;
import com.springboot.blog.service.CustomersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomersServiceImpl implements CustomersService {



    private CustomersRepository customersRepository;




    public CustomersServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        log("Create Customer");
        CustomerEntity newCustomerEntity = this.customersRepository.save(mapToEntity(postDTO));
        return mapToDTO(newCustomerEntity);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<CustomerEntity> postRepositories = customersRepository.findAll();
        log("getAllPost is calling");
        return postRepositories.stream().map(customerEntity -> mapToDTO(customerEntity)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        System.out.println("get Post by id is calling");
        CustomerEntity customerEntity = this.customersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", id));
        return mapToDTO(customerEntity);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        CustomerEntity customerEntity = this.customersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", id));

        customerEntity.setTitle(postDTO.getTitle());
        customerEntity.setDescription(postDTO.getDescription());
        customerEntity.setContent(postDTO.getContent());

        CustomerEntity updatedCustomerEntity = this.customersRepository.save(customerEntity);
        return mapToDTO(updatedCustomerEntity);
    }

    @Override
    public void deletePostById(long id) {
        // get customerEntity by id from the database
        CustomerEntity customerEntity = this.customersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", id));
        this.customersRepository.delete(customerEntity);
    }

    // convert Entity into DTO
    private PostDTO mapToDTO(CustomerEntity customerEntity){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(customerEntity.getId());
        postDTO.setTitle(customerEntity.getTitle());
        postDTO.setDescription(customerEntity.getDescription());
        postDTO.setContent(customerEntity.getContent());
        return postDTO;
    }

    // convert DTO to entity
    private CustomerEntity mapToEntity(PostDTO postDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setTitle(postDTO.getTitle());
        customerEntity.setDescription(postDTO.getDescription());
        customerEntity.setContent(postDTO.getContent());
        return customerEntity;
    }
    public static void log (String d){
        System.out.println(d);
    }
}
