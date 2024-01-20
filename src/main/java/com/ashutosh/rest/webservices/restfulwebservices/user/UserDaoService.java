package com.ashutosh.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static{
        users.add(new User(++userCount, "vinod", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "abhay", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "abhishek", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "pranjal", LocalDate.now().minusYears(30)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        for(User user: users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
