package com.onlinebanking.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource(value = "classpath:db.properties")
public class AppConfig {

	@Autowired
	Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("driver"));
		dataSource.setUrl(environment.getProperty("url"));
		dataSource.setUsername(environment.getProperty("myusername"));
		dataSource.setPassword(environment.getProperty("mypassword"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean x = new LocalContainerEntityManagerFactoryBean();
		x.setDataSource(dataSource());
		x.setJpaProperties(jpaProperties());
		x.setPackagesToScan("com.onlinebanking.domain");
		x.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return x;

	}

	public Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "update"); // switch back to update later, other options are
																	// create-drop or validate
		properties.setProperty("hibernate.show_SQL", "false");
		return properties;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver y = new InternalResourceViewResolver();
		y.setPrefix("/WEB-INF/views/");
		y.setSuffix(".jsp");
		y.setViewClass(JstlView.class);
		return y;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		
		return new MappingJackson2HttpMessageConverter();
		
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
		
		
	     
	    mailSender.setUsername(System.getenv("SynfreeUsername"));
	    mailSender.setPassword(System.getenv("SynfreePass"));
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "false");
	     
	    return mailSender;
	}
}
