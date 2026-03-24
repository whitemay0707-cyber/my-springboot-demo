package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true) // 告诉数据库：这一列的值必须唯一
    public Long id;

    public String username;

    // 必须要加这一行！
    public String password;
}
