package br.edu.ufersa.controller.Interfaces;

import br.edu.ufersa.model.entity.Funcionario;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public interface Tabelas <O>
{
    void initialize();
    void updateTable(List<O> list);
    void stableTable();
    void busca();
    void buscaEnter(KeyEvent keyEvent);
    void deletaLinha();
    void deletaDel(KeyEvent keyEvent);
    void deletaBotao(MouseEvent mouseEvent);
    void editar(MouseEvent mouseEvent);
    void adicionar(MouseEvent mouseEvent);
    void sair(MouseEvent mouseEvent);
    void retornar(MouseEvent mouseEvent);
}
