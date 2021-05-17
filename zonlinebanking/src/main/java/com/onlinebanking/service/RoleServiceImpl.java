package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.domain.Role;
import com.onlinebanking.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findRole(long roleId) {
		var result = roleRepository.findById(roleId);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

	@Override
	public void deleteRole(long roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findRoleByName(String roleName) {
		var result = roleRepository.findByName(roleName);

		if (result.isPresent()) {
			return result.get();
		}

		return null;
	}

}
