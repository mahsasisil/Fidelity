package com.springboot.blog.service.impl;

import com.springboot.blog.repository.CustomersRepository;
import com.springboot.blog.entity.CustomerEntity;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.dto.CustomerDTO;
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
    public CustomerDTO createPost(CustomerDTO customerDTO) {

        log("Create Customer");
        CustomerEntity newCustomerEntity = this.customersRepository.save(mapToEntity(customerDTO));
        return mapToDTO(newCustomerEntity);
    }

    @Override
    public List<CustomerDTO> getAllPosts() {
        List<CustomerEntity> postRepositories = customersRepository.findAll();
        log("getAllPost is calling");
        return postRepositories.stream().map(customerEntity -> mapToDTO(customerEntity)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getPostById(long id) {
        System.out.println("get Post by id is calling");
        CustomerEntity customerEntity = this.customersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", id));
        return mapToDTO(customerEntity);
    }


    @Override
    public CustomerDTO getPostByFirstName(String firstName) {
        System.out.println("get Post by id is calling");
        CustomerEntity customerEntity = this.customersRepository.findByFirstName(firstName);
        return mapToDTO(customerEntity);
    }



    @Override
    public CustomerDTO updatePost(CustomerDTO customerDTO, long id) {
        CustomerEntity customerEntity = this.customersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "id", id));

        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setJobTitle(customerDTO.getJobTitle());

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
    private CustomerDTO mapToDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setLastName(customerEntity.getLastName());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setJobTitle(customerEntity.getJobTitle());
        return customerDTO;
    }

    // convert DTO to entity
    private CustomerEntity mapToEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setJobTitle(customerDTO.getJobTitle());
        return customerEntity;
    }
    public static void log (String d){
        System.out.println(d);
    }
}
