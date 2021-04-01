package com.kpininja.user.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpininja.user.model.User;
import com.kpininja.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public Optional<User> getUserByName(String name) {
		
		return userRepository.findById(name);
	}

	public boolean save(User user) {
		String result="";
		boolean flag=false;
		try {
			if(userRepository.findById(user.getName()).isPresent()) {
				result="User already exists";
				throw new Exception(result);
			}else {
				result="User created successfully";
				flag=true;
			userRepository.save(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
		public boolean update(User user) {
			String result="";
			boolean flag=false;
			try {
				if(userRepository.findById(user.getName()).isPresent()) {
				userRepository.save(user);
				flag=true;
				}else {
					result="Unauthorised to change the name.Click to save it as new user";
					throw new Exception(result);
				}
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			return flag;
			
		}
	
public void deleteUserByName(String name) {
		
		userRepository.deleteById(name);
	}


}
