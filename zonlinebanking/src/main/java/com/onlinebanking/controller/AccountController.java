package com.onlinebanking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Account;
import com.onlinebanking.domain.Branch;
import com.onlinebanking.domain.enums.AccountType;
import com.onlinebanking.service.AccountService;
import com.onlinebanking.service.BranchService;
import com.onlinebanking.service.CustomerService;
import com.onlinebanking.validation.AccountValidator;

@Controller
public class AccountController {

	@Autowired
	BranchService branchService;

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

	@Autowired
	AccountValidator accountValidator;

//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setValidator(accountValidator);
//	}

	@RequestMapping("/accountForm")
	public ModelAndView getAccountForm(Branch e, Model model) {
		ModelAndView modelAndView = new ModelAndView("accountForm");

		model.addAttribute("account", new Account());
		model.addAttribute("accountTypes", AccountType.values());
		model.addAttribute("accountBranches", branchService.getAllBranches());

		model.addAttribute("customerAccounts", customerService.findAll());

		return modelAndView;
	}

	@RequestMapping("/saveAccount")
	public String saveTheAccount(Account account, Model model, BindingResult br) {

		accountValidator.validate(account, br);
		
		if (br.hasErrors()) {
			model.addAttribute("accountTypes", AccountType.values());
			model.addAttribute("accountBranches", branchService.getAllBranches());

			model.addAttribute("customerAccounts", customerService.findAll());

			return "accountForm";
		}

		account.setAccountDateOpened(LocalDate.now());
		accountService.save(account);

		return "redirect:accountForm";
	}

}
