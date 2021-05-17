package com.onlinebanking.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.User;

@Component
public class UserValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

@Override
public void validate(Object target, Errors errors) {
	User user = (User) target;
	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty.value", "Username is required");	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty.value", "Password is required");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty.value", "Email is required");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "mobile.empty.value", "Mobile is required");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "roles.empty.value", "At least one role is required");
	
    if(!user.getUsername().matches("[a-zA-Z]+")) {
      	 errors.rejectValue("username", "user.username.value", "Username must only contains letters");
       }
	
	
}

}
