package br.edu.ufersa.controller.Interfaces;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface BotaoDeTroca
{
    void mudarCorMed(MouseEvent mouseEvent);
    void voltarCorMed(MouseEvent mouseEvent);
    void irTelaMedicos(ActionEvent event);

    void mudarCorPac(MouseEvent mouseEvent);
    void voltarCorPac(MouseEvent mouseEvent);
    void irTelaPacientes(ActionEvent event);


    void mudarCorPro(MouseEvent mouseEvent);
    void voltarCorPro(MouseEvent mouseEvent);
    void irTelaProntuarios(ActionEvent event);


    void mudarCorAg(MouseEvent mouseEvent);
    void voltarCorAg(MouseEvent mouseEvent);
    void irTelaAgendas(ActionEvent event);

    void mudarCorLg(MouseEvent mouseEvent);
    void voltarCorLg(MouseEvent mouseEvent);
    void irTelaLog(ActionEvent actionEvent);
}
