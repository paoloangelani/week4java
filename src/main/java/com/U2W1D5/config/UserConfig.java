package com.U2W1D5.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W1D5.model.User;
import com.github.javafaker.Faker;

@Configuration
public class UserConfig {
	
	@Bean("User")
	@Scope("prototype")
	public User paramsUser(String fullname, String username, String email) {
		return new User(fullname, username, email);
	}
	
	@Bean("FakeUser")
	@Scope("prototype")
	public User fakeUser() {
		Faker f = Faker.instance(new Locale("it-IT"));
		User u = new User();
		String rName = f.name().firstName();
		String rSurname = f.name().lastName();
		u.setFullname(rName + " " + rSurname);
		u.setUsername(rName.toLowerCase() + "." + rSurname.toLowerCase() + "#");
		u.setEmail(rName.toLowerCase() + "." + rSurname.toLowerCase() + "@doge.com");
		return u;
	}
}
