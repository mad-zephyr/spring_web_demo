package com.springweb.app.restservice.Filtering;

import com.springweb.app.restservice.Filtering.Entity.SomeBean;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@Setter
@RestController
public class FilteringController {

    @GetMapping("filtering")
    public SomeBean filtering() {
        return new SomeBean("value 1", "value 2", "value 3");
    }
    @GetMapping("filtering-list")
    public List<SomeBean> filteringList() {

        return Arrays.asList(
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 22", "value 33", "value 44")
        );
    }
}
