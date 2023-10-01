package br.edu.ufersa.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface BotaoDeTroca
{
    void mudarCorMed(MouseEvent mouseEvent);
    void voltarCorMed(MouseEvent mouseEvent);
    void irTelaMedicos(ActionEvent event);

    void mudarCorPac(MouseEvent mouseEvent);
    void voltarCorPac(MouseEvent mouseEvent);

    void mudarCorPro(MouseEvent mouseEvent);
    void voltarCorPro(MouseEvent mouseEvent);

    void mudarCorAg(MouseEvent mouseEvent);
    void voltarCorAg(MouseEvent mouseEvent);
}
