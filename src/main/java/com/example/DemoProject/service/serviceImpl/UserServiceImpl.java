package com.example.DemoProject.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DemoProject.model.User;
import com.example.DemoProject.repository.UserRepository;
import com.example.DemoProject.service.UserService;
import com.example.DemoProject.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		System.out.println("here");
		User findById = (User) this.userRepository.findById((long)id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id",(long) id));

		System.out.println("here");
		this.userRepository.delete(findById);
	}

	@Override
	public User updateUser(User user, int id) {

		User findById = (User) this.userRepository.findById((long)id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id", (long)id));
		findById.setName(user.getName());
		findById.setAge(user.getAge());
		findById.setGender(user.getGender());

		return this.userRepository.save(findById);
	}

	@Override
	public List<User> getAllUsers() {

		List<User> users=this.userRepository.findAll();
		
		return users;
	}

	@Override
	public User getUser(int id) {
		User findById = (User) this.userRepository.findById((long)id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id", (long)id));
		return findById;
	}

	@Override
	public List<User> searchUser(String searchText) {
	    try {
	        int age = Integer.parseInt(searchText);
	        return userRepository.findByNameContainingIgnoreCaseOrGenderContainingIgnoreCaseOrAge(searchText, searchText, age);
	    } catch (NumberFormatException e) {
	        return userRepository.findByNameContainingIgnoreCaseOrGenderContainingIgnoreCase(searchText, searchText);
	    }
	}

}
