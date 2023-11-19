package com.in28minutes.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface todorepository extends JpaRepository<todo, Integer>{
	
	public List<todo> findByUsername(String username);
 
}
