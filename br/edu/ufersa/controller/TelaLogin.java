package br.edu.ufersa.controller;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaLogin
{
    @FXML private TextField login;
    @FXML private TextField senha;
    @FXML private Label erroaut = new Label();

    FuncionarioBo funcBO = new FuncionarioBo();
    public void autenticar(ActionEvent event)
    {
        Funcionario func = new Funcionario();

        try
        {
            func.setCrm(login.getText());
            func.setSenha(senha.getText());
        }
        catch (CampoVazioException e)
        {
            this.erroaut.setText("Usuário ou senha não podem ser vazios");
            this.erroaut.setVisible(true);
        }
        try
        {
            Funcionario autenticado = funcBO.autenticar(func);
            if(autenticado.getGerente())
            {
                Telas.telaPrincipalGerente(autenticado);
            }
            else
            {
                //TODO
                Telas.telaPrincipalGerente(autenticado);
            }
        }
        catch(Exception e)
        {
            this.erroaut.setText("Usuário ou senha inválidos");
            this.erroaut.setVisible(true);
            e.printStackTrace();
        }
    }
}
