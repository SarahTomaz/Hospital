package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.MedicoDTO;
import br.edu.ufersa.hospital.api.dto.UsuarioDTO;
import br.edu.ufersa.hospital.model.entity.Usuario;
import br.edu.ufersa.hospital.model.service.AccountBO;
import br.edu.ufersa.hospital.model.service.UsuarioBO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class CadastroController {
	
		@FXML private TextField username;
	    @FXML private PasswordField senha;
	    @FXML private PasswordField confirmSenha;
	    @FXML private TextField email;
	    @FXML private Label erroAutent;
	    @FXML private Button botaoFechar;
	    UsuarioBO bo = new UsuarioBO();
	    AccountBO bo2 = new AccountBO();
	    public void cadastrar(ActionEvent action){
	      UsuarioDTO user = new UsuarioDTO();
	      if(senha.getText().equals(confirmSenha.getText())) {
	    	  
	      user.setSenha(senha.getText());
	      user.setUsername(username.getText());
	      try {
	           Usuario cadastrado = bo.adicionar(user);
	           bo2.adicionar(user);
	           Telas.login();
	           }
	       catch(PasswordErrorException e){
	       erroAutent.setText("Username ou senha não encontrados!");
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
