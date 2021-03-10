package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Yuhao Alex");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        Name name = new Name("Yuhao","Alex");
        return new PersonV2(name);
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personV1Param(){
        return new PersonV1("Yuhao Alex");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 personV2Param(){
        return new PersonV2(new Name("Yuhao", "Alex"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerVersion1(){
        return new PersonV1("Yuhao Alex");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerVersion2(){
        return new PersonV2(new Name("Yuhao", "Alex"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Yuhao Alex");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Yuhao", "Alex"));
    }
}
