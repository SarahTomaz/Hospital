package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.api.dto.PacienteDTO;
import br.edu.ufersa.hospital.model.service.PacienteBO;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class EditarPacienteController {

	@FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField idade;
    private static String cpfEdit;
    private PacienteBO bo = new PacienteBO();
	
    public static void telaEditar(PacienteDTO pacDTO) {
    	Telas.telaEdicaoPaciente();
    	cpfEdit = pacDTO.getCpf();
    }
    
    public void editar() {
    	PacienteDTO dto = new PacienteDTO();
        dto.setNome(nome.getText());
        dto.setCpf(cpf.getText());
        dto.setEndereco(endereco.getText());
        dto.setIdade(Integer.parseInt(idade.getText()));	
        bo.atualizar(dto, cpfEdit);
        Telas.listarPacientesAdmin();
    }
    
	public void cancelar() {
		Telas.listarPacientesAdmin();
	}
	
}
