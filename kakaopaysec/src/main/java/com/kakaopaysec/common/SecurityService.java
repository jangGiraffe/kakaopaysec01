package com.kakaopaysec.common;

public interface SecurityService {
	String createToken(String subject);
	String getSubject(String token);
}
