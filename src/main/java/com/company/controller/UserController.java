package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.model.User;
import com.company.repository.UserRepo;

@Controller 
public class UserController {

	@Autowired 
	private UserRepo userRepo;
	
	@GetMapping("/")
	public String getlog(Model model) {
		
		User user = new User();
		
		model.addAttribute("user1",user);
		
		return "index";
	}
	
	@GetMapping("/registr")
	public String registr(Model model) {
		
		User user = new User();
		
		model.addAttribute("user",user);	
		
		return "register";
	}
	
	@PostMapping("/regpro")
	public String registration(User user) {
		
		User u = new User(user.getEmail(),user.getName(),user.getSurname(),user.getUsername(),user.getPassword());
		
		userRepo.save(u);
		
		return "redirect:/";
	}
	
	@PostMapping("/loginfun")
	public String loginfun(User u) {
		
		String username1 = u.getUsername();
		
		User user = userRepo.findByUsername(username1);
		
		if(user!=null) {
			return "login";
		}
		
		return "redirect:/";
	}

}
