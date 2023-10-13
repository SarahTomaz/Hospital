package br.edu.ufersa.controller.Prontuarios;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.Bo.ProntuarioBo;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;
import br.edu.ufersa.view.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TelaCriarProntuarios
{
    @FXML private ChoiceBox<String> cpf;
    @FXML private TextField data;
    @FXML private TextArea observacoes;
    @FXML private Label erroaut = new Label();

    public void initialize()
    {
        try
        {
            PacienteBo pacBo = new PacienteBo();
            List<Paciente> pacList = pacBo.listar();

            ObservableList<String> listaCpf = FXCollections.observableArrayList();

            while (!pacList.isEmpty())
            {
                listaCpf.add(pacList.get(0).getCpf());
                pacList.remove(0);
            }

            cpf.setItems(listaCpf);

            Date date = Date.valueOf(LocalDate.now());

            data.setText(date.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void inserir(ActionEvent actionEvent)
    {
        ProntuarioBo proBo = new ProntuarioBo();
        Prontuario pro = new Prontuario();

        try {
            try
            {
                pro.setP_Cpf(cpf.getValue());
                pro.setData(Date.valueOf(data.getText()));
                pro.setObservacoes(observacoes.getText());

                proBo.criar(pro);
                Telas.telaProntuarios();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (CampoVazioException e)
        {
            e.getMessage();
            erroaut.setText("Algum campo é inválido ou vazio");
            erroaut.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent)
    {
        try
        {
            Telas.telaProntuarios();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//______________________________________________________________________________________________________________________
    //Escolhas estilísticas

    @FXML Button realizarIns;
    @FXML Button cancelarIns;

    public void mudarCorAlt(MouseEvent mouseEvent)
    {
        realizarIns.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }
    public void voltarCorAlt(MouseEvent mouseEvent)
    {
        realizarIns.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }


    public void mudarCorCan(MouseEvent mouseEvent)
    {
        cancelarIns.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }
    public void voltarCorCan(MouseEvent mouseEvent)
    {
        cancelarIns.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }
}
