package com.ashutosh.rest.webservices.restfulwebservices.user;

import com.ashutosh.rest.webservices.restfulwebservices.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResources {

    private UserRepository userRepository;

    public UserJpaResources(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return  userRepository.findAll();
    }

    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw  new UserNotFoundException("id:"+ id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userRepository.deleteById(id);

    }

    //@RequestMapping (path = "/users", method = RequestMethod.POST)
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
}