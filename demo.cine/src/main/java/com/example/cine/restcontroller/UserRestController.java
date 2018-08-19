package com.example.cine.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cine.entity.User;
import com.example.cine.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService; // Service which will do all data retrieval/manipulation work

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		//spring hace que lo que se devuelve es un JSON
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	//crear un usuario
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User userNew) {
		
		System.out.println("Tratando de crear al usuario: " + userNew.getUsername());
		
		if (userService.isUserExist(userNew)) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);// mando conflicto pork ya existe el user
		}
		
		userService.saveUser(userNew);
		System.out.println("Se creo al usuario");
		
		//aca estamos devolviendo un string, ejemplo de json esta en getUser 
		return new ResponseEntity<String>("Usuario agregado!!", HttpStatus.OK);
	}
	
	//actualiza un usuario
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody User userUpdate) {
		
		System.out.println("Tratando de actualizar al usuario: " + userUpdate.getUsername());
		
		User userExistente = userService.findById(userUpdate.getId());
		if (userExistente == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);// mando conflicto pork ya existe el user
		}
		
		userExistente.setAddress(userUpdate.getAddress());
		userExistente.setEmail(userUpdate.getEmail());
		userExistente.setUsername(userUpdate.getUsername());
		
		
		userService.saveUser(userExistente);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//borrar un user por id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		
		System.out.println("Tratando de borrar al usuario id: " + id);
		
		User userExistente = userService.findById(id);
		if (userExistente == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);// mando conflicto pork ya existe el user
		}
		
		userService.deleteUserById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
