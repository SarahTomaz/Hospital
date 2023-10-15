package br.edu.ufersa.controller.Interfaces;

import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public abstract class BotaoDeTrocaImpl implements BotaoDeTroca
{
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
    public void irTelaProntuarios(ActionEvent event)
    {
        try {
            Telas.telaProntuarios();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void irTelaAgendas(ActionEvent event)
    {
        try {
            Telas.telaAgendas();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void irTelaLog(ActionEvent actionEvent)
    {
        if (Telas.user.getGerente())
        {
            try
            {
                Telas.telaLog();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta de Autoridade");
            alert.setContentText("Você não possui as permissões necessárias para executar essa ação");
            alert.showAndWait();
        }
    }

//______________________________________________________________________________________________________________________

    @Override
    public abstract void mudarCorMed(MouseEvent mouseEvent);

    @Override
    public abstract void voltarCorMed(MouseEvent mouseEvent);


    @Override
    public abstract void mudarCorPac(MouseEvent mouseEvent);

    @Override
    public abstract void voltarCorPac(MouseEvent mouseEvent);


    @Override
    public abstract void mudarCorPro(MouseEvent mouseEvent);

    @Override
    public abstract void voltarCorPro(MouseEvent mouseEvent);


    @Override
    public abstract void mudarCorAg(MouseEvent mouseEvent);

    @Override
    public abstract void voltarCorAg(MouseEvent mouseEvent);


    public abstract void mudarCorLg(MouseEvent mouseEvent);

    public abstract void voltarCorLg(MouseEvent mouseEvent);
}
