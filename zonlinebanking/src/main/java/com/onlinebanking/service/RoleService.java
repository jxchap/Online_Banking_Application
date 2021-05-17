package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.domain.Role;

public interface RoleService {

	public Role findRole(long roleId);

	public void deleteRole(long roleId);

	public List<Role> getAllRoles();

	public Role save(Role role);
	
	public Role findRoleByName(String roleName);

}
