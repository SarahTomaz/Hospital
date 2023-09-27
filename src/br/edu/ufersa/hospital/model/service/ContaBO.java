package br.edu.ufersa.hospital.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.ContaDTO;
import br.edu.ufersa.hospital.model.dao.AdmDAO;
import br.edu.ufersa.hospital.model.dao.ContaDAO;
import br.edu.ufersa.hospital.model.dao.UsuarioDAO;
import br.edu.ufersa.hospital.model.entity.Adm;
import br.edu.ufersa.hospital.model.entity.Conta;
import br.edu.ufersa.hospital.model.entity.Usuario;

public class ContaBO<e extends Conta> {
	UsuarioDAO usuDao = new UsuarioDAO();
	AdmDAO adminDao = new AdmDAO();
	ContaDAO<Conta> contaDao = new ContaDAO<Conta>();
public Conta adicionar(ContaDTO contaDTO) throws PasswordErrorException {
	    
	    Conta conta = Conta.converter(contaDTO);
      
		ResultSet rs = contaDao.encontrarPorUsername(conta);
		try {
			if(rs==null || !(rs.next())) {
				if(contaDao.cadastrar(conta) == true)
					return conta;
					else throw new PasswordErrorException();
			}
			else throw new PasswordErrorException();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new PasswordErrorException();
		}	
	} 
public Conta autenticar(e vo) throws AutenticationException{
	ResultSet rs =  contaDao.encontrarPorUsername(vo);
	try {
		if(rs.next()) // existe uma conta com esse username
			{
			if(rs.getString("senha").equals(vo.getSenha())) { // existe uma conta com essa senha
				Adm admin = new Adm();
				admin.setUsername(rs.getString("username"));
				ResultSet admRS = adminDao.encontrarPorUsername(admin);
				if(admRS.next()) {
					admin.setSenha(rs.getString("senha"));
					return admin;
				}
				else {
					Usuario usu = new Usuario();
					usu.setUsername(rs.getString("username"));
					usu.setSenha(rs.getString("senha"));
					return usu;
				}
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
