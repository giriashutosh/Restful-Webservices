package com.ashutosh.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static{
        users.add(new User(1, "vinod", LocalDate.now().minusYears(30)));
        users.add(new User(2, "abhay", LocalDate.now().minusYears(30)));
        users.add(new User(3, "abhishek", LocalDate.now().minusYears(30)));
        users.add(new User(4, "pranjal", LocalDate.now().minusYears(30)));
    }

}
