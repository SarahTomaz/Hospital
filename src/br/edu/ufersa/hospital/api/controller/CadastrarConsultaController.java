package br.edu.ufersa.hospital.api.controller;
import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.model.service.ConsultaBO;
import br.edu.ufersa.hospital.model.dao.MedicoDAO;
import br.edu.ufersa.hospital.model.dao.PacienteDAO;
import br.edu.ufersa.hospital.model.entity.Medico;
import br.edu.ufersa.hospital.model.entity.Paciente;

//Fazer os import do JavaFX e das views quando implementar
public class CadastrarConsultaController {
	
	@FXML private TextField cpfPaciente;
    @FXML private TextField cpfMedico;
    @FXML private TextField data;
    @FXML private TextField horario;
    @FXML private CheckBox addPront; 
    private ConsultaBO bo = new ConsultaBO();
    private PacienteDAO dao = new PacienteDAO();
    private MedicoDAO dao1 = new MedicoDAO();
    
    public void cadastrar() {
    	/*Paciente p = new Paciente();
    	Medico m = new Medico();
    	m.setCpf(cpfMedico.getText());
    	m = dao1.encontrarPorCpf(m);
    	p.setCpf(cpfPaciente.getText());
    	p = dao.encontrarPorCPF(p);*/
    	ConsultaDTO dto = new ConsultaDTO();
    	dto.setIdPaciente(Integer.parseInt(cpfPaciente.getText()));
    	dto.setIdMedico(Integer.parseInt(cpfMedico.getText()));
		//dto.setData(LocalDate.parse(data.getText()));
		//dto.setHorario(LocalTime.parse(horario.getText()));
		bo.adicionar(dto);
		Telas.listarConsultasAdmin();
    }
    
    public void cancelar() {
    	Telas.listarConsultasAdmin();
    }
    
    public void adicionarPront() {
    	
    }
}