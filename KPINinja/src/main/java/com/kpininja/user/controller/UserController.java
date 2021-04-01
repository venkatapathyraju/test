package com.kpininja.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kpininja.user.model.User;
import com.kpininja.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> getAllUsers = userService.getAllUsers();
        model.addAttribute("getAllUsers", getAllUsers);
        System.out.print("Get / ");	
        return "index";
    }
	
	/*
	 * @GetMapping("/user") private List<User> getAllUsers() { return
	 * userService.getAllUsers(); }
	 */
	@GetMapping("/newUser")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }
	
	@RequestMapping(value= "/saveUser", method= RequestMethod.POST)
	 public ModelAndView saveUser(@ModelAttribute("user") User user,RedirectAttributes redirAttrs) {
		ModelAndView model = new ModelAndView("result");
		boolean result=userService.save(user);
		 String message="";
		 if(result) {
			 message="User created successfully";
		 //redirAttrs.addFlashAttribute("success", "User created successfully");
			 model.addObject("message", message);
		 }else {
		  //redirAttrs.addFlashAttribute("error", "User already exists");
			 message="User already exists";
			 model.addObject("message", message);
		 }
		 return model;
	        //return "redirect:/";
	    }
	 @RequestMapping(value= "/updateUser", method= RequestMethod.POST)
	 public ModelAndView updateUser(@ModelAttribute("user") User user,RedirectAttributes redirAttrs) {
			ModelAndView model = new ModelAndView("result");
			boolean result=userService.update(user);
		 String message="";
		 if(result) {
			 message="User updated successfully";
			 model.addObject("message", message);
		 //redirAttrs.addFlashAttribute("success", "User updated successfully");
		 }else {
			 message="Unauthorised to change the name.Please add it as new user";
			 model.addObject("message", message);
			 //redirAttrs.addFlashAttribute("error", "Unauthorised to change the name.Please add it as new user");
		 }
		 
		 	return model;
	        //return "redirect:/";
	    }
	 
	 @RequestMapping("/edit/{name}")
	    public ModelAndView showUpdateUserPage(@PathVariable(name = "name") String name) {
	        ModelAndView mav = new ModelAndView("updateUser");
	        Optional<User> user = userService.getUserByName(name);
	        mav.addObject("user", user);
	        return mav;
	        
	    }
	 @RequestMapping("/delete/{name}")
	    public String deleteUser(@PathVariable(name = "name") String name) {
		 userService.deleteUserByName(name);
	        return "redirect:/";
	    }
	
		
}
