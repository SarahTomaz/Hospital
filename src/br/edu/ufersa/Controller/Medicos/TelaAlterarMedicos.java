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

public class TelaAlterarMedicos
{

    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField crm;
    @FXML private TextField endereco;
    @FXML private TextField senha;
    @FXML private TextField salario;
    @FXML private CheckBox gerente;

    public void initialize(Funcionario func)
    {
        try {
            nome.setText(func.getNome());
            cpf.setText(func.getCpf());
            crm.setText(func.getCrm());
            endereco.setText(func.getEndereco());
            senha.setText(func.getSenha());
            salario.setText(String.valueOf(func.getSalario()));
            if (func.getGerente()) {
                gerente.setSelected(true);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML Label erroaut = new Label();

    @FXML Button realizarAlt;
    public void autenticar(ActionEvent actionEvent)
    {
        Funcionario func = new Funcionario();
        FuncionarioBo funcBo = new FuncionarioBo();

        try
        {
            func.setNome(nome.getText());
            func.setCpf(cpf.getText());
            func.setCrm(crm.getText());
            func.setEndereco(endereco.getText());
            func.setSenha(senha.getText());
            func.setSalario(Double.parseDouble(salario.getText()));
            func.setGerente(gerente.isSelected());

            funcBo.alterar(func);

            Telas.telaFuncionario();
        }
        catch (CampoVazioException e)
        {
            erroaut.setText("Nenhum campo pode ser vazio");
            erroaut.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML Button cancelarAlt;
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

    public void mudarCorAlt(MouseEvent mouseEvent)
    {
        realizarAlt.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }

    public void voltarCorAlt(MouseEvent mouseEvent)
    {
        realizarAlt.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }

    public void mudarCorCan(MouseEvent mouseEvent)
    {
        cancelarAlt.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }

    public void voltarCorCan(MouseEvent mouseEvent)
    {
        cancelarAlt.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }
}
