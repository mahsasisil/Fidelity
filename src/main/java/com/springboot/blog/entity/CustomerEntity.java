package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name","last_name"})}
)
public class CustomerEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String title;

    @Column(name = "last_Name", nullable = false)
    private String description;

    @Column(name = "job_title", nullable = true)
    private String content;
}
