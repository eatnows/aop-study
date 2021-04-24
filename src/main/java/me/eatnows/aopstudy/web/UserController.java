package me.eatnows.aopstudy.web;

import lombok.RequiredArgsConstructor;
import me.eatnows.aopstudy.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // http://localhost:8080/user
    @GetMapping("/user")
    public ApiResponseDto<List<User>> findAll() {
        System.out.println("findAll()");
        return new ApiResponseDto<>(HttpStatus.OK.value(), userRepository.findAll()); // MessageConverter (JavaObject -> JSON String)
    }

    // http://localhost:8080/user/1
    @GetMapping("/user/{id}")
    public ApiResponseDto<User> findById(@PathVariable int id) {
        System.out.println("findById() id : " + id);
        return new ApiResponseDto<>(HttpStatus.OK.value(), userRepository.findById(id));
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
    public ApiResponseDto<String> save(@RequestBody JoinReqDto joinReqDto) {
        System.out.println("save()");
        System.out.println("joinReqDto = " + joinReqDto);

        userRepository.save(joinReqDto);

        return new ApiResponseDto<>(HttpStatus.OK.value(), "ok");
    }



    // http://localhost:8080/user/1
    @DeleteMapping("/user/{id}")
    public ApiResponseDto delete(@PathVariable int id) {
        System.out.println("Delete()");
        userRepository.delete(id);

        return new ApiResponseDto<>(HttpStatus.OK.value());
    }

    // http://localhost:8080/user/1
    @PutMapping("/user/{id}")
    public ApiResponseDto update(@PathVariable int id, @RequestBody UpdateReqDto dto) {
        System.out.println("update()");
        userRepository.update(id, dto);

        return new ApiResponseDto<>(HttpStatus.OK.value());
    }
}
