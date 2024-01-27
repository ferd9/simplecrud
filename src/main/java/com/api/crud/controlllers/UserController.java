package com.api.crud.controlllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ArrayList<UserModel> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return userService.saveUser(user);
	}
	
	@GetMapping(path = "/{id}")
	public UserModel getUserById(@PathVariable("id") long id) {
		UserModel user = null;
		Optional<UserModel> oUser = userService.getById(id);
		
		if(oUser.isPresent()) {
			user = oUser.get();
		}
		
		return user;
	}
	
	@PutMapping(path = "/{id}")
	public UserModel updateUserById(@RequestBody UserModel user, @PathVariable("id") Long id) {
		return userService.updateById(user, id).orElseThrow();
	}
	
	@DeleteMapping(path = "/{id}")
	public Boolean deleteUserById(@PathVariable("id") Long id) {
		return userService.deleteUser(id);
	}
	
}
