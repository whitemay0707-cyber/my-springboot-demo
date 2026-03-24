package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// 这是操作数据库的简易工具
interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA 会自动根据方法名生成查询 SQL
    boolean existsByUsername(String username);
}

@RestController
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder encoder; // 注入刚才定义的粉碎机

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 1. 校验密码不能为空
        if (user.password == null || user.password.trim().isEmpty()) {
            return "注册失败：密码不能为空！";
        }

        // 2. 校验用户名格式（正则：只允许字母和数字）
        // ^[a-zA-Z0-9]+$ 表示从头到尾只能是英文字母或数字
        if (user.username == null || !user.username.matches("^[a-zA-Z0-9]+$")) {
            return "注册失败：用户名只能包含字母或数字！";
        }

        // 3. 校验用户名是否重复
        // 我们需要先去数据库查一下这个名字是否存在
        if (repo.existsByUsername(user.username)) {
            return "注册失败：用户名已存在！";
        }

        // 4. 加密并保存
        user.password = encoder.encode(user.password);
        repo.save(user);
        return "注册成功！";
    }
}
