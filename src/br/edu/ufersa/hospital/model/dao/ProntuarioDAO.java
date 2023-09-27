package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Medico;
import br.edu.ufersa.hospital.model.entity.Paciente;
import br.edu.ufersa.hospital.model.entity.Prontuario;

@SuppressWarnings("unused")
public class ProntuarioDAO extends BaseDAO implements BaseInterDAO<Prontuario> {

	@Override
  	public boolean cadastrar(Prontuario vo) {
	  String sql = "INSERT INTO Prontuario (momento,obs,idPaciente) VALUES (?,?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setDate(1, Date.valueOf(vo.getData()));
			ps.setString(2, vo.getObs());
			ps.setInt(3, vo.getPaciente().getId());
			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
  	}

	@Override
	public boolean editar(Prontuario vo, String valor) {
	  String sql = "UPDATE Prontuario SET momento = ?, obs = ?, idPaciente = ? WHERE idProntuario=? ";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setDate(1, Date.valueOf(vo.getData()));
			ps.setString(2, vo.getObs());
			ps.setInt(3, vo.getPaciente().getId());
			ps.setInt(4, vo.getId());
			ps.executeUpdate();

			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}	
 	}
  
	@Override
 	public boolean excluirPorId(Prontuario vo) {
		String sql = "DELETE FROM Prontuario WHERE idProntuario = ?;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getId());
			
			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
  	}

	@Override
	public ResultSet listar(){
		String sql = "SELECT * FROM prontuario;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);

			return ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

  public ResultSet encontrarPorPaciente(Prontuario vo) {
	  String sql = "SELECT * FROM Prontuario WHERE idPaciente=? ;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getPaciente().getId());

			return ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;
		}
  }
  
  public ResultSet encontrar(Prontuario vo){
      String sql = "SELECT * FROM Prontuario WHERE idPaciente=? ;";

      try {
          PreparedStatement ps = getConnection().prepareStatement(sql);
          ps.setInt(1, vo.getPaciente().getId());

          ResultSet rs = ps.executeQuery();
          return rs;
      } catch(SQLException ex) {
          ex.printStackTrace();
          return null;
      }
  }

@Override
public Prontuario encontrarPorId(Prontuario e) {
	
	return null;
}

@Override
public ResultSet encontrarPorCampoEspecifico(Prontuario e, String field) {
	
	return null;
}

@Override
public ResultSet encontrarPorNome(Prontuario e) {
	
	return null;
}

@Override
public boolean excluirPorCPF(Prontuario e) {
	
	return false;
}

@Override
public ResultSet BuscarPorId(Prontuario e) {
	
	return null;
}

@Override
public boolean editar(Consulta vo, int idPaciente) {
	
	return false;
}
  
}
