package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

// 这是操作数据库的简易工具
interface UserRepository extends JpaRepository<User, Long> {}

@RestController
public class UserController {
    @Autowired
    UserRepository repo;

    @PostMapping("/register") // 定义接口地址
    public String register(@RequestBody User user) {
        repo.save(user); // 这一行就是把数据存进 MySQL
        return "保存成功！";
    }
}
