package com.douglas.workshopmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.workshopmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        User person = new User("1", "Douglas", "LrB2b@example.com");
        User person2 = new User("2", "Alex", "AbsB2b@example.com");

        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(person, person2));

        return ResponseEntity.ok().body(list);
    }
}
