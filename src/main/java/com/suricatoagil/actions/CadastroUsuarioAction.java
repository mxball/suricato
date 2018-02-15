package com.suricatoagil.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suricatoagil.daos.PermissaoDao;
import com.suricatoagil.models.Usuario;
import com.suricatoagil.viewmodels.CadastroUsuarioDTO;

@Repository
public class CadastroUsuarioAction {

	@Autowired
	private PermissaoDao permissaoDao;
	
	public Usuario criaUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(cadastroUsuarioDTO.getNome());
		usuario.setSenha(cadastroUsuarioDTO.getSenhaCriptada());
		usuario.setEmail(cadastroUsuarioDTO.getEmail());
		usuario.setPermissao(permissaoDao.getPermissaoUsuario());
		return usuario;
	}
}
