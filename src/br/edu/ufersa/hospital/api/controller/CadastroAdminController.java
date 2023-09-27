package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.AdministratorDTO;
import br.edu.ufersa.hospital.model.entity.Administrator;
import br.edu.ufersa.hospital.model.service.AdministratorBO;
import br.edu.ufersa.hospital.model.service.AccountBO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroAdminController {
	
		@FXML private TextField username;
	    @FXML private PasswordField senha;
	    @FXML private PasswordField confirmSenha;
	    @FXML private TextField email;
	    @FXML private Label erroAutent;
	    @FXML private Button botaoFechar;
	    AdministratorBO bo = new AdministratorBO();
	    AccountBO bo2 = new AccountBO();
	    public void cadastrar(ActionEvent action){
			AdministratorDTO admin = new AdministratorDTO();
	      if(senha.getText().equals(confirmSenha.getText())) {
	    	  
	      admin.setSenha(senha.getText());
	      admin.setUsername(username.getText());
	      try {
	           Adm cadastrado = bo.adicionar(admin);
	           bo2.adicionar(admin);
	           Telas.login();
	           }
	       catch(PasswordErrorException e){
	       erroAutent.setText("username ou senha não encontrados!");
	       erroAutent.setVisible(true);
	       botaoFechar.setVisible(true);
	       botaoFechar.setDisable(false);
	       }            
	         }
	      else {
	    	  erroAutent.setText("Preencha corretamente!");
		      erroAutent.setVisible(true);
		      botaoFechar.setVisible(true);
		      botaoFechar.setDisable(false);
	      }
	    }
	    
	    public void voltarTelaLogin() {
	    	Telas.login();
	    }
	    
	    public void fecharError(ActionEvent action) {
	    	erroAutent.setVisible(false);
	    	botaoFechar.setVisible(false);
	    	botaoFechar.setDisable(true);
	    }
	    public void IrTelaCadastroAdmin(ActionEvent action) {
	    	Telas.cadastroAdmin();
	    }
	}
