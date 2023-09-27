package br.edu.ufersa.hospital.model.service;
import br.edu.ufersa.hospital.model.entity.Paciente;
import br.edu.ufersa.hospital.model.dao.PacienteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.hospital.api.dto.PacienteDTO;
import br.edu.ufersa.hospital.model.dao.BaseInterDAO;

public class PacienteBO {
    
    BaseInterDAO<Paciente> dao = new PacienteDAO();
    
    public boolean adicionar(PacienteDTO pacDTO) {
        
        Paciente pac = Paciente.converter(pacDTO);
        
        ResultSet rs = dao.encontrar(pac);
        try {
            if(rs==null || !(rs.next())) {
                if(dao.cadastrar(pac) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }   
    }
    
    public boolean atualizar(PacienteDTO pacDTO, String cpf) {
        
        Paciente pac = Paciente.converter(pacDTO);
        
        ResultSet rs = dao.encontrar(pac);
        dao.editar(pac, cpf);
        try {
            if(rs!=null || (rs.next())) {
                if(dao.editar(pac, cpf) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean apagar(PacienteDTO pacDTO) {  
    
        Paciente pac = Paciente.converter(pacDTO);
    
        ResultSet rs = dao.encontrar(pac);

        try {
            if(rs!=null || (rs.next())) {
                if(dao.excluirPorCPF(pac) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<PacienteDTO> listar() {

        List<PacienteDTO> pacientes = new ArrayList<PacienteDTO>();
        ResultSet rs = dao.listar();

        try {

            while(rs.next()) {
                PacienteDTO pac = new PacienteDTO();
                pac.setId(rs.getInt("Pacienteid"));
                pac.setNome(rs.getString("nome"));
                pac.setCpf(rs.getString("cpf"));
                pac.setIdade(rs.getInt("idade"));
                pac.setEndereco(rs.getString("endereco"));

                pacientes.add(pac);
            }
            return pacientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<PacienteDTO> listarPorCpf(PacienteDTO pacDTO) {

        List<PacienteDTO> listaPacientes = new ArrayList<PacienteDTO>();
        Paciente paciente = Paciente.converter(pacDTO);
        ResultSet rs = dao.encontrar(paciente);

        try {

            while(rs.next()) {
                PacienteDTO pac = new PacienteDTO();
                pac.setId(rs.getInt("Pacienteid"));
                pac.setNome(rs.getString("nome"));
                pac.setCpf(rs.getString("cpf"));
                pac.setIdade(rs.getInt("idade"));
                pac.setEndereco(rs.getString("endereco"));

                listaPacientes.add(pac);
            }
            return listaPacientes;
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<PacienteDTO> listarPorNome(PacienteDTO pacDTO) {

        List<PacienteDTO> listaPacientes = new ArrayList<PacienteDTO>();
        Paciente paciente = Paciente.converter(pacDTO);
        ResultSet rs = dao.encontrarPorNome(paciente);

        try {

            while(rs.next()) {
            	PacienteDTO pac = new PacienteDTO();
                pac.setId(rs.getInt("Pacienteid"));
                pac.setNome(rs.getString("nome"));
                pac.setCpf(rs.getString("cpf"));
                pac.setIdade(rs.getInt("idade"));
                pac.setEndereco(rs.getString("endereco"));

                listaPacientes.add(pac);
            }
            return listaPacientes;
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }

}
