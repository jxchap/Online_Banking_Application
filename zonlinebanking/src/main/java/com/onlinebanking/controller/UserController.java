package com.onlinebanking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Role;
import com.onlinebanking.domain.User;
import com.onlinebanking.service.RoleService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserValidator userValidator;

	@RequestMapping("/userForm")
	public ModelAndView getUserForm(User user, Model model) {
		ModelAndView modelAndView = new ModelAndView("userForm");
		
		

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		boolean isAdmin = false;

		for (var role : loggedInUser.getRoles()) {

			if (role.getName().equalsIgnoreCase("admin")) {
				isAdmin = true;
			}
		}

		if (isAdmin) {

			model.addAttribute("roles", roleService.getAllRoles());
			model.addAttribute("users", userService.getAllUsers());

		} else {

			List<Role> roles = new ArrayList<>();
			roles.add(roleService.findRoleByName("User"));

			List<User> users = new ArrayList<>();
			users.add(userService.findUserByUsername(loggedInUser.getUsername()));

			model.addAttribute("roles", roles);
			model.addAttribute("users", users);

		}

		model.addAttribute("user", new User());

		return modelAndView;
	}

	@RequestMapping("/saveUser")
	public String saveTheUser(User user, Model model, BindingResult br) {
		
		
		userValidator.validate(user, br);
		
		if(br.hasErrors()) {
			model.addAttribute("users", userService.getAllUsers());
			model.addAttribute("roles", roleService.getAllRoles());
			return "userForm";
		}

		userService.save(user);

		model.addAttribute("users", userService.getAllUsers());

		return "redirect:userForm";
	}

	@RequestMapping("/deleteUser")
	public String deleteTheUser(@RequestParam String username, Model model) {

		var auth = SecurityContextHolder.getContext().getAuthentication();
		if (username.equalsIgnoreCase(auth.getName())) {
			userService.deleteUserByUsername(username);
			model.addAttribute("users", userService.getAllUsers());
			return "redirect:login?logout";
		}

		userService.deleteUserByUsername(username);
		model.addAttribute("users", userService.getAllUsers());

		return "redirect:userForm";
	}

	@RequestMapping("/updateUser")
	public String updateTheUser(User user, @RequestParam String username, Model model) {

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		boolean isAdmin = false;

		for (var role : loggedInUser.getRoles()) {

			if (role.getName().equalsIgnoreCase("admin")) {
				isAdmin = true;
			}
		}

		if (isAdmin) {

			model.addAttribute("roles", roleService.getAllRoles());
			model.addAttribute("users", userService.getAllUsers());

		} else {

			List<Role> roles = new ArrayList<>();
			roles.add(roleService.findRoleByName("User"));

			List<User> users = new ArrayList<>();
			users.add(userService.findUserByUsername(loggedInUser.getUsername()));

			model.addAttribute("roles", roles);
			model.addAttribute("users", users);

		}

//		model.addAttribute("users", userService.getAllUsers());
//		model.addAttribute("roles", userToUpdate.getRoles());

		var userToUpdate = userService.findUserByUsername(username);
		model.addAttribute("user", userToUpdate);

		return "userForm";
	}

}
