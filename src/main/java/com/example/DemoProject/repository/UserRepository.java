package com.example.DemoProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DemoProject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByNameContainingIgnoreCaseOrGenderContainingIgnoreCaseOrAge(String name, String gender, int age);

	List<User> findByNameContainingIgnoreCaseOrGenderContainingIgnoreCase(String name, String gender);
}
