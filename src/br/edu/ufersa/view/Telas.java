package br.edu.ufersa.view;

import br.edu.ufersa.controller.Agendas.TelaAlterarAgendas;
import br.edu.ufersa.controller.Agendas.TelaCriarAgendas;
import br.edu.ufersa.controller.Log.TelaLog;
import br.edu.ufersa.controller.Medicos.TelaAlterarMedicos;
import br.edu.ufersa.controller.Pacientes.TelaAlterarPacientes;
import br.edu.ufersa.controller.Prontuarios.TelaAlterarProntuarios;
import br.edu.ufersa.controller.Prontuarios.TelaCriarProntuarios;
import br.edu.ufersa.controller.TelaPrincipal;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;
import br.edu.ufersa.model.entity.Consulta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Telas extends Application
{
    private static Stage stage;
    public static Funcionario user = null;


    public void setStage(Stage stage)
    {
        Telas.stage = stage;
    }
    public Stage getStage()
    {return stage;}

    public static void main (String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        setStage(stage);
        stage.setTitle("Sistema Med+");
        stage.show();
        telaLogin();
    }

    public static void telaLogin() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaLogin.fxml"));
        Parent root = loader.load();

        Scene login = new Scene(root);
        stage.setScene(login);
    }

    public static void telaPrincipal(Funcionario func) throws Exception
    {
        user = func;

        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaInicio.fxml"));
        Parent root = loader.load();

        TelaPrincipal controller = loader.getController();
        controller.initialize();

        Scene inicioGerente = new Scene(root);
        stage.setScene(inicioGerente);
    }

    public static void telaFuncionario() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaMedicos.fxml"));
        Parent root = loader.load();

        Scene telaFuncionario = new Scene(root);
        stage.setScene(telaFuncionario);
    }

    public static void telaAlterarMedicos(Funcionario func) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaAlterarMedicos.fxml"));
        Parent root = loader.load();

        TelaAlterarMedicos controller = loader.getController();
        controller.initialize(func);

        Scene telaAlterarMedicos = new Scene(root);
        stage.setScene(telaAlterarMedicos);
    }

    public static void telaCriarMedicos() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaCriarMedicos.fxml"));
        Parent root = loader.load();

        Scene telaCriarMedicos = new Scene(root);
        stage.setScene(telaCriarMedicos);
    }

    public static void telaPacientes() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaPacientes.fxml"));
        Parent root = loader.load();

        Scene telaPacientes = new Scene(root);
        stage.setScene(telaPacientes);
    }

    public static void telaAlterarPacientes(Paciente pac) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaAlterarPacientes.fxml"));
        Parent root = loader.load();

        TelaAlterarPacientes controller = loader.getController();
        controller.initialize(pac);

        Scene telaAlterarPacientes = new Scene(root);
        stage.setScene(telaAlterarPacientes);
    }

    public static void telaCriarPacientes() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaCriarPacientes.fxml"));
        Parent root = loader.load();

        Scene telaCriarPacientes = new Scene(root);
        stage.setScene(telaCriarPacientes);
    }

    public static void telaProntuarios() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaProntuarios.fxml"));
        Parent root = loader.load();

        Scene telaProntuarios = new Scene(root);
        stage.setScene(telaProntuarios);
    }

    public static void telaAlterarProntuarios(Prontuario pro) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaAlterarProntuarios.fxml"));
        Parent root = loader.load();

        TelaAlterarProntuarios controller = loader.getController();
        controller.initialize(pro);

        Scene telaAlterarProntuarios = new Scene(root);
        stage.setScene(telaAlterarProntuarios);
    }

    public static void telaCriarProntuarios() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaCriarProntuarios.fxml"));
        Parent root = loader.load();

        TelaCriarProntuarios controller = loader.getController();
        controller.initialize();

        Scene telaCriarProntuarios = new Scene(root);
        stage.setScene(telaCriarProntuarios);
    }

    public static void telaAgendas() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaAgendas.fxml"));
        Parent root = loader.load();

        Scene telaAgendas = new Scene(root);
        stage.setScene(telaAgendas);
    }

    public static void telaAlterarAgendas(Consulta con) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaAlterarAgendas.fxml"));
        Parent root = loader.load();


        TelaAlterarAgendas controller = loader.getController();
        controller.initialize(con);


        Scene telaAlterarAgendas = new Scene(root);
        stage.setScene(telaAlterarAgendas);
    }

    public static void telaCriarAgendas() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaCriarAgendas.fxml"));
        Parent root = loader.load();

        TelaCriarAgendas controller = loader.getController();
        controller.initialize();

        Scene telaCriarAgendas = new Scene(root);
        stage.setScene(telaCriarAgendas);
    }

    public static void telaLog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaLog.fxml"));
        Parent root = loader.load();

        TelaLog controller = loader.getController();
        controller.initialize();

        Scene telaLog = new Scene(root);
        stage.setScene(telaLog);
    }
}
