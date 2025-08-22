package com.example.sbre.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbre.domain.Member;
import com.example.sbre.jwt.JwtService;
import com.example.sbre.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Member member) {
		memberService.insert(member);
		
		return new ResponseEntity<>("회원가입 성공!", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
		UsernamePasswordAuthenticationToken cred = 
				new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		
		Authentication auth = authenticationManager.authenticate(cred);
		
		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities());
		
		return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
					.build();
	}
	
	
	@GetMapping("/userinfo")
	public ResponseEntity<?> userInfo(Authentication auth) {
		Member member = memberService.getMember(auth.getName());
		
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
}





