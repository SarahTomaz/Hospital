package br.edu.ufersa.hospital.model.entity;

import br.edu.ufersa.hospital.api.dto.AccountDTO;

public class Account {
	protected String username;
	protected String senha;
	protected String confirmSenha;
	public Account(Account Account) {
		this.username = Account.getUsername();
		this.senha = Account.getSenha();
	}
	public Account() {
		this.username = null;
		this.senha = null;
	}
	public Account(String username, String senha) {
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
	
	 public static Account converter(AccountDTO dto) {
	        Account Account = new Account();
	        Account.setUsername(dto.getUsername());
	        Account.setSenha(dto.getSenha());
	        Account.setConfirmSenha(dto.getConfirmSenha());
	        return Account;
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
