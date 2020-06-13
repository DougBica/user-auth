package com.caronapp.BackEndUserModule.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caronapp.BackEndUserModule.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByNome(String nome);

	public Usuario findByEmailAndSenha(String email, String senha);

	public Usuario findByEmail(String email);

}
