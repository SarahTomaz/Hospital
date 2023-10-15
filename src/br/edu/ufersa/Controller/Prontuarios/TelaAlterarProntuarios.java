package br.edu.ufersa.controller.Prontuarios;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.Bo.ProntuarioBo;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;
import br.edu.ufersa.view.Telas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class TelaAlterarProntuarios
{
    @FXML private ChoiceBox<String> cpf;
    @FXML private DatePicker data;
    @FXML private TextArea observacoes;
    @FXML private Label erroaut = new Label();
    @FXML private Label nomePaciente = new Label();
    private Long id = null;
    private Date d = null;

    public void initialize(Prontuario pro)
    {
        try
        {
            this.id = pro.getId();
            d = pro.getData();
            data.setValue(pro.getData().toLocalDate());
            observacoes.setText(pro.getObservacoes());
            cpf.setValue(pro.getP_Cpf());

            ProntuarioBo proBo = new ProntuarioBo();
            List<Prontuario> proList = proBo.buscarPorId(pro);

            cpf.getItems().add(proList.get(0).getP_Cpf());
            proList.remove(0);

            cpf.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
                {
                    PacienteBo pacBo = new PacienteBo();
                    Paciente pac = new Paciente();
                    pac.setCpf(t1);
                    List<Paciente> pacList = pacBo.buscarPorCpf(pac);
                    nomePaciente.setText(pacList.get(0).getNome());
                }
            });
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
            pro.setId(id);
            pro.setP_Cpf(cpf.getValue());
            pro.setData(Date.valueOf(data.getValue()));
            pro.setObservacoes(observacoes.getText());

            if (pro.getData().compareTo(Date.valueOf(LocalDate.now())) <= 0 ||
                    pro.getData().compareTo(d) == 0)
            {
                proBo.alterar(pro);

                Telas.telaProntuarios();
            }
            else
            {
                erroaut.setText("Um prontuário não pode ter sua data alterada para o futuro");
                erroaut.setVisible(true);
            }
        }
        catch (CampoVazioException e)
        {
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

//_____________________________________________________________________________________________________________________
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
