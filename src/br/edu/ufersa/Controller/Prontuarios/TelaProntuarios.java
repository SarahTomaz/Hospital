package br.edu.ufersa.controller.Prontuarios;

import br.edu.ufersa.controller.Interfaces.BotaoDeTrocaImpl;
import br.edu.ufersa.controller.Interfaces.Tabelas;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.Bo.ProntuarioBo;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;
import br.edu.ufersa.view.Telas;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TelaProntuarios extends BotaoDeTrocaImpl implements Tabelas<Prontuario>
{
    @FXML
    private TableView<Prontuario> tabelaProntuario = new TableView<>();
    @FXML private TableColumn colId = new TableColumn<Prontuario, Integer>("Id");
    @FXML private TableColumn colCpf = new TableColumn<Prontuario, String>("Cpf do Paciente");
    @FXML private TableColumn colData  = new TableColumn<Prontuario, Date>("Data");
    @FXML private TableColumn colObs = new TableColumn<Prontuario, String>("Observações");
    @FXML private ChoiceBox<String> escolha;
    @FXML private Label nomeUsu = new Label();

    @Override
    public void initialize()
    {
        ProntuarioBo proBo = new ProntuarioBo();

        colId.setCellValueFactory(new PropertyValueFactory<Prontuario, Integer>("id"));
        colCpf.setCellValueFactory(new PropertyValueFactory<Prontuario, String>("p_Cpf"));
        colData.setCellValueFactory(new PropertyValueFactory<Prontuario, Date>("data"));
        colObs.setCellValueFactory(new PropertyValueFactory<Prontuario, String>("observacoes"));

        tabelaProntuario.getColumns().clear();
        tabelaProntuario.getColumns().add(colId);
        tabelaProntuario.getColumns().add(colCpf);
        tabelaProntuario.getColumns().add(colData);
        tabelaProntuario.getColumns().add(colObs);

        try
        {
            List<Prontuario> proList = proBo.listar();

            while(!proList.isEmpty())
            {
                tabelaProntuario.getItems().add(proList.get(0));
                proList.remove(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        nomeUsu.setText("Olá, Dr(a). " + Telas.user.getNome());

        escolha.getItems().addAll("Id", "Cpf");
        escolha.setValue("Id");
    }

    @Override
    public void updateTable(List<Prontuario> list)
    {
        tabelaProntuario.getItems().clear();
        while(!list.isEmpty())
        {
            tabelaProntuario.getItems().add(list.get(0));
            list.remove(0);
        }
    }

    @Override
    public void stableTable()
    {
        tabelaProntuario.getItems().clear();
        ProntuarioBo proBo = new ProntuarioBo();

        try
        {
            List<Prontuario> proList = proBo.listar();

            while(!proList.isEmpty())
            {
                tabelaProntuario.getItems().add(proList.get(0));
                proList.remove(0);
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
        Prontuario pro = new Prontuario();
        if (nomeBusca.getText() != null && !nomeBusca.getText().isEmpty())
        {
            ProntuarioBo proBo = new ProntuarioBo();

            if (escolha.getValue().equals("Id"))
            {
                pro.setId(Long.valueOf(nomeBusca.getText()));

                List<Prontuario> proId = null;
                try
                {
                    proId = proBo.buscarPorId(pro);
                }
                catch (CampoVazioException e)
                {
                    System.out.println(e.getMessage());
                }

                try
                {
                    if (!proId.isEmpty())
                    {
                        updateTable(proId);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (escolha.getValue().equals("Cpf"))
            {
                pro.setP_Cpf(nomeBusca.getText());

                List<Prontuario> proP_Cpf = null;
                try
                {
                    proP_Cpf = proBo.buscarPorP_Cpf(pro);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }

                try
                {
                    if (!proP_Cpf.isEmpty())
                    {
                        updateTable(proP_Cpf);
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
        ProntuarioBo proBo = new ProntuarioBo();

        try
        {
            proBo.deletar(tabelaProntuario.getSelectionModel().getSelectedItem());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tabelaProntuario.getItems().removeAll(tabelaProntuario.getSelectionModel().getSelectedItem());
        stableTable();
    }

    public void confirmDeletar()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta de confirmação de deleção");
        alert.setContentText("Tem certeza que deseja deletar o prontuário n°" +
                tabelaProntuario.getSelectionModel().getSelectedItem().getId() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deletaLinha();
        }
    }

    @Override
    public void deletaDel(KeyEvent keyEvent)
    {
        if (keyEvent.getCode().equals(KeyCode.DELETE) && tabelaProntuario.getSelectionModel().getSelectedItem() != null)
        {
            confirmDeletar();
        }
    }

    @Override
    public void deletaBotao(MouseEvent mouseEvent)
    {
        if (tabelaProntuario.getSelectionModel().getSelectedItem() != null)
        {
            confirmDeletar();
        }
    }

    @Override
    public void editar(MouseEvent mouseEvent)
    {
        if (tabelaProntuario.getSelectionModel().getSelectedItem() != null)
        {
            try {
                Prontuario pro = tabelaProntuario.getSelectionModel().getSelectedItem();
                Telas.telaAlterarProntuarios(pro);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void adicionar(MouseEvent mouseEvent)
    {
        try
        {
            Telas.telaCriarProntuarios();
        }
        catch(IOException e)
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
    public void mudarCorPro(MouseEvent mouseEvent) {}
    @Override
    public void voltarCorPro(MouseEvent mouseEvent) {}


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

