package com.caronapp.BackEndUserModule.usuario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caronapp.BackEndUserModule.usuario.entity.Usuario;
import com.caronapp.BackEndUserModule.usuario.service.UsuarioService;

@RestController
@RequestMapping("/caronapp")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(path = "/public/usuario")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.createUsuario(usuario);
	}
	
	

}
