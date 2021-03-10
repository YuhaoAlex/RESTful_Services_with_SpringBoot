package com.example.demo.filters;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"age","pass"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String name;

    private String age;

//    @JsonIgnore
    private String pass;

    public SomeBean(String name, String age, String pass) {
        this.name = name;
        this.age = age;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPass() {
        return pass;
    }
}
