package com.onlinebanking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long roleId;
	
	private String name;
	
	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private Set<User> users = new HashSet<>();
	
	public Role() {
		
	}

	public Role(Long roleId, String name, Set<User> users) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.users = users;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name+ "]";
	}

	
	
}
