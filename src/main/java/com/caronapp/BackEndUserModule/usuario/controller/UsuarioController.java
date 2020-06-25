package com.caronapp.BackEndUserModule.usuario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caronapp.BackEndUserModule.autenticacao.entity.Login;
import com.caronapp.BackEndUserModule.usuario.entity.Usuario;
import com.caronapp.BackEndUserModule.usuario.service.UsuarioService;

@RestController
@RequestMapping("/caronapp")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(path = "/private/usuario")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.createUsuario(usuario);
	}
	
	@PostMapping(path = "/private/usuario/login")
	public Usuario createUsuario(@RequestBody Login login) {
		return usuarioService.login(login);
	}
	
	
	@PostMapping(path = "/public/usuario/email")
	public Usuario getUsuarioPorEmail(@RequestBody Login email) {
		try {
			return usuarioService.getUsuarioPorEmail(email.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
