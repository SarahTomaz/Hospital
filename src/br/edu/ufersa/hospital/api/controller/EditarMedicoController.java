package br.edu.ufersa.hospital.api.controller;

import br.edu.ufersa.hospital.api.dto.MedicoDTO;
import br.edu.ufersa.hospital.model.service.MedicoBO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class EditarMedicoController {

	@FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField codigoDoConselho;
    @FXML private TextField valorDaConsulta;
    private static String cpfEdit;
    private MedicoBO bo = new MedicoBO();
	
    public static void telaEditar(MedicoDTO medDTO) {
    	Telas.telaEdicaoMedico();
    	cpfEdit = medDTO.getCpf();
    }
    
    public void editar() {
    	MedicoDTO dto = new MedicoDTO();
        dto.setNome(nome.getText());
        dto.setCpf(cpf.getText());
        dto.setEndereco(endereco.getText());
        dto.setCodigoDoConselho(Integer.parseInt(codigoDoConselho.getText()));	
        dto.setValorDaConsulta(Double.parseDouble(valorDaConsulta.getText()));
        bo.atualizar(dto, cpfEdit);
        Telas.listarMedicosAdmin();
    }
    
	public void cancelar() {
		Telas.listarMedicosAdmin();
	}
	
}
