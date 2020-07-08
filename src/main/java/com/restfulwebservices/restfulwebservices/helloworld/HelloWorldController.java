package com.restfulwebservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {


    @Autowired
    private MessageSource messageSource;

    //GET
    //URI - /hello-world
    //method - "Hello World"
    @RequestMapping(method = RequestMethod.GET, value = "/hello-world")
    //@GetMapping(value = "/helloworld")
    public String helloWorld() {
        return ">>>>> Hello World !!!!";
    }

    //hello-world-bean

    @RequestMapping(method = RequestMethod.GET, value = "/hello-world-bean",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean(">>>> Hello World Bean");
    }


    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello-world-internationalization")
    public String helloWorldInternationalization(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello-world-internationalization-localeContextHolder")
    public String helloWorldInternationalizationUsingLocaleContextHolder() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }



}
