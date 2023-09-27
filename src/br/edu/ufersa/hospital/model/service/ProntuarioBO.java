package br.edu.ufersa.hospital.model.service;
import br.edu.ufersa.hospital.model.entity.Prontuario;
import br.edu.ufersa.hospital.model.dao.BaseInterDAO;
import br.edu.ufersa.hospital.model.dao.ProntuarioDAO;
import br.edu.ufersa.hospital.api.dto.ProntuarioDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioBO {
    
    BaseInterDAO<Prontuario> dao = new ProntuarioDAO();
    
    public boolean adicionar(ProntuarioDTO prontDTO) {
        
        Prontuario pront = Prontuario.converter(prontDTO);
      
        ResultSet rs = dao.encontrar(pront);
        try {
            if(rs==null || !(rs.next())) {
                if(dao.cadastrar(pront) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }   
    } 
    
    public boolean atualizar(ProntuarioDTO prontDTO, String valor) {
        
        Prontuario pront = Prontuario.converter(prontDTO);
        
        ResultSet rs = dao.encontrar(pront);
        try {
            if(rs==null || !(rs.next())) {
                if(dao.editar(pront, valor) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean apagar(ProntuarioDTO prontDTO) {  
        
        Prontuario pront = Prontuario.converter(prontDTO);
        
        ResultSet rs = dao.encontrar(pront);
        try {
            if(rs==null || !(rs.next())) {
                if(dao.excluirPorId(pront) == true)
                    return true;
                    else return false;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Prontuario> listar() {

        List<Prontuario> listaProntuarios = new ArrayList<Prontuario>();
        ResultSet rs = dao.listar();

        try {

            while(rs.next()) {
                Prontuario pront = new Prontuario();
                pront.setId(rs.getInt("idProntuario"));
                pront.getPaciente().setId(rs.getInt("idPaciente")); 
                pront.setObs(rs.getString("obs"));
                pront.setData(LocalDate.parse(rs.getDate("momento").toString()));
                pront.setHorario(LocalTime.parse(rs.getTime("horario").toString()));
                listaProntuarios.add(pront);
            }
            return listaProntuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
