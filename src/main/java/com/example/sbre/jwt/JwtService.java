package com.example.sbre.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	// 토큰의 만료 시간 저장 변수 ( 단위 : ms )
	static final long EXPIRATIONTIME = 24 * 60 * 60 * 1000;
	
	// 토큰 타입 ( 응답 헤더에 사용될 접두어 )
	static final String PREFIX = "Bearer ";
	
	// 임시 서명 키 
	static final SecretKey KEY = Jwts.SIG.HS256.key().build();
	
	// 권한 관련 클레임 키
	static final String ROLES_CLAIM = "roles";
	
	
	public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Date now = new Date();
		Date exp = new Date( now.getTime() + EXPIRATIONTIME );
		
		List<String> roles = (authorities == null)
								? List.of()
								: authorities.stream()
											 .map(GrantedAuthority::getAuthority)
											 .toList();
		
		return Jwts.builder()
				   .subject(username) 	// sub
				   .issuedAt(now)		// iat
				   .expiration(exp)		// exp
				   .signWith(KEY)		// 서명
				   .claim(ROLES_CLAIM, roles) // 커스텀 클레임
				   .compact();
	}
	
}







