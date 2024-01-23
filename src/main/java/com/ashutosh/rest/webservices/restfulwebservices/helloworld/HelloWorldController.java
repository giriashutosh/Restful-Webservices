package com.ashutosh.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping(path="/hello-world")
    public String helloworld(){
        return "Hello world";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloworldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean("Hello world " + name);
    }

    @GetMapping(path="/hello-world-internationalized")
    public String helloworldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        //return "Hello world";
    }
}
