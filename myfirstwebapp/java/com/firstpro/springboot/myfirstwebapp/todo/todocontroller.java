package com.firstpro.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class todocontroller {

	private todoservice todoservice;

	public todocontroller(com.firstpro.springboot.myfirstwebapp.todo.todoservice todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@RequestMapping("list-todos")
	public String listalltodos(ModelMap model) {
		String username=getloggedinusername(model);
		List<todo> todos = todoservice.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listtodos";

	}

	private String getloggedinusername(ModelMap model) {

		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String shownewtodopage(ModelMap model) {
		todo todo = new todo(0, getloggedinusername(model), "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";

	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addnewtodopage(ModelMap model, @Valid todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		todoservice.addtodo(getloggedinusername(model), todo.getDescription(), todo.getTargetdate(), false);
		return "redirect:list-todos";

	}

	@RequestMapping("delete-todo")
	public String deletetodos(@RequestParam int id) {
		todoservice.deletbyid(id);
		return "redirect:list-todos";

	}
	
	@RequestMapping(value ="update-todo", method = RequestMethod.GET)
	public String showupdatetodos(@RequestParam int id ,ModelMap model	) {
		todo todo=todoservice.findbyid(id);
		model.addAttribute("todo",todo);
		return "todo";

	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updatetodos(ModelMap model, @Valid todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username=getloggedinusername(model);
		todo.setUsername(username);
		todoservice.update(todo);
		return "redirect:list-todos";

	}
	
	
	

}
