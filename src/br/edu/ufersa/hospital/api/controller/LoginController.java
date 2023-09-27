package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.model.entity.Account;
import br.edu.ufersa.hospital.model.entity.Usuario;
import br.edu.ufersa.hospital.model.service.AccountBO;
import br.edu.ufersa.hospital.model.service.UsuarioBO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController {
	@FXML private TextField nomeUsuario;
    @FXML private PasswordField senha;
    @FXML private Label erroAutent;
    @FXML private Button botaoFechar;
    AccountBO<Conta> bo = new AccountBO<Conta>();
    public void autenticar(ActionEvent action) {
            Conta conta = new Conta();
            conta.setSenha(senha.getText());
            conta.setUsername(nomeUsuario.getText());            
            try {
            	 Conta autenticado = bo.autenticar(conta);
            	 if(autenticado instanceof Usuario) {
            		 Telas.listarPacientes(); 
            	 }
            	 else{
            		 Telas.listarMedicosAdmin();
            	 }
            }
            catch(AutenticationException e){
            	erroAutent.setText(e.getMessage());
            	erroAutent.setVisible(true);
            	botaoFechar.setVisible(true);
            	botaoFechar.setDisable(false);
            }
    }
    
    public void telaCadastro(ActionEvent action) {
    	Telas.cadastroUsuario();
    }
    
    public void fecharError(ActionEvent action) {
    	erroAutent.setVisible(false);
    	botaoFechar.setVisible(false);
    	botaoFechar.setDisable(true);
    }
}
