package com.example.demo.user;

import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retriveAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id: "+ id + " Not Found!");
//            throw new handleAllExceptions()
        }

        //创建 Resource 对象，用于返回
        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());

        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        User addedUser = userRepository.save(user);
        //check if input validate
        if(user.getName()==null){
            throw new BadUserInputException("User name needed");
        }
        //build the response and return
        //first, build the created entity uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    //find all posts from one user
    @GetMapping("jpa/users/{id}/posts")
    public List<Post> findAllPostByUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();     //这里由 UserRepository and PostRepository 共同配置好的 getPosts()
    }

    //create a post for a user
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);
        //check if input validate, if there is such a user
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id: "+ id);
        }

        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        //build the response and return
        //first, build the created entity uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
