package com.example.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAResource {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/posts")
    public List<Post> findAll(){
        return postRepository.findAll();
    }

//    @GetMapping("/jpa/posts/{id}")
//    public Post findById(@PathVariable int id){
//        Optional<Post>post =  postRepository.findById(id);
//        if(!post.isPresent()){
//            throw PostNotFindException();
//        }
//
//        return post.get();
//    }
}
