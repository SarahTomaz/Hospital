package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.api.dto.PacienteDTO;
import br.edu.ufersa.hospital.model.service.PacienteBO;
import br.edu.ufersa.hospital.view.Telas;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class CadastrarPacienteController {
    
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField idade;
    private PacienteBO bo = new PacienteBO();
    
    public void cadastrar() {
        PacienteDTO dto = new PacienteDTO();
        dto.setNome(nome.getText());
        dto.setCpf(cpf.getText());
        dto.setEndereco(endereco.getText());
        dto.setIdade(Integer.parseInt(idade.getText()));
        bo.adicionar(dto);
    }
    
    public void cancelar() {
    }
    
}