package com.springweb.app.restservice.Filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springweb.app.restservice.Filtering.Entity.SomeBean;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@Setter
@RestController
public class FilteringController {

    @GetMapping("filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value 1", "value 2", "value 3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("filtering-list")
    public MappingJacksonValue filteringList() {

        List<SomeBean> beans = Arrays.asList(
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 22", "value 33", "value 44")
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beans);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
