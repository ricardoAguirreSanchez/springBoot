package com.example.cine.service.imple;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cine.dao.UserDAO;
import com.example.cine.entity.User;
import com.example.cine.service.UserService;


@Service
public class UserServiceImple implements UserService {

		
	@Autowired
	private UserDAO userDAO;
	


	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDAO.save(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		userDAO.deleteById(id);
	}

	@Override
	public List<User> findAllUsers() {
		userDAO.findAll();
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserExist(User user) {
		userDAO.existsById(user.getId());
		return false;
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
