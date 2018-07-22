package com.example.cine.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cine.modelo.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
//	se puede hacer findBy cualquier atributo
	User findByUsername(String username);
	
}
