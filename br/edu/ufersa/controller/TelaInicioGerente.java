package br.edu.ufersa.controller;

import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TelaInicioGerente implements BotaoDeTroca
{
    @FXML public Label nomeUsu = new Label();
    @FXML private Button botaoMedicos;
    @FXML private Button botaoPacientes;
    @FXML private Button botaoProntuarios;
    @FXML private Button botaoAgenda;
    
    public void setFuncionario(Funcionario func)
    {
        this.nomeUsu.setText("Olá, Dr(a). " + func.getNome());
    }

    @Override
    public void mudarCorMed(MouseEvent mouseEvent)
    {
        botaoMedicos.setStyle("-fx-background-color: #00CED1;");
    }
    @Override
    public void voltarCorMed(MouseEvent mouseEvent)
    {
        botaoMedicos.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
    @Override
    public void irTelaMedicos(ActionEvent event)
    {
        try {
            Telas.telaFuncionario();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void mudarCorPac(MouseEvent mouseEvent)
    {
        botaoPacientes.setStyle("-fx-background-color: #00CED1;");
    }
    @Override
    public void voltarCorPac(MouseEvent mouseEvent)
    {
        botaoPacientes.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
    public void irTelaPacientes(ActionEvent event)
    {
        try {
            Telas.telaPacientes();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void mudarCorPro(MouseEvent mouseEvent)
    {
        botaoProntuarios.setStyle("-fx-background-color: #00CED1;");
    }
    @Override
    public void voltarCorPro(MouseEvent mouseEvent)
    {
        botaoProntuarios.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
    public void irTelaProntuario(ActionEvent event)
    {
        try {
            Telas.telaProntuario();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void mudarCorAg(MouseEvent mouseEvent)
    {
        botaoAgenda.setStyle("-fx-background-color: #00CED1;");
    }

    @Override
    public void voltarCorAg(MouseEvent mouseEvent)
    {
        botaoAgenda.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
    public void irTelaAgenda(ActionEvent event)
    {
        try {
            Telas.telaAgenda();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
