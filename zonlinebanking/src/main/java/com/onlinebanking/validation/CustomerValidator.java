package com.onlinebanking.validation;



import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.Customer;

@Component
public class CustomerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
	return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "customerName.empty.value", "Customer name is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "gender.empty.value", "Gender is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerMobile", "customerMobile.empty.value", "Mobile is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerEmail", "customerEmail.empty.value", "Email is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.addressline1", "addressLine1.empty.value", "Address Line 1 is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.city", "city.empty.value", "City is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.state", "state.empty.value", "State is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.zip", "zip.empty.value", "Zip code is required");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerIDType", "customerIDType.empty.value", "Id-Type is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "dob.empty.value", "Date of birth is required");

		if(customer.getDob() !=null) {
			
			var customerAge = Period.between(customer.getDob(), LocalDate.now()).getYears();
			if(customerAge < 18) {
				errors.rejectValue("dob", "customer.dob.value", "Must be 18 years old.");
			}
			
		}
		
		
	}
	
	

}
