package com.caronapp.BackEndUserModule.autenticacao.entity;

import java.io.Serializable;
import java.util.List;

public class Login implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6731870853425936047L;
	private String email;
	private String nome;
	private String senha;
	private List<Acesso> acessos;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Acesso> getAcessos() {
		return acessos;
	}
	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}
	
	

}
