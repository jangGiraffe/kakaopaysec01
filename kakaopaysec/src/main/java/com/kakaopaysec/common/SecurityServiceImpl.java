package com.kakaopaysec.common;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityServiceImpl implements SecurityService{
	private static final String SECRETKEY = "kakaoPaySec";
	private static final long EXPIRATION_TIME = 1000*60*60; 

	@Override
	public String createToken(String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySeBytes = DatatypeConverter.parseBase64Binary(SECRETKEY);
		Key signingKey = new SecretKeySpec(apiKeySeBytes,signatureAlgorithm.getJcaName());
		
		JwtBuilder builder = Jwts.builder().setSubject(subject).signWith(signatureAlgorithm, signingKey);
		
		long nowMillis = System.currentTimeMillis();
		builder.setExpiration(new Date(nowMillis+EXPIRATION_TIME));
		return builder.compact();
	}

	@Override
	public String getSubject(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY)).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
}
