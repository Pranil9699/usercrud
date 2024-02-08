package com.example.DemoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoProject.exception.ApiResponse;
import com.example.DemoProject.model.User;
import com.example.DemoProject.service.UserService;

@RequestMapping("/api/v1/users")
@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User createUser = this.userService.createUser(user);
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = this.userService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		User updateUser = this.userService.updateUser(user, id);

		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return new ResponseEntity<List<User>>(this.userService.getAllUsers(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted for this id : " + id, true),
				HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchText}")
    public ResponseEntity<List<User>> searchUsers(@PathVariable String searchText) {
        List<User> users = userService.searchUser(searchText);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
