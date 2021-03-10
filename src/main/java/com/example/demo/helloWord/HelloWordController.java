package com.example.demo.helloWord;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWordController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/helloword")
    public String helloword(){
        return "hello world from Yuhao";
    }

    @GetMapping(path = "/hello_world_bean")
    public HelloWorldBean helloWordBean(){
        return new HelloWorldBean("hello world of Bean From Yuhao");
    }

    @GetMapping(path =  "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello, %s", name));
    }

    @GetMapping(path = "/hello_world_internationalized")
    public String helloWordInternationalized(@RequestHeader(name="Accept-language", required = false)Locale locale) {
            //return "helloWorld";
        return messageSource.getMessage("good.morning.message", null, locale);
    }
}
