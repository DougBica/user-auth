package com.caronapp.BackEndUserModule.autenticacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caronapp.BackEndUserModule.autenticacao.entity.Acesso;
import com.caronapp.BackEndUserModule.usuario.entity.Usuario;
import com.caronapp.BackEndUserModule.usuario.repository.UsuarioRepository;


@Service
public class ServiceDetalhesUsuario implements UserDetailsService {
	
	private UsuarioRepository usuarioRepository;
	
	public ServiceDetalhesUsuario(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = Optional.ofNullable(usuarioRepository.findByEmail(email)).orElse(null);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(usuario.getEmail(), usuario.getSenha(), carregaRoles(usuario.getAcessos()));
	}
	
	private List<GrantedAuthority> carregaRoles(List<Acesso> acessos){
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if(!acessos.isEmpty()) 
			acessos.forEach((acesso) -> roles.add(new SimpleGrantedAuthority(acesso.getDescricao())));	
		return roles;
	}

}
