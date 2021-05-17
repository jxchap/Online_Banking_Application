package com.onlinebanking.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.domain.Customer;
import com.onlinebanking.domain.User;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.CustomerValidator;

@RestController
public class CustomerControllerREST {

	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;

	@Autowired
	CustomerValidator customerValidator;

	@PostMapping("/saveCustomerREST")
	public ResponseEntity<?> saveTheCustomer(@RequestBody Customer customer, BindingResult br) {

		customerValidator.validate(customer, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getFieldErrors().toString(),HttpStatus.CONFLICT);
		}
		
		var auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = userService.findUserByUsername(auth.getName());
		var existingCustomer = customerService.findByUsername(loggedInUser.getUsername());

		
		
		if (existingCustomer == null) {
			customer.setUsername(loggedInUser.getUsername());
			customerService.save(customer);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Customer already exists for that username", HttpStatus.CONFLICT);
		}
		

	}

	@PutMapping("/updateCustomerREST")
	public  ResponseEntity<?> updateTheCustomer(@RequestBody Customer customer, BindingResult br) {
		
		customerValidator.validate(customer, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getFieldErrors().toString(), HttpStatus.CONFLICT);
		}

		var customertoUpdate = customerService.findById(customer.getCustomerId());
		
		if (customertoUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		customertoUpdate.setCustomerAddress(customer.getCustomerAddress());
		customertoUpdate.setCustomerEmail(customer.getCustomerEmail());
		customertoUpdate.setCustomerIDType(customer.getCustomerIDType());
		customertoUpdate.setCustomerMobile(customer.getCustomerMobile());
		customertoUpdate.setCustomerName(customer.getCustomerName());
		customertoUpdate.setDob(customer.getDob());
		customertoUpdate.setGender(customer.getGender());
		
		customerService.save(customertoUpdate);
		

		return new ResponseEntity<>(customertoUpdate, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomersREST")
	public ResponseEntity<List<Customer>> getTheCustomers() {

		var listOfCustomers = customerService.findAll();

		return new ResponseEntity<>(listOfCustomers, HttpStatus.OK);
	}

}
