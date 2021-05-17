package com.onlinebanking.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.domain.User;
import com.onlinebanking.service.RoleService;
import com.onlinebanking.service.UserService;
import com.onlinebanking.validation.UserValidator;

@RestController
public class UserControllerREST {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserValidator userValidator;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/saveUserREST")
	public ResponseEntity<?> saveTheUser(@RequestBody User user, BindingResult br) {

		userValidator.validate(user, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		userService.save(user);
		var result = userService.findUserByUsername(user.getUsername());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUserREST")
	public ResponseEntity<String> deleteTheUser(@RequestBody User user) {

		var userToDelete = userService.findUserByUsername(user.getUsername());

		if (userToDelete == null) {
			return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
		} else {
			userService.deleteUserByUsername(user.getUsername());
			return new ResponseEntity<>("User deleted", HttpStatus.OK);
		}

	}

	@PutMapping("/updateUserREST")
	public ResponseEntity<?> updateTheUser(@RequestBody User user, BindingResult br) {

		userValidator.validate(user, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var userToUpdate = userService.findUserByUsername(user.getUsername());
		if (userToUpdate != null) {
			userToUpdate.setEmail(user.getEmail());
			userToUpdate.setMobile(user.getMobile());
			userToUpdate.setRoles(user.getRoles());
			userToUpdate.setPassword(user.getPassword());
			userService.save(userToUpdate);
			
			var result = userService.findUserByUsername(user.getUsername());

			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers() {

		var result = userService.getAllUsers();

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
