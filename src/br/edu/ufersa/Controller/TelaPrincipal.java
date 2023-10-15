package br.edu.ufersa.controller;

import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.view.Telas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TelaPrincipal extends BotaoDeTrocaImpl
{
    @FXML public Label nomeUsu = new Label();
    @FXML private Button botaoMedicos;
    @FXML private Button botaoPacientes;
    @FXML private Button botaoProntuarios;
    @FXML private Button botaoAgenda;
    @FXML private Button botaoLog;
    
    public void initialize()
    {
        this.nomeUsu.setText("Olá, Dr(a). " + Telas.user.getNome());
    }

    public void sair()
    {
        try
        {
            Telas.telaLogin();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
    public void mudarCorPac(MouseEvent mouseEvent)
    {
        botaoPacientes.setStyle("-fx-background-color: #00CED1;");
    }
    @Override
    public void voltarCorPac(MouseEvent mouseEvent)
    {
        botaoPacientes.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
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

    public void mudarCorLg(MouseEvent mouseEvent)
    {
        if (Telas.user.getGerente())
        {
            botaoLog.setStyle("-fx-background-color: #00CED1;");
        }
        else
        {
            botaoLog.setStyle("-fx-background-color: #fc1303; -fx-border-color: #2F4F4F;");
        }
    }
    public void voltarCorLg(MouseEvent mouseEvent)
    {
        botaoLog.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
}
