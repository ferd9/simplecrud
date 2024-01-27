package com.api.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	IUserRepository userRepository;
	
	public ArrayList<UserModel> getUsers(){		
		ArrayList<UserModel> usuarios = new ArrayList<UserModel>();
		usuarios.addAll(userRepository.findAll());
		if(usuarios.isEmpty())
		{
			return null;
		}
		return usuarios;
	}
	
	public UserModel saveUser(UserModel user) {
		return userRepository.save(user);
	}
	
	public Optional<UserModel> getById(Long id){
		return userRepository.findById(id);
	}
	
	public Optional<UserModel> updateById(UserModel userModel, Long id){
		Optional<UserModel> user = userRepository.findById(id);
		if(user.isPresent()) {
			user.get().setFirtName(userModel.getFirtName());
			user.get().setLastName(userModel.getLastName());
			user.get().setEmail(userModel.getEmail());
			userRepository.save(user.get());
		}else {
			user.orElseThrow();
		}
	
		return user;
	}
	
	public Boolean deleteUser(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
