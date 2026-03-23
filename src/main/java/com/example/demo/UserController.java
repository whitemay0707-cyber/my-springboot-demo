package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// 这是操作数据库的简易工具
interface UserRepository extends JpaRepository<User, Long> {}

@RestController
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder encoder; // 注入刚才定义的粉碎机

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 关键步骤：把明文密码拿出来，加密，再塞回去
        String secretPassword = encoder.encode(user.password);
        user.password = secretPassword;

        repo.save(user);
        return "加密注册成功！";
    }
}
