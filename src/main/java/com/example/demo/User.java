package com.example.demo;

import jakarta.persistence.*;

@Entity // 告诉电脑：这是一个数据库表
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // 自动生成的编号
    public String username; // 用户名
}
