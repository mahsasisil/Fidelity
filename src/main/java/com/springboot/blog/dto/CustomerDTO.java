package com.springboot.blog.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String jobTitle;
}
