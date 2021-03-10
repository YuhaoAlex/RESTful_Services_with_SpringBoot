package com.example.demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> retriveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
        User user = userDaoService.find(id);
        if(user==null){
            throw new UserNotFoundException("id: "+ id + " Not Found!");
//            throw new handleAllExceptions()
        }

        //创建 Resource 对象，用于返回
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());

        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDaoService.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("id: "+ id + " Not Found!");
//            throw new handleAllExceptions()
        }
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User addedUser = userDaoService.save(user);
        //check if input validate
        if(user.getName()==null){
            throw new BadUserInputException("User name needed");
        }
        //build the response and return
        //first, build the created entity uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
