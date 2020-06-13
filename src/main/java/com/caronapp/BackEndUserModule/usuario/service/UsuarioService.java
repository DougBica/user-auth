package com.caronapp.BackEndUserModule.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.caronapp.BackEndUserModule.usuario.entity.Usuario;
import com.caronapp.BackEndUserModule.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public Usuario createUsuario(Usuario usu) {
		try {		
			usu.setSenha(bCryptPasswordEncoder.encode(usu.getSenha()));
			return usuarioRepository.save(usu);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Usuario> ListarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuario(Long id) throws Exception {
		return Optional.ofNullable(usuarioRepository.findById(id)).orElse(null).orElseThrow(() -> new Exception());
	}

}
