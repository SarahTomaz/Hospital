package br.edu.ufersa.hospital.model.entity;

import br.edu.ufersa.hospital.api.dto.AdministratorDTO;

public class Administrator extends Conta {
	public Administrator(Administrator admin) {
		this.username = admin.getUsername();
		this.senha = admin.getSenha();
	}
	public Administrator() {
		this.username = null;
		this.senha = null;
	}
	public Administrator(String username, String senha) {
		setUsername(username);
		setSenha(senha);
		}
	 public static Administrator converter(AdministratorDTO dto) {
		Administrator admin = new Administrator();
	        admin.setUsername(dto.getUsername());
	        admin.setSenha(dto.getSenha());
	        admin.setConfirmSenha(dto.getConfirmSenha());
	        return admin;
	    }
}
