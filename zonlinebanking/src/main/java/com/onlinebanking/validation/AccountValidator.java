package com.onlinebanking.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.onlinebanking.domain.Account;

@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Account.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountType", "accountType.empty.value",
				"Account Type is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountBranch", "accountBranch.empty.value",
				"Account Branch is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountCustomer", "accountCustomer.empty.value",
				"Customer ID is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountBalance", "accountBalance.empty.value", "Account Balance is required");
		
		if (account.getAccountType() != null && account.getAccountBalance() != null) {
			
			switch (account.getAccountType()) {

			case CHECKING:
				if (account.getAccountBalance() < 100) {
					errors.rejectValue("accountBalance", "account.accountBalance.value",
							"Account balance minimum is $100");
				}
				break;
			case SAVINGS:
				if (account.getAccountBalance() < 50) {
					errors.rejectValue("accountBalance", "account.accountBalance.value",
							"Account balance minimum is $50");
				}
				break;
			case LOAN:
				if (account.getAccountBalance() < 50) {
					errors.rejectValue("accountBalance", "account.accountBalance.value", "Loan minimum is $500");
				}
				break;

			}
		}

	}

}
