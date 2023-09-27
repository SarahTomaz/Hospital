package br.edu.ufersa.hospital.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.AdministratorDTO;
import br.edu.ufersa.hospital.model.dao.AdministratorDAO;
import br.edu.ufersa.hospital.model.entity.Administrator;

public class AdministratorBO {
	
	AdministratorDAO dao = new AdministratorDAO();
public Administrator adicionar(AdministratorDTO AdministratorDTO) throws PasswordErrorException {
	    
	    Administrator admin = Administrator.converter(AdministratorDTO);
      
	    if(admin.getUsername().equals("")) {
	    	throw new PasswordErrorException();
	    }
	    
		ResultSet rs = dao.encontrarPorUsername(admin);
		try {
			if(rs==null || !(rs.next())) {
				if(dao.cadastrar(admin) == true)
					return admin;
					else throw new PasswordErrorException();
			}
			else throw new PasswordErrorException();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PasswordErrorException();
		}	
	} 
public Administrator autenticar(Administrator vo) throws AutenticationException{
	ResultSet rs =  dao.encontrarPorUsername(vo);
	try {
		if(rs.next()) {
			if(rs.getString("senha").equals(vo.getSenha())) {
				return vo;
			}
			else throw new AutenticationException();
		}
		else throw new AutenticationException();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AutenticationException();
	}
}
}
