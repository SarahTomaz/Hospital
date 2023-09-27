package br.edu.ufersa.hospital.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.UsuarioDTO;
import br.edu.ufersa.hospital.model.dao.AdministratorDAO;
import br.edu.ufersa.hospital.model.dao.UsuarioDAO;
import br.edu.ufersa.hospital.model.entity.Usuario;

@SuppressWarnings("unused")
public class UsuarioBO{
	
	UsuarioDAO dao = new UsuarioDAO();
	public Usuario adicionar(UsuarioDTO userDTO) throws PasswordErrorException {
	    
	    Usuario user = Usuario.converter(userDTO);
	    if(user.getUsername().equals("")) {
	    	throw new PasswordErrorException();
	    }
		ResultSet rs = dao.encontrarPorUsername(user);
		try {
			if(rs==null || !(rs.next())) { // verifica se foi encontrado algum usuario com mesmo username
				if(dao.cadastrar(user) == true) {
					return user;
				} else throw new PasswordErrorException();
					
			}	else return null;
		}
		    catch (SQLException e) {
			e.printStackTrace();
			throw new PasswordErrorException();
		    }
		}	
	 
public Usuario autenticar(Usuario vo) throws AutenticationException{
	ResultSet rs =  dao.encontrarPorUsername(vo);
	try {
		if(rs.next()) {
			if(rs.getString("senha").equals(vo.getSenha())) {
				return vo;
			}
			else throw new AutenticationException();
		}
		else throw new AutenticationException();
	} catch (SQLException e){
		e.printStackTrace();
		throw new AutenticationException();
	}
}
}
