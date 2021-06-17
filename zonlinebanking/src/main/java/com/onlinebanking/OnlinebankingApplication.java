package com.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlinebankingApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/dinesh");
		System.setProperty("server.servlet.context-path", "/BestBank");
		SpringApplication.run(OnlinebankingApplication.class, args);
		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(OnlinebankingApplication.class);
		
	}

}

/*
1. SpringBoot application should extend SpringBootServletInitializer.
   override the configure method that takes and returns SpringApplicationBuilder

2. Change the default packaging from .jar to .war as below in pom.xml:
<packaging>war</packaging>

3. Comment the dependency for embedded tomcat in pom.xml and go for following dependency:
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>

In order to avoid any problem with jsp implict object pageContext on jsp pages go for following dependency:
         <dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>javax.servlet.jsp-api</artifactId>
			<version>2.3.3</version>
			<scope>provided</scope>
		</dependency>

4. Edit tomcat-installation-directory/conf/tomcat-users.xml to provide user name, password, and role:
<tomcat-users>
    <user username="dinesh" password="dinesh" roles="manager-gui"/>
</tomcat-users>

5. Go to Tomcat's Manager App from the URL http://localhost:8080/
6. Fill in login details if prompted for that.
7. Go to "War file to deploy" section of Tomcat Web Application Manager and click on the browse button to get the location of .war file.
8. Click on the deploy button. Application is deployed in few minutes.
9. Once deployed then the application can be accessed from any browser.




*/