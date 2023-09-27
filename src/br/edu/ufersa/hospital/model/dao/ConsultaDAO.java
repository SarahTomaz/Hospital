package br.edu.ufersa.hospital.model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Medico;
import br.edu.ufersa.hospital.model.entity.Paciente;

public class ConsultaDAO extends BaseDAO implements BaseInterDAO<Consulta>{

	@Override
	public boolean cadastrar(Consulta vo) {
		String sql = "insert into Consulta (idPaciente, idMedico) values (?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1,vo.getpacinteid());
			ps.setInt(2,vo.getMedicoid());
			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean editar(Consulta vo, int idPaciente) {
		String sql = "UPDATE Consulta SET idPaciente = ?, idMedico = ?  WHERE idPaciente=? ";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1,vo.getpacinteid());
			ps.setInt(2,vo.getMedicoid());
			ps.setInt(3, idPaciente);
			ps.executeUpdate();

			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}	
	}
	
	@Override
	public boolean excluirPorId(Consulta vo) {
		String sql = "delete from Consulta where idPaciente = ?;";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getpacinteid());

			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}
	
	@Override
	public ResultSet listar(){
		String sql = "select * from Consulta";
		
		try {
			Statement st = getConnection().createStatement();

			ResultSet rs = st.executeQuery(sql);
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;

		}
	}
	public Consulta buscarPorPaciente(Paciente vo) {
		String sql = "SELECT * FROM Consulta WHERE idPaciente=? ;";
		Consulta result = new Consulta();

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getId());
			ResultSet rs = ps.executeQuery();
			
			result.setId(rs.getInt("idConsulta"));
			//TO DO

			return result;
		
		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;
		}
	}

	public Consulta buscarPorMedico(Medico vo) { //O OBJETO COMPLETO OU APENAS UM FIELD?
		String sql = "SELECT * FROM Consulta WHERE idMedico=? ;";
		Consulta result = new Consulta();

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getId());
			ResultSet rs = ps.executeQuery();
			
			result.setId(rs.getInt("idConsulta"));
			//TODO


			return result;
		
		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;
		}
	}

	@Override
	public ResultSet encontrar(Consulta e) {
		String sql = "SELECT * FROM Consulta WHERE idPaciente=? ;";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, e.getpacinteid());

            ResultSet rs = ps.executeQuery();
            return rs;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	public ResultSet BuscarPorId(Consulta e) {
		String sql = "SELECT * FROM Consulta WHERE idPaciente=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, e.getId());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Consulta a = new Consulta();
				MedicoDAO dao1 = new MedicoDAO();
				PacienteDAO dao2 = new PacienteDAO();
				a.setHorario(LocalTime.parse(rs.getTime("horario").toString()));
				a.setData(LocalDate.parse(rs.getDate("dia").toString()));
				a.setId(e.getId());
				return rs;
			}
			else return null;
		
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public ResultSet BuscarPorCampoEspecifico(Consulta e, String field) {
		String sql = "SELECT * FROM Consulta WHERE " + field +"=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			switch (field) {
			case "dia":
				pst.setDate(1, Date.valueOf(e.getHorario().toString()));
				break;
				
			case "horario":
				pst.setTime(1, Time.valueOf(e.getData().toString()));
				break;
				
			case "idPaciente":
				pst.setInt(1, e.getpacinteid());
				break;
				
			case "idMedico":
				pst.setInt(1, e.getMedicoid());
				break;
			
			default: 
				pst.setInt(1, e.getId()); // id da consulta
			}
			
			ResultSet rs = pst.executeQuery();
			return rs;
		
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Consulta encontrarPorId(Consulta e) {
		return null;
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Consulta e, String field) {
		return null;
	}

	@Override
	public ResultSet encontrarPorNome(Consulta e) {
		return null;
	}

	@Override
	public boolean excluirPorCPF(Consulta e) {
		return false;
	}

	@Override
	public boolean editar(Consulta e, String cpf) {
		return false;
	}
}
