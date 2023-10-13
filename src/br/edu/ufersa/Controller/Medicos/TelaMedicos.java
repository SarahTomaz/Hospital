package br.edu.ufersa.controller.Medicos;

import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.controller.Interfaces.Tabelas;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.view.Telas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TelaMedicos extends BotaoDeTrocaImpl implements Tabelas<Funcionario>
{
    @FXML private TableView<Funcionario> tabelaFuncionario = new TableView<Funcionario>();
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

        try {
            List<Funcionario> funcList = funcBo.listar();

            while(!funcList.isEmpty()) {
                tabelaFuncionario.getItems().add(funcList.get(0));
                funcList.remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTable(List<Funcionario> list)
    {
        tabelaFuncionario.getItems().clear();
        while(!list.isEmpty())
        {
            tabelaFuncionario.getItems().add(list.get(0));
            list.remove(0);
        }
    }

    public void stableTable()
    {
        tabelaFuncionario.getItems().clear();

        FuncionarioBo funcBo = new FuncionarioBo();
        try
        {
            List<Funcionario> funcList = funcBo.listar();

            while(!funcList.isEmpty())
            {
                tabelaFuncionario.getItems().add(funcList.get(0));
                funcList.remove(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML private TextField nomeBusca;
    public void busca()
    {
        Funcionario func = new Funcionario();
        if (nomeBusca.getText() != null && !nomeBusca.getText().isEmpty())
        {
            try
            {
                func.setCrm(nomeBusca.getText());
                func.setNome(nomeBusca.getText());
            }
            catch (CampoVazioException e)
            {
                System.out.println(e.getMessage());
            }

            FuncionarioBo funcBo = new FuncionarioBo();
            List<Funcionario> funcCrm = null;
            try
            {
                funcCrm = funcBo.buscarPorCrm(func);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            List<Funcionario> funcNome = null;
            try
            {
                funcNome = funcBo.buscarPorNome(func);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                if (!funcCrm.isEmpty())
                {
                    updateTable(funcCrm);
                }
                else
                {
                    if (!funcNome.isEmpty())
                    {
                        updateTable(funcNome);
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        else
        {
            stableTable();
        }
    }

    @FXML private Label msgDel = new Label();

    public void buscaEnter(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            busca();
        }

        msgDel.setVisible(false);
    }

    public void deletaLinha()
    {
        FuncionarioBo funcBo = new FuncionarioBo();

        this.msgDel.setText("Será necessário realocar as consultas do(a) Dr(a)." + (tabelaFuncionario.getSelectionModel().getSelectedItem()).getNome());
        this.msgDel.setVisible(true);

        try {
            funcBo.deletar(tabelaFuncionario.getSelectionModel().getSelectedItem());
        }
        catch (CampoVazioException campoVazioException)
        {
            this.msgDel.setText(campoVazioException.getMessage());
        }

        tabelaFuncionario.getItems().removeAll(tabelaFuncionario.getSelectionModel().getSelectedItem());
        stableTable();
    }

    public void confirmDeletar()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta de confirmação de deleção");
        alert.setContentText("Tem certeza que deseja deletar Dr(a)." +
                tabelaFuncionario.getSelectionModel().getSelectedItem().getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            deletaLinha();
        }
    }

    public void deletaDel(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.DELETE))
        {
            confirmDeletar();

            msgDel.setVisible(true);
        }
    }

    public void deletaBotao(MouseEvent mouseEvent)
    {
        if (tabelaFuncionario.getSelectionModel().getSelectedItem() != null)
        {
            confirmDeletar();

            msgDel.setVisible(true);
        }
    }

    public void editar(MouseEvent mouseEvent)
    {
        if (tabelaFuncionario.getSelectionModel().getSelectedItem() != null)
        {
            try {
                Funcionario func = tabelaFuncionario.getSelectionModel().getSelectedItem();
                Telas.telaAlterarMedicos(func);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionar(MouseEvent mouseEvent)
    {
        try
        {
            Telas.telaCriarMedicos();
        }
        catch(IOException e)
        {
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
}
