package com.onlinebanking.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.Branch;


@Component
public class BranchValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Branch.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Branch branch = (Branch)target;

//		//checking branch name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName", "branch.branchName.empty", "Branch name is required.");
        
        if(!branch.getBranchName().matches("[a-zA-Z]+")) {
       	 errors.rejectValue("branchName", "branch.branchName.value", "Branch name must only contains letters");
        }
        
        //checking address fields
        if(branch.getBranchAddress() != null) {
        	
        	if (branch.getBranchAddress().getAddressline1().isBlank())
        	errors.rejectValue("branchAddress.addressline1", "branch.branchAddressLine1.value", "Branch Address Line 1 is required.");
        	//errors.rejectValue("branchAddressLine1", "branch.branchAddressLine1.value", "Branch Address Line 1 must only contain letters, numbers, and periods.");
        	
//        	if (branch.getBranchAddress().getAddressline2().isBlank())
//        	errors.rejectValue("branchAddress.addressline2", "branch.branchAddressLine2.value", "Branch Address Line 2 must only contain letters, numbers, and periods.");
        	
        	if (branch.getBranchAddress().getCity().isBlank())
        	errors.rejectValue("branchAddress.city", "branch.branchAddress.city.value", "City is required.");
        	//errors.rejectValue("city", "branch.city.value", "City must only contain letters and periods.");
        	
        	if (branch.getBranchAddress().getState().isBlank())
        	errors.rejectValue("branchAddress.state", "branch.state.value", "State is required.");
        	//errors.rejectValue("state", "branch.state.value", "State must only contain letters and periods.");
        	
        	if (branch.getBranchAddress().getZip().isBlank())
        	errors.rejectValue("branchAddress.zip", "branch.zip.value", "Zipcode is required.");
        	//errors.rejectValue("zip", "branch.zip.value", "Zipcode must only contain numbers and dashes.");
        	      	
        }
	}

}
