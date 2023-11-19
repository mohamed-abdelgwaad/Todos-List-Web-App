package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class todoservice {

	private static List<todo> todos = new ArrayList<>();
	private static int todoscount = 0;
	static {
		todos.add(new todo(++todoscount, "in28minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
		todos.add(new todo(++todoscount, "in28minutes", "Learn DevOps", LocalDate.now().plusYears(2), false));
		todos.add(new todo(++todoscount, "in28minutes", "Learn Full Stack Development", LocalDate.now().plusYears(3),
				false));
	}

	public List<todo> findByUsername(String username) {
		Predicate<? super todo> predicate =
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

	public void addtodo(String username, String description, LocalDate targetdate, boolean done) {
		todos.add(new todo(++todoscount, username, description, targetdate, done));

	}

	public void deletbyid(int id) {
		Predicate<? super todo> predicate = todo -> todo.getId() == id;

		todos.removeIf(predicate);

	}

	public todo findbyid(int id) {
		Predicate<? super todo> predicate = todo -> todo.getId() == id;

             return todos.stream().filter(predicate).findFirst().get();
	}

	public void update(@Valid todo todo) {
	deletbyid(todo.getId());
	todos.add(todo);
	}
	
	
	

}
