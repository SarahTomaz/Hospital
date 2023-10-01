package br.edu.ufersa.controller;

import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TelaMedicos implements BotaoDeTroca
{
    @FXML private TableView tabelaFuncionario = new TableView<Funcionario>();
    @FXML private TableColumn colCrm = new TableColumn<Funcionario, String>("Crm");
    @FXML private TableColumn colCpf = new TableColumn<Funcionario, String>("Cpf");
    @FXML private TableColumn colNome = new TableColumn<Funcionario, String>("Nome");
    @FXML private TableColumn colEnd  = new TableColumn<Funcionario, String>("Endereço");
    @FXML private TableColumn colSal = new TableColumn<Funcionario, Double>("Salario");

    ObservableList<Funcionario> lista = FXCollections.observableArrayList();

    public void initialize() {
        FuncionarioBo funcBo = new FuncionarioBo();

        colCrm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("crm"));

        colCpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));

        colNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));

        colEnd.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("endereco"));

        colSal.setCellValueFactory(new PropertyValueFactory<Funcionario, Double>("salario"));

        tabelaFuncionario.getColumns().add(colCrm);
        tabelaFuncionario.getColumns().add(colCpf);
        tabelaFuncionario.getColumns().add(colNome);
        tabelaFuncionario.getColumns().add(colEnd);
        tabelaFuncionario.getColumns().add(colSal);

        List<Funcionario> funcList = null;
        try {
            funcList = funcBo.listar();

            while(!funcList.isEmpty()) {
                tabelaFuncionario.getItems().add(funcList.get(0));
                funcList.remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//Menu Lateral_______________________________________
    @FXML private Button botaoMedicos;
    @FXML private Button botaoPacientes;
    @FXML private Button botaoProntuarios;
    @FXML private Button botaoAgenda;

    @Override
    public void mudarCorMed(MouseEvent mouseEvent) {}
    @Override
    public void voltarCorMed(MouseEvent mouseEvent) {}
    @Override
    public void irTelaMedicos(ActionEvent event) {}

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
