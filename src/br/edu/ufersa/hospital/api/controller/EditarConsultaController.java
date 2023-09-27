package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.api.dto.MedicoDTO;
import br.edu.ufersa.hospital.model.service.ConsultaBO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditarConsultaController {

	@FXML private TextField idPaciente;
    @FXML private TextField idMedico;
    private static int idEdit;
    private ConsultaBO bo = new ConsultaBO();
	
    public static void telaEditar(ConsultaDTO consDTO) {
    	Telas.telaEdicaoConsulta();
    	idEdit = consDTO.getIdPaciente();
    }
    
    public void editar() {
    	ConsultaDTO dto = new ConsultaDTO();
        dto.setIdPaciente(Integer.parseInt(idPaciente.getText()));
        dto.setIdMedico(Integer.parseInt(idMedico.getText()));
        bo.atualizar(dto, idEdit);
        Telas.listarConsultasAdmin();
    }
    
	public void cancelar() {
		Telas.listarConsultasAdmin();
	}
    
}
