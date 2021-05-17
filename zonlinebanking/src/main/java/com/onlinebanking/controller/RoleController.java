package com.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Role;
import com.onlinebanking.service.RoleService;
import com.onlinebanking.validation.RoleValidator;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleValidator roleValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder webDataBinder) {
//		webDataBinder.addValidators(roleValidator);
//	}

	@RequestMapping("/roleForm")
	public ModelAndView getBranchForm(Role e, Model model) {
		ModelAndView modelAndView = new ModelAndView("roleForm");

		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("role", new Role());

		return modelAndView;
	}

	@RequestMapping("/saveRole")
	public String saveTheRole(Role role, Model model, BindingResult br) {
		roleValidator.validate(role, br);
		
		if (br.hasErrors()) {
			model.addAttribute("roles", roleService.getAllRoles());
			return "roleForm";
		}

		roleService.save(role);

		model.addAttribute("roles", roleService.getAllRoles());

		return "redirect:roleForm";
	}

	@RequestMapping("/deleteRole")
	public String deleteTheRole(@RequestParam long roleId, Model model) {

		roleService.deleteRole(roleId);
		model.addAttribute("roles", roleService.getAllRoles());

		return "redirect:roleForm";
	}

	@RequestMapping("/updateRole")
	public String updateTheRole(Role role, @RequestParam long roleId, Model model, BindingResult br) {
		
		var roleToupdate = roleService.findRole(roleId);
		model.addAttribute("role", roleToupdate);

		model.addAttribute("roles", roleService.getAllRoles());

		return "roleForm";
	}

}
