package com.ashutosh.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResources {

    private UserDaoService service;

    public UserResources(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return  service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id){
        User user = service.findById(id);
        if(user == null){
            throw  new UserNotFoundException("id:"+ id);
        }
        return user;
    }

    //@RequestMapping (path = "/users", method = RequestMethod.POST)
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
}
