package com.caronapp.BackEndUserModule.redis.lettuce.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class RedisDataObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5433168113747120339L;
	
	private String email;
	private String token;
	private List<GrantedAuthority> acessos; 
	
	
	public RedisDataObject(String email, String token, List<GrantedAuthority> acessos) {
		super();
		this.email = email;
		this.token = token;
		this.acessos = acessos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<GrantedAuthority> getAcessos() {
		return acessos;
	}
	public void setAcessos(List<GrantedAuthority> acessos) {
		this.acessos = acessos;
	}
	
}
