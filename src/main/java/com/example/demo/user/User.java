package com.example.demo.user;


import com.example.demo.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "Details about user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;


    @Size(min=2, message = "Name should be more than 2")
    private String name;

    @Past
    @ApiModelProperty(notes = "Create Time should be in the past")
    private Date createTime;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    protected User(){

    }

    public User(int id, String name, Date createTime) {
        super();
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
