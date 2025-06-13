package com.codegym.ung_dung_blog.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Blog> blogs;
}
