package com.caronapp.BackEndUserModule.autenticacao.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.caronapp.BackEndUserModule.autenticacao.security.ConstantesSeguranca.EXPIRATION_TIME;
import static com.caronapp.BackEndUserModule.autenticacao.security.ConstantesSeguranca.HEADER_STRING;
import static com.caronapp.BackEndUserModule.autenticacao.security.ConstantesSeguranca.SECRET;
import static com.caronapp.BackEndUserModule.autenticacao.security.ConstantesSeguranca.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.auth0.jwt.JWT;
import com.caronapp.BackEndUserModule.autenticacao.entity.Login;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthFilter extends AbstractAuthenticationProcessingFilter {
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthFilter (String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		this.authenticationManager = authenticationManager;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Login usu = new ObjectMapper().readValue(request.getInputStream(), Login.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usu.getEmail(), usu.getSenha(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
}
