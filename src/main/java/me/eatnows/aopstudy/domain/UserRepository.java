package me.eatnows.aopstudy.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "apple", "1234", "010111111"));
        users.add(new User(2, "hi", "1234", "010111111"));
        users.add(new User(3, "hello", "1234", "010111111"));

        return users;
    }

    public User findById(int id) {
        return new User(id, "hello", "afasfasf", "3402305235s");
    }

    public void save(JoinReqDto dto) {
        System.out.println("DB INSERT");
    }

    public void delete(int id) {
        System.out.println("DB DELETE");
    }

    public void update(int id, UpdateReqDto dto) {
        System.out.println("DB UPDATE");
    }
}
