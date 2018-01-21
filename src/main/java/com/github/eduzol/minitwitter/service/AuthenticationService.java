package com.github.eduzol.minitwitter.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  implements IAuthenticationService {

	@Override
	public Authentication getAuthentication() {
		 return SecurityContextHolder.getContext().getAuthentication();
	}

}
