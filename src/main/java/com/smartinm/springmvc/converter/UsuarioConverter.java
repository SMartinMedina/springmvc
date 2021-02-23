package com.smartinm.springmvc.converter;

import org.springframework.stereotype.Component;

import com.smartinm.springmvc.entity.Usuario;
import com.smartinm.springmvc.model.UsuarioModel;

@Component("usuarioConverter")
public class UsuarioConverter {
	
	public UsuarioModel entity2Model(Usuario usuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setId(usuario.getId());
		usuarioModel.setNombre(usuario.getNombre());
		usuarioModel.setUser(usuario.getUser());
		usuarioModel.setPass(usuario.getPass());
		
		return usuarioModel;
	}

	public Usuario model2Entity(UsuarioModel usuarioModel) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioModel.getId());
		usuario.setNombre(usuarioModel.getNombre());
		usuario.setUser(usuarioModel.getUser());
		usuario.setPass(usuarioModel.getPass());
		
		return usuario;
	}
}
