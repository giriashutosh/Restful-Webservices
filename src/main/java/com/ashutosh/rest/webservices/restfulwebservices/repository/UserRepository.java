package com.ashutosh.rest.webservices.restfulwebservices.repository;

import com.ashutosh.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
