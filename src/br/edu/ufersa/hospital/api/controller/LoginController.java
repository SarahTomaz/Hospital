package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.model.entity.Conta;
import br.edu.ufersa.hospital.model.entity.Usuario;
import br.edu.ufersa.hospital.model.service.ContaBO;
import br.edu.ufersa.hospital.model.service.UsuarioBO;

//Fazer os import do JavaFX e das views quando implementar
public class LoginController {
	@FXML private TextField nomeUsuario;
    @FXML private PasswordField senha;
    @FXML private Label erroAutent;
    @FXML private Button botaoFechar;
    ContaBO<Conta> bo = new ContaBO<Conta>();
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
