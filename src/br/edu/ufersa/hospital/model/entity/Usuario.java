package br.edu.ufersa.hospital.model.entity;

import br.edu.ufersa.hospital.api.dto.UsuarioDTO;

public class Usuario extends Conta {
	public Usuario(Usuario user) {
		this.username = user.getUsername();
		this.senha = user.getSenha();
	}
	public Usuario() {
		this.username = null;
		this.senha = null;
	}
	public Usuario(String username, String senha) {
		setUsername(username);
		setSenha(senha);
	}
	 public static Usuario converter(UsuarioDTO dto) {
	        Usuario user = new Usuario();
	        user.setUsername(dto.getUsername());
	        user.setSenha(dto.getSenha());
	        user.setConfirmSenha(dto.getConfirmSenha());
	        return user;
	    }
}
