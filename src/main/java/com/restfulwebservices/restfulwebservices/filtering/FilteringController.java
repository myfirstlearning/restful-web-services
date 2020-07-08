package com.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){

        SomeBean someBean =  new SomeBean("value1","value2","value3");
        return someBean;

    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue retrieveSomeBeanDynamicFiltering(){

        //Dynamic filtering -> field1, field2
        SomeBean someBean =  new SomeBean("value1","value2","value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }



    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans(){

        return Arrays.asList(new SomeBean("value1","value2","value3"),
                new SomeBean("value11","value22","value33"));

    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeansDynamicFiltering(){

        //Dynamic filtering -> field2, field3
        List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),
                new SomeBean("value11","value22","value33"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;

    }


}
