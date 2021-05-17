package com.onlinebanking.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.Role;

@Component
public class RoleValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Role role = (Role) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty.value", "Role name is required");	
		
        if(!role.getName().matches("[a-zA-Z]+")) {
          	 errors.rejectValue("name", "role.name.value", "Role name must only contains letters");
           }
		
		
		//errors.rejectValue("dob", "customer.dob.value", "Must be 18 years old.");
	}

}
