package me.eatnows.aopstudy.web;

import lombok.RequiredArgsConstructor;
import me.eatnows.aopstudy.domain.User;
import me.eatnows.aopstudy.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // http://localhost:8080/user
    @GetMapping("/user")
    public List<User> findAll() {
        System.out.println("findAll()");
        return userRepository.findAll(); // MessageConverter (JavaObject -> JSON String)
    }

    // http://localhost:8080/user/1
    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id) {
        System.out.println("findById() id : " + id);
        return userRepository.findById(id);
    }

    // http://localhost:8080/user/1
    @PostMapping("/user")
    // x-www-form-urlencoded => request.getParameter()
//    public void save(String username, String password, String phone) {
//        System.out.println("save()");
//        System.out.println("username = " + username + ", password = " + password + ", phone = " + phone);
//
//    }

    // text/plain => @RequestBody 애너테이션으로 읽을 수 있음
//    public void save(@RequestBody String data) {
//        System.out.println("save()");
//        System.out.println("data : " + data);
//    }
    // application/json => @RequestBody 애너테이션 + 오브젝트
    public String save(@RequestBody User user) {
        System.out.println("save()");
        System.out.println("user : " + user);

        userRepository.save(user);

        return "ok";
    }



    // http://localhost:8080/user/1
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        System.out.println("Delete()");

    }

    // http://localhost:8080/user/1
    @PutMapping("/user/{id}")
    public void update(@PathVariable int id, String password, String phone) {
        System.out.println("update()");

    }
}
