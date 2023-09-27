package br.edu.ufersa.hospital.model.service;
import br.edu.ufersa.hospital.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.model.dao.*;

public class ConsultaBO {
  
    BaseInterDAO<Consulta> dao = new ConsultaDAO();

    public boolean adicionar(ConsultaDTO consDTO) {
        
        Consulta cons = Consulta.converter(consDTO);
      
        ResultSet rs = dao.BuscarPorId(cons);

        try {
            if(rs==null || !(rs.next())) {
                if(dao.cadastrar(cons) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }   
    } 
    
    public boolean atualizar(ConsultaDTO consDTO, int idPaciente) {
        
        Consulta cons = Consulta.converter(consDTO);
        
        ResultSet rs = dao.encontrar(cons);
        try {
            if(rs!=null || (rs.next())) {
                if(dao.editar(cons, idPaciente) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean apagar(ConsultaDTO consDTO) { 
        
        Consulta cons = Consulta.converter(consDTO);
        
        ResultSet rs = dao.encontrar(cons);
        try {
            if(rs!=null || (rs.next())) {
                if(dao.excluirPorId(cons) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ConsultaDTO> listar() {

        List<ConsultaDTO> listaConsultas = new ArrayList<ConsultaDTO>();
        ResultSet rs = dao.listar();

        try {

            while(rs.next()) {
                ConsultaDTO cons = new ConsultaDTO();
                cons.setpacienteid(rs.getInt("pacienteid"));
                cons.setMedicoid(rs.getInt("Medicoid"));
                //cons.getProntuario().setId(rs.getInt("idProntuario"));
                //cons.setId(rs.getInt("idConsulta"));
                //cons.setData(LocalDate.parse(rs.getDate("dia").toString()));
                //cons.setHorario(LocalTime.parse(rs.getTime("horario").toString()));
                listaConsultas.add(cons);
            }
            return listaConsultas;
        } catch (SQLException e) {
            
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ConsultaDTO> listarPorCpfPaciente(ConsultaDTO consDTO) {

        List<ConsultaDTO> listaConsultas = new ArrayList<ConsultaDTO>();
        Consulta consulta = Consulta.converter(consDTO);
        ResultSet rs = dao.encontrar(consulta);

        try {

            while(rs.next()) {
                ConsultaDTO cons = new ConsultaDTO();
                cons.setpacienteid(rs.getInt("pacienteid")); 
                cons.setMedicoid(rs.getInt("Medicoid"));
                //cons.getProntuario().setId(rs.getInt("idProntuario"));
                cons.setId(rs.getInt("idConsulta"));
                cons.setData(LocalDate.parse(rs.getDate("dia").toString()));
                cons.setHorario(LocalTime.parse(rs.getTime("horario").toString()));

                listaConsultas.add(cons);
            }
            return listaConsultas;
        } catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
    }

}
