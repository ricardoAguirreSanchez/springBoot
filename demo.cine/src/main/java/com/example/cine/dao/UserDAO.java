package com.example.cine.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.cine.entity.User;


public interface UserDAO extends CrudRepository<User, Long>{


}
