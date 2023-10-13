package br.edu.ufersa.controller.Pacientes;

import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TelaCriarPacientes
{
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField idade;
    @FXML private Label erroaut = new Label();

    public void inserir(ActionEvent actionEvent)
    {
        Paciente pac = new Paciente();
        PacienteBo pacBo = new PacienteBo();

        try {
            pac.setNome(nome.getText());
            pac.setCpf(cpf.getText());
            pac.setEndereco(endereco.getText());
            pac.setIdade(Integer.parseInt(idade.getText()));

            try {
                pacBo.criar(pac);

                try {
                    Telas.telaPacientes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                erroaut.setText("Esse Cpf já foi registrada");
                erroaut.setVisible(true);
            }
        }
        catch (Exception e)
        {
            erroaut.setText("Há pelo menos um campo inválido ou vazio");
            erroaut.setVisible(true);
        }
    }

    public void cancelar(ActionEvent actionEvent)
    {
        try {
            Telas.telaPacientes();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//____________________________________________________________________________________________________________________
    //Escolhas estilísticas

    @FXML
    Button realizarIns;
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
