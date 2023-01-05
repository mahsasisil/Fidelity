package com.springboot.blog.repository;

import com.springboot.blog.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomerEntity, Long> {

}
