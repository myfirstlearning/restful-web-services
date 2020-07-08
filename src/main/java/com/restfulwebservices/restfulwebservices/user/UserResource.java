package com.restfulwebservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;

    //GET /users
    //retrieve all Users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

     //GET /users/{id}
     @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
         User user = userService.findOne(id);

         if(user == null){
             throw new UserNotFoundException("id- " + id);
         }

         //"all-users", SERVER_PATH + "/users"
         //retrieveAllUsers


         //HATEOAS (HyperMedia As The Engine Of Application State)
         /*Resource<User> resource = new Resource<User>(user);
         ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
         resource.add(linkTo.withRel("all-users"));
         return resource;*/


         return user;
    }

    //input - detail of the user
    //output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User createdUser = userService.save(user);
        //CREATED
        // /users/{id}   user.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                   .path("{/id}")
                                   .buildAndExpand(createdUser.getId().toString())
                                   .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.deleteById(id);

        if(user == null){
            throw new UserNotFoundException("id- " + id);
        }

        //return ResponseEntity.noContent();

    }


}
