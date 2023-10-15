package br.edu.ufersa.controller.Log;

import br.edu.ufersa.DAO.BaseDaoImpl;
import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.controller.Interfaces.Tabelas;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.Bo.LogBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Log;
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

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TelaLog extends BotaoDeTrocaImpl implements Tabelas<Log>
{
    @FXML private TableView<Log> tabelaLog = new TableView<>();
    @FXML private TableColumn<Log, Date> colData = new TableColumn<>("Data");
    @FXML private TableColumn<Log, Time> colHora = new TableColumn<>("Hora");
    @FXML private TableColumn<Log, String> colUsu = new TableColumn<>("Usuario");
    @FXML private TableColumn<Log, String> colMod = new TableColumn<>("Modificação");
    @FXML private TableColumn<Log, String> colTab  = new TableColumn<>("Tabela");
    @FXML Label nomeUsu = new Label();

    public void initialize()
    {
        LogBo logBo = new LogBo();

        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colUsu.setCellValueFactory(new PropertyValueFactory<>("user"));
        colMod.setCellValueFactory(new PropertyValueFactory<>("modif"));
        colTab.setCellValueFactory(new PropertyValueFactory<>("tabela"));

        tabelaLog.getColumns().clear();
        tabelaLog.getColumns().add(colData);
        tabelaLog.getColumns().add(colHora);
        tabelaLog.getColumns().add(colUsu);
        tabelaLog.getColumns().add(colMod);
        tabelaLog.getColumns().add(colTab);

        try
        {
            List<Log> logList = logBo.listar();
            ObservableList<Log> obsList = FXCollections.observableArrayList();

            while (!logList.isEmpty())
            {
                obsList.add(logList.get(0));
                logList.remove(0);
            }

            tabelaLog.setItems(obsList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        nomeUsu.setText("Olá, Dr(a). " + Telas.user.getNome());
    }

    @Override
    public void updateTable(List<Log> list)
    {
        tabelaLog.getItems().clear();
        try
        {
            while (!list.isEmpty())
            {
                tabelaLog.getItems().add(list.get(0));
                list.remove(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stableTable()
    {
        tabelaLog.getItems().clear();

        LogBo logBo = new LogBo();
        try
        {
            List<Log> logList = logBo.listar();

            while(!logList.isEmpty())
            {
                tabelaLog.getItems().add(logList.get(0));
                logList.remove(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    TextField nomeBusca;

    @Override
    public void busca()
    {
        Log log = new Log();
        if (nomeBusca.getText() != null && !nomeBusca.getText().isEmpty())
        {
            try
            {
                log.setData(Date.valueOf(nomeBusca.getText()));
                log.setUser(nomeBusca.getText());
            }
            catch (CampoVazioException e)
            {
                System.out.println(e.getMessage());
            }

            LogBo logBo = new LogBo();
            List<Log> logData = null;
            try
            {
                logData = logBo.buscarPorData(log);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            List<Log> logUser = null;
            try
            {
                logUser = logBo.buscarPorUsuario(log);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                if (!logData.isEmpty())
                {
                    updateTable(logData);
                }
                else
                {
                    if (!logUser.isEmpty())
                    {
                        updateTable(logUser);
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
    }

    @Override
    public void deletaLinha() {}

    @Override
    public void deletaDel(KeyEvent keyEvent) {}

    @Override
    public void deletaBotao(MouseEvent mouseEvent) {}

    @Override
    public void editar(MouseEvent mouseEvent) {}

    @Override
    public void adicionar(MouseEvent mouseEvent) {}

    @Override
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

    @Override
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

//_____________________________________________________________________________________________________________________
    //Escolhas estilísticas
@FXML
private Button botaoMedicos;
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
    public void mudarCorAg(MouseEvent mouseEvent)
    {
        botaoAgenda.setStyle("-fx-background-color: #00CED1;");
    }

    @Override
    public void voltarCorAg(MouseEvent mouseEvent)
    {
        botaoAgenda.setStyle("-fx-background-color: #a9a9a9; -fx-border-color: #2F4F4F;");
    }


    @Override
    public void mudarCorLg(MouseEvent mouseEvent)
    {}
    @Override
    public void voltarCorLg(MouseEvent mouseEvent)
    {}
    public void irTelaLog(ActionEvent actionEvent){}
}