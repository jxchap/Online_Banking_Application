package com.onlinebanking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] adminOnlyList = new String[] { "/saveBranch", "/deleteBranch", "/updateBranch",
			"/roleForm", "/saveRole", "/deleteRole", "/updateRole", "/deleteUser","/accountForm"};

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(adminOnlyList).hasAuthority("Admin")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/home")
		.and()
		.csrf().disable();

	}

}
