package br.edu.ufersa.controller.Interfaces;

import br.edu.ufersa.model.entity.Funcionario;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public interface Tabelas <O>
{
    public void initialize();
    public void updateTable(List<O> list);
    public void stableTable();
    public void busca();
    public void buscaEnter(KeyEvent keyEvent);
    public void deletaLinha();
    public void deletaDel(KeyEvent keyEvent);
    public void deletaBotao(MouseEvent mouseEvent);
    public void editar(MouseEvent mouseEvent);
    public void adicionar(MouseEvent mouseEvent);
}
