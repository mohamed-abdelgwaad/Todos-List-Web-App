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

@Controller
@SessionAttributes("name")
public class todocontrollerjpa {


	private todorepository todorepository;
	public todocontrollerjpa( todorepository todorepository) {
		super();
	
		this.todorepository=todorepository;
	}

	@RequestMapping("list-todos")
	public String listalltodos(ModelMap model) {
		String username=getloggedinusername(model);
		
		List<todo> todos = todorepository.findByUsername(username);
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
		String username=getloggedinusername(model);
		todo todo = new todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";

	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addnewtodopage(ModelMap model, @Valid todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username=getloggedinusername(model);
		todo.setUsername(username);
		todorepository.save(todo);
		//todoservice.addtodo(username, 
			//	todo.getDescription(), todo.getTargetdate(), todo.isDone());
		return "redirect:list-todos";

	}

	@RequestMapping("delete-todo")
	public String deletetodos(@RequestParam int id) {
		todorepository.deleteById(id);
	
		return "redirect:list-todos";

	}
	
	@RequestMapping(value ="update-todo", method = RequestMethod.GET)
	public String showupdatetodos(@RequestParam int id ,ModelMap model	) {
		todo todo=todorepository.findById(id).get();
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
		todorepository.save(todo);
		return "redirect:list-todos";

	}
	
	
	

}
