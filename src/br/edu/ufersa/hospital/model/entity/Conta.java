package br.edu.ufersa.hospital.model.entity;

import br.edu.ufersa.hospital.api.dto.ContaDTO;

public class Conta {
	protected String username;
	protected String senha;
	protected String confirmSenha;
	public Conta(Conta conta) {
		this.username = conta.getUsername();
		this.senha = conta.getSenha();
	}
	public Conta() {
		this.username = null;
		this.senha = null;
	}
	public Conta(String username, String senha) {
		setUsername(username);
		setSenha(senha);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username != null){
            this.username = username;
        }
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		if(senha != null){
            this.senha = senha;
        }
	}
	
	 public static Conta converter(ContaDTO dto) {
	        Conta conta = new Conta();
	        conta.setUsername(dto.getUsername());
	        conta.setSenha(dto.getSenha());
	        conta.setConfirmSenha(dto.getConfirmSenha());
	        return conta;
	    }
	public String getConfirmSenha() {
		return confirmSenha;
	}
	public void setConfirmSenha(String confirmSenha) {
		if(confirmSenha != null){
            this.confirmSenha = confirmSenha;
        }
	}
}
