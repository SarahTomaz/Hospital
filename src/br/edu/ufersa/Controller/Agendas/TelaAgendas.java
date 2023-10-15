package br.edu.ufersa.controller.Agendas;

import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.controller.Interfaces.Tabelas;
import br.edu.ufersa.model.Bo.ConsultasBo;
import br.edu.ufersa.model.entity.Consulta;
import br.edu.ufersa.model.entity.Prontuario;
import br.edu.ufersa.view.Telas;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class TelaAgendas extends BotaoDeTrocaImpl implements Tabelas<Consulta>
{

    @FXML
    private TableView<Consulta> tabelaConsulta = new TableView<>();
    @FXML private TableColumn colId = new TableColumn<Consulta, Integer>("Id");
    @FXML private TableColumn colMed = new TableColumn<Consulta, String>("Medico");
    @FXML private TableColumn colPac  = new TableColumn<Consulta, String>("Paciente");
    @FXML private TableColumn colData = new TableColumn<Consulta, Date>("Data_Consulta");
    @FXML private ChoiceBox<String> escolha;
    @FXML private Label nomeUsu = new Label();

    @Override
    public void initialize()
    {
        ConsultasBo conBo = new ConsultasBo();

        colId.setCellValueFactory(new PropertyValueFactory<Consulta, Integer>("id"));
        colMed.setCellValueFactory(new PropertyValueFactory<Consulta, String>("medico"));
        colPac.setCellValueFactory(new PropertyValueFactory<Consulta, String>("paciente"));
        colData.setCellValueFactory(new PropertyValueFactory<Consulta, Date>("data_consulta"));

        tabelaConsulta.getColumns().add(colId);
        tabelaConsulta.getColumns().add(colMed);
        tabelaConsulta.getColumns().add(colPac);
        tabelaConsulta.getColumns().add(colData);

        try
        {
            List<Consulta> conList = conBo.listar();

            while(!conList.isEmpty())
            {
                tabelaConsulta.getItems().add(conList.get(0));
                conList.remove(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        nomeUsu.setText("Olá, Dr(a). " + Telas.user.getNome());

        escolha.getItems().addAll("Paciente", "Médico", "Data");
        escolha.setValue("Paciente");
    }

    @Override
    public void updateTable(List<Consulta> list)
    {
        tabelaConsulta.getItems().clear();
        while (!list.isEmpty())
        {
            tabelaConsulta.getItems().add(list.get(0));
            list.remove(0);
        }
    }

    @Override
    public void stableTable()
    {
        tabelaConsulta.getItems().clear();
        ConsultasBo conBo = new ConsultasBo();

        try
        {
            List<Consulta> conList = conBo.listar();

            while (!conList.isEmpty())
            {
                tabelaConsulta.getItems().add(conList.get(0));
                conList.remove(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML TextField nomeBusca;

    @Override
    public void busca()
    {
        if (nomeBusca.getText() != null && !nomeBusca.getText().isEmpty())
        {
            Consulta con = new Consulta();
            ConsultasBo conBo = new ConsultasBo();

            if (escolha.getValue().equals("Paciente"))
            {
                try
                {
                    con.setPaciente(nomeBusca.getText());
                    List<Consulta> conPac = conBo.buscarPorNomeP(con);

                    if (!conPac.isEmpty())
                    {
                        updateTable(conPac);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (escolha.getValue().equals("Médico"))
            {
                try
                {
                    con.setMedico(nomeBusca.getText());
                    List<Consulta> conMed = conBo.buscarPorNomeM(con);

                    if (!conMed.isEmpty())
                    {
                        updateTable(conMed);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (escolha.getValue().equals("Data"))
            {
                try
                {
                    con.setData_consulta(Date.valueOf(nomeBusca.getText()));
                    List<Consulta> conData = conBo.buscarPorData(con);

                    if (!conData.isEmpty())
                    {
                        updateTable(conData);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
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
    }

    @Override
    public void deletaLinha()
    {
        ConsultasBo conBo = new ConsultasBo();

        try
        {
            conBo.deletar(tabelaConsulta.getSelectionModel().getSelectedItem());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tabelaConsulta.getItems().removeAll(tabelaConsulta.getSelectionModel().getSelectedItem());
        stableTable();
    }

    public void confirmDeletar()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta de confirmação de deleção");
        alert.setContentText("Tem certeza que deseja deletar a consulta n°" +
                tabelaConsulta.getSelectionModel().getSelectedItem().getId() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            deletaLinha();
        }
    }

    @Override
    public void deletaDel(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.DELETE) && tabelaConsulta.getSelectionModel().getSelectedItem() != null &&
                        (Telas.user.getNome().equals(tabelaConsulta.getSelectionModel().getSelectedItem().getMedico()) ||
                        Telas.user.getGerente()))
        {
            confirmDeletar();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta de Autoridade");
            alert.setContentText("Você não possui as permissões necessárias para executar essa ação");
            alert.showAndWait();
        }
    }

    @Override
    public void deletaBotao(MouseEvent mouseEvent)
    {
        if (tabelaConsulta.getSelectionModel().getSelectedItem() != null &&
                (Telas.user.getNome().equals(tabelaConsulta.getSelectionModel().getSelectedItem().getMedico()) ||
                        Telas.user.getGerente()))
        {
            confirmDeletar();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta de Autoridade");
            alert.setContentText("Você não possui as permissões necessárias para executar essa ação");
            alert.showAndWait();
        }
    }

    @Override
    public void editar(MouseEvent mouseEvent)
    {
        if (tabelaConsulta.getSelectionModel().getSelectedItem() != null &&
                Telas.user.getNome().equals(tabelaConsulta.getSelectionModel().getSelectedItem().getMedico()))
        {
            try
            {
                Consulta con = tabelaConsulta.getSelectionModel().getSelectedItem();
                Telas.telaAlterarAgendas(con);
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

    @Override
    public void adicionar(MouseEvent mouseEvent)
    {
        try
        {
            Telas.telaCriarAgendas();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sair(MouseEvent mouseEvent)
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

    public void retornar(MouseEvent mouseEvent)
    {
        try
        {
            Telas.telaPrincipal(Telas.user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//______________________________________________________________________________________________________________________
    //Escolhas estilísticas

    @FXML private Button botaoMedicos;
    @FXML private Button botaoPacientes;
    @FXML private Button botaoProntuarios;
    @FXML private Button botaoAgenda;
    @FXML private Button botaoLog;

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
    public void mudarCorAg(MouseEvent mouseEvent) {}
    @Override
    public void voltarCorAg(MouseEvent mouseEvent) {}


    @Override
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
    @Override
    public void voltarCorLg(MouseEvent mouseEvent)
    {
        botaoLog.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }
}
