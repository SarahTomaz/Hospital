package br.edu.ufersa.view;

import br.edu.ufersa.controller.TelaInicioGerente;
import br.edu.ufersa.controller.TelaMedicos;
import br.edu.ufersa.model.entity.Funcionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Telas extends Application
{
    private static Stage stage;


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

    public static void telaPrincipalGerente(Funcionario func) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/telaInicioGerente.fxml"));
        Parent root = loader.load();

        //Instancia o controller para setar o nome de quem está usando a tela atual
        TelaInicioGerente controller = loader.getController();
        controller.setFuncionario(func);

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

    public static void telaPacientes() throws IOException
    {

    }

    public static void telaProntuario() throws IOException
    {

    }

    public static void telaAgenda() throws IOException
    {

    }
}
