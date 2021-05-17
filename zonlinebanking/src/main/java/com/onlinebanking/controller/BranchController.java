package com.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebanking.domain.Address;
import com.onlinebanking.domain.Branch;
import com.onlinebanking.service.BranchService;
import com.onlinebanking.validation.BranchValidator;

@Controller
public class BranchController {

	@Autowired
	BranchService branchService;

	@Autowired
	BranchValidator branchValidator;

//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setValidator(branchValidator);
//	}

	@RequestMapping("/branchForm")
	public ModelAndView getBranchForm(Branch e, Model model) {
		ModelAndView modelAndView = new ModelAndView("branchForm");

		model.addAttribute("branches", branchService.getAllBranches());
		model.addAttribute("branch", new Branch());
		model.addAttribute("branchAddress", new Address());

		return modelAndView;
	}

	@RequestMapping("/saveBranch")
	public String saveTheBranch(@ModelAttribute Branch branch, BindingResult br, Model model) {

		 branchValidator.validate(branch, br);

		if (br.hasErrors()) {
			model.addAttribute("branches", branchService.getAllBranches());
			return "branchForm";
		}

		branchService.save(branch);

		model.addAttribute("branches", branchService.getAllBranches());

		return "redirect:branchForm";
	}

	@RequestMapping("/deleteBranch")
	public String deleteTheBranch(@RequestParam long branchId, Model model) {

		branchService.deleteBranch(branchId);
		model.addAttribute("branches", branchService.getAllBranches());

		return "redirect:branchForm";
	}

	@RequestMapping("/updateBranch")
	public String updateTheBranch(Branch branch, @RequestParam long branchId, Model model) {

		var branchToUpdate = branchService.findBranch(branchId);
		model.addAttribute("branch", branchToUpdate);

		model.addAttribute("branches", branchService.getAllBranches());

		return "branchForm";
	}

}
