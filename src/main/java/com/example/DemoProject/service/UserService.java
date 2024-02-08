package com.example.DemoProject.service;

import java.util.List;

import com.example.DemoProject.model.User;

public interface UserService {

	User createUser(User user);
	void deleteUser(int id);
	User updateUser(User user,int id);
	List<User> getAllUsers();
	User getUser(int id);
	List<User> searchUser(String searchText);
}
