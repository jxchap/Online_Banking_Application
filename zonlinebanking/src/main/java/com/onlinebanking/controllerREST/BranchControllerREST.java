package com.onlinebanking.controllerREST;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.domain.Branch;
import com.onlinebanking.service.BranchService;
import com.onlinebanking.validation.BranchValidator;

@RestController
public class BranchControllerREST {

	@Autowired
	BranchService branchService;

	@Autowired
	BranchValidator branchValidator;

	@PostMapping("/saveBranchREST")
	public ResponseEntity<Branch> saveTheBranch(@Valid @RequestBody Branch branch, BindingResult br) {

		branchValidator.validate(branch, br);

		if (br.hasErrors()) {
			System.out.println(br.getAllErrors().toString());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		branchService.save(branch);
		return new ResponseEntity<>(branch, HttpStatus.OK);

	}

	@DeleteMapping("/deleteBranchREST")
	public ResponseEntity<String> deleteTheBranch(@Valid @RequestBody Branch branch) {

		var branchToDelete = branchService.findBranch(branch.getBranchId());
		if (branchToDelete != null) {
			branchService.deleteBranch(branch.getBranchId());
			return new ResponseEntity<>("This has been deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("This was not found", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateBranchREST")
	public ResponseEntity<Branch> updateTheBranch(@Valid @RequestBody Branch branch, BindingResult br) {

		var branchToUpdate = branchService.findBranch(branch.getBranchId());

		if (branchToUpdate == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		branchToUpdate.setBranchAddress(branch.getBranchAddress());
		branchToUpdate.setBranchName(branch.getBranchName());

		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		branchService.save(branchToUpdate);
		return new ResponseEntity<>(branchToUpdate, HttpStatus.OK);
	}

	@GetMapping("/getBranchesREST")
	public ResponseEntity<List<Branch>> getTheBranches() {

		var result = branchService.getAllBranches();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
