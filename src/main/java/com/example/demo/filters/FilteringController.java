package com.example.demo.filters;



import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filterBean(){
        return new SomeBean("name1", "age1", "pass1");
    }

    @GetMapping("/dynamicfiltering")
    public MappingJacksonValue dynamicFilterBean(){

        //创建返回的 BEAN
        SomeBean someBean = new SomeBean("name1", "age1", "pass1");

        //创建 BeanPropertyFilter
        //只留下 age， pass
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("age","pass");

        //创建 Filter, 把Filter，命名为 SomeBeanFilter，马上要放在 entity上面
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);

        //创建JacksonMapping
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        //Filter
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filteringlist")
    public List<SomeBean> filterBeans(){
        return Arrays.asList( new SomeBean("name1","age1","pass1"),
                                new SomeBean("name2","age2","pass2"));

    }

    @GetMapping("/dynamicfilteringlist")
    public MappingJacksonValue dynamicFilterBeans(){

        //创建返回的 BEAN
        List<SomeBean> list = Arrays.asList( new SomeBean("name1","age1","pass1"),
                                                    new SomeBean("name2","age2","pass2"));

        //创建 BeanPropertyFilter
        //只留下 name, age
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("name","age");

        //创建 Filter, 把Filter，命名为 SomeBeanFilter，马上要放在 entity上面
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);

        //创建JacksonMapping
        MappingJacksonValue mapping = new MappingJacksonValue(list);

        //Filter
        mapping.setFilters(filters);

        return mapping;
    }
}
