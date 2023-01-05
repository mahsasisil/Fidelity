package com.springboot.blog.repository;

import com.springboot.blog.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomersRepository extends JpaRepository<CustomerEntity, Long> {
    public CustomerEntity findByFirstName(String firstName);

}
