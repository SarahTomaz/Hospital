package br.edu.ufersa.hospital.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.Exception.AutenticationException;
import br.edu.ufersa.hospital.Exception.PasswordErrorException;
import br.edu.ufersa.hospital.api.dto.AccountDTO;
import br.edu.ufersa.hospital.model.dao.AdministratorDAO;
import br.edu.ufersa.hospital.model.dao.AccountDAO;
import br.edu.ufersa.hospital.model.dao.UsuarioDAO;
import br.edu.ufersa.hospital.model.entity.Administrator;
import br.edu.ufersa.hospital.model.entity.Account;
import br.edu.ufersa.hospital.model.entity.Usuario;

public class AccountBO<e extends Account> {
	UsuarioDAO usuDao = new UsuarioDAO();
	AdministratorDAO AdministratorinDao = new AdministratorDAO();
	AccountDAO<Account> AccountDao = new AccountDAO<Account>();
public Account adicionar(AccountDTO AccountDTO) throws PasswordErrorException {
	    
	    Account Account = br.edu.ufersa.hospital.model.entity.Account.converter(AccountDTO);
      
		ResultSet rs = AccountDao.encontrarPorUsername(Account);
		try {
			if(rs==null || !(rs.next())) {
				if(AccountDao.cadastrar(Account) == true)
					return Account;
					else throw new PasswordErrorException();
			}
			else throw new PasswordErrorException();
		} catch (SQLException e) {

			e.printStackTrace();
			throw new PasswordErrorException();
		}	
	} 
public Account autenticar(e vo) throws AutenticationException{
	ResultSet rs =  AccountDao.encontrarPorUsername(vo);
	try {
		if(rs.next()) // existe uma Account com esse username
			{
			if(rs.getString("senha").equals(vo.getSenha())) { // existe uma Account com essa senha
				Administrator Administratorin = new Administrator();
				Administratorin.setUsername(rs.getString("username"));
				ResultSet AdministratorRS = AdministratorinDao.encontrarPorUsername(Administratorin);
				if(AdministratorRS.next()) {
					Administratorin.setSenha(rs.getString("senha"));
					return Administratorin;
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
