package br.edu.ufersa.controller;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TelaLogin
{
    @FXML private TextField login;
    @FXML private PasswordField senha;
    @FXML private Label erroaut = new Label();

    FuncionarioBo funcBO = new FuncionarioBo();
    public void autenticar(ActionEvent actionEvent)
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
            if(autenticado.isValid())
            {
                Telas.telaPrincipal(autenticado);
            }
        }
        catch(Exception e)
        {
            this.erroaut.setText("Usuário ou senha inválidos");
            this.erroaut.setVisible(true);
            e.printStackTrace();
        }
    }

    public void autenticarEnter(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            autenticar(new ActionEvent());
        }
    }
}
