package com.onlinebanking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	
	private String username;
	
	private String password;
	private String email;
	private String mobile;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	         joinColumns= {@JoinColumn(name="userId")},
	         inverseJoinColumns= {@JoinColumn(name="roleId")}
			)
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}

	public User(Long userId, String username, String password, String email, String mobile) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
