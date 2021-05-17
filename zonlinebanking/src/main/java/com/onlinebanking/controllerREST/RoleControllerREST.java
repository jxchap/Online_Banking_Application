package com.onlinebanking.controllerREST;

import java.util.List;

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

import com.onlinebanking.domain.Role;
import com.onlinebanking.service.RoleService;
import com.onlinebanking.validation.RoleValidator;

@RestController
public class RoleControllerREST {

	@Autowired
	RoleService roleService;

	@Autowired
	RoleValidator roleValidator;

	@PostMapping("/saveRoleREST")
	public ResponseEntity<?> saveTheRole(@RequestBody Role role, BindingResult br) {

		roleValidator.validate(role, br);

		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var potentialDuplicate = roleService.findRoleByName(role.getName());

		if (potentialDuplicate == null) {
			roleService.save(role);
			return new ResponseEntity<>(role, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Role with that name already exists", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/deleteRoleREST")
	public ResponseEntity<String> deleteTheRole(@RequestBody Role role) {

		var roleToDelete = roleService.findRoleByName(role.getName());

		if (roleToDelete != null) {
			roleService.deleteRole(role.getRoleId());
			return new ResponseEntity<>("Role Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updateRoleREST")
	public ResponseEntity<?> updateTheRole(@RequestBody Role role, BindingResult br) {

		roleValidator.validate(role, br);
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().toString(), HttpStatus.CONFLICT);
		}

		var roleToupdate = roleService.findRole(role.getRoleId());

		if (roleToupdate == null) {
			return new ResponseEntity<>("Role doesn't exist", HttpStatus.NOT_FOUND);
		} else {
			roleToupdate.setName(role.getName());
			roleService.save(roleToupdate);
			return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getRoles")
	public ResponseEntity<List<Role>> getRoles() {

		var result = roleService.getAllRoles();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
