package com.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Address;
import com.onlinebanking.domain.Customer;
import com.onlinebanking.domain.User;
import com.onlinebanking.domain.enums.Gender;
import com.onlinebanking.domain.enums.IdentityType;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.CustomerValidator;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;
	
	@Autowired
	CustomerValidator customerValidator;
	
//	@InitBinder
//	public void initBinder(WebDataBinder webDataBinder) {
//		webDataBinder.addValidators(customerValidator);
//	}

	@RequestMapping("/customerForm")
	public ModelAndView getCustomerForm(Customer customer, Model model) {

		ModelAndView modelAndView = new ModelAndView("customerForm");

		model.addAttribute("customerGenders", Gender.values());
		model.addAttribute("customerIdTypes", IdentityType.values());
		model.addAttribute("customer", new Customer());
		model.addAttribute("customerAddress", new Address());

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		if (existingCustomer != null) {
			model.addAttribute("existingCustomer", existingCustomer);
		} else {

		}
		
		

		return modelAndView;
	}

	@RequestMapping("/saveCustomer")
	public String saveTheCustomer(Customer customer, Model model, BindingResult br) {
		
		customerValidator.validate(customer, br);

		if (br.hasErrors()) {
			model.addAttribute("customerGenders", Gender.values());
			model.addAttribute("customerIdTypes", IdentityType.values());
			model.addAttribute("customerAddress", new Address());
			
			return "customerForm";
		}

		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		if (existingCustomer == null) {
			customer.setUsername(loggedInUser.getUsername());
			customerService.save(customer);

			model.addAttribute("customer", customer);

			return "redirect:customerForm";
		}

		
		if (customer.getCustomerId()!= null && customer.getCustomerId().equals(existingCustomer.getCustomerId())) {
			customer.setUsername(existingCustomer.getUsername());
			customerService.save(customer);

			model.addAttribute("customer", customer);
			model.addAttribute("existingCustomer", existingCustomer);
			return "redirect:customerForm";
		}

		model.addAttribute("customer", customer);
		model.addAttribute("existingCustomer", existingCustomer);

		return "redirect:customerForm";

	}

	@RequestMapping("/updateCustomer")
	public String updateTheCustomer(Customer customer, @RequestParam long customerId, Model model) {

		model.addAttribute("customerGenders", Gender.values());
		model.addAttribute("customerIdTypes", IdentityType.values());
		
		var customertoUpdate = customerService.findById(customerId);
		model.addAttribute("customer", customertoUpdate);
		model.addAttribute("existingCustomer", customertoUpdate);

		return "customerForm";
	}

}
