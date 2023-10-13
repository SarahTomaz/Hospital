package br.edu.ufersa.controller.Medicos;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TelaCriarMedicos
{
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField crm;
    @FXML private TextField endereco;
    @FXML private TextField senha;
    @FXML private TextField salario;
    @FXML private CheckBox gerente;
    @FXML Label erroaut = new Label();

    public void inserir(ActionEvent actionEvent)
    {
        Funcionario func = new Funcionario();
        FuncionarioBo funcBo = new FuncionarioBo();

        try {
            func.setNome(nome.getText());
            func.setCpf(cpf.getText());
            func.setCrm(crm.getText());
            func.setEndereco(endereco.getText());
            func.setSenha(senha.getText());
            func.setSalario(Double.parseDouble(salario.getText()));
            func.setGerente(gerente.isSelected());

            try {
                funcBo.criar(func);

                try {
                    Telas.telaFuncionario();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                erroaut.setText("Essa Crm já foi registrada");
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
            Telas.telaFuncionario();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //_______________________________________________________________________________________________
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
