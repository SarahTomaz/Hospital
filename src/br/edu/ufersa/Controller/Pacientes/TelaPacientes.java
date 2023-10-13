package br.edu.ufersa.controller.Pacientes;

import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.controller.Interfaces.Tabelas;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.view.Telas;
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

public class TelaPacientes extends BotaoDeTrocaImpl implements Tabelas<Paciente>
{
    @FXML private TableView<Paciente> tabelaPaciente = new TableView<Paciente>();
    @FXML private TableColumn colCpf = new TableColumn<Paciente, String>("Cpf");
    @FXML private TableColumn colNome = new TableColumn<Paciente, String>("Nome");
    @FXML private TableColumn colEnd  = new TableColumn<Paciente, String>("Endereço");
    @FXML private TableColumn colIda = new TableColumn<Paciente, Integer>("Idade");

    @Override
    public void initialize()
    {
        PacienteBo pacBo = new PacienteBo();

        colCpf.setCellValueFactory(new PropertyValueFactory<Paciente, String>("cpf"));
        colNome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nome"));
        colEnd.setCellValueFactory(new PropertyValueFactory<Paciente, String>("endereco"));
        colIda.setCellValueFactory(new PropertyValueFactory<Paciente, String>("idade"));

        tabelaPaciente.getColumns().add(colCpf);
        tabelaPaciente.getColumns().add(colNome);
        tabelaPaciente.getColumns().add(colEnd);
        tabelaPaciente.getColumns().add(colIda);

        try
        {
            List<Paciente> pacList = pacBo.listar();

            while(!pacList.isEmpty())
            {
                tabelaPaciente.getItems().add(pacList.get(0));
                pacList.remove(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(List<Paciente> list)
    {
        tabelaPaciente.getItems().clear();
        while(!list.isEmpty())
        {
            tabelaPaciente.getItems().add(list.get(0));
            list.remove(0);
        }
    }

    @Override
    public void stableTable()
    {
        tabelaPaciente.getItems().clear();
        PacienteBo pacBo = new PacienteBo();

        try
        {
            List<Paciente> pacList = pacBo.listar();

            while(!pacList.isEmpty())
            {
                tabelaPaciente.getItems().add(pacList.get(0));
                pacList.remove(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML private TextField nomeBusca;
    @FXML private Label msgDel = new Label();

    @Override
    public void busca()
    {
        Paciente pac = new Paciente();
        if (nomeBusca.getText() != null && !nomeBusca.getText().isEmpty())
        {
            pac.setCpf(nomeBusca.getText());
            pac.setNome(nomeBusca.getText());

            PacienteBo pacBo = new PacienteBo();
            List<Paciente> pacCpf = null;
            try
            {
                pacCpf = pacBo.buscarPorCpf(pac);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            List<Paciente> pacNome = null;
            try
            {
                pacNome = pacBo.buscarPorNome(pac);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                if (!pacCpf.isEmpty())
                {
                    updateTable(pacCpf);
                }
                else
                {
                    if (!pacNome.isEmpty())
                    {
                        updateTable(pacNome);
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

    @Override
    public void buscaEnter(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
        {
            busca();
        }

        msgDel.setVisible(false);
    }

    @Override
    public void deletaLinha()
    {
        PacienteBo pacBo = new PacienteBo();

        try
        {
            pacBo.deletar(tabelaPaciente.getSelectionModel().getSelectedItem());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tabelaPaciente.getItems().removeAll(tabelaPaciente.getSelectionModel().getSelectedItem());
        stableTable();
    }

    public void confirmDeletar()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta de confirmação de deleção");
        alert.setContentText("Tem certeza que deseja deletar " +
                tabelaPaciente.getSelectionModel().getSelectedItem().getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            deletaLinha();
        }
    }

    @Override
    public void deletaDel(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.DELETE))
        {
            confirmDeletar();
        }
    }

    @Override
    public void deletaBotao(MouseEvent mouseEvent)
    {
        confirmDeletar();
    }

    @Override
    public void editar(MouseEvent mouseEvent)
    {
        try {
            Paciente pac = tabelaPaciente.getSelectionModel().getSelectedItem();
            Telas.telaAlterarPacientes(pac);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(MouseEvent mouseEvent)
    {
        try
        {
            Telas.telaCriarPacientes();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
//_________________________________________________________________________________________________________________
    //Escolhas Estilísticas

    @FXML private Button botaoMedicos;
    @FXML private Button botaoPacientes;
    @FXML private Button botaoProntuarios;
    @FXML private Button botaoAgenda;

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
    public void mudarCorPac(MouseEvent mouseEvent) {}
    @Override
    public void voltarCorPac(MouseEvent mouseEvent) {}
    @Override
    public void irTelaPacientes(ActionEvent actionEvent){}


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
