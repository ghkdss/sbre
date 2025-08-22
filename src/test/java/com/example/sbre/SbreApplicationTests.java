package com.example.sbre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.sbre.jwt.JwtService;

@SpringBootTest
class SbreApplicationTests {

	@Autowired
	private JwtService jwtService;
	@Test
	void contextLoads() {
		
		String jwt = jwtService.createToken("asd", null);
		
		System.out.println(jwt);
		
	}

}
