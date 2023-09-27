package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Paciente;
import br.edu.ufersa.hospital.model.entity.Prontuario;

public class PacienteDAO extends BaseDAO implements BaseInterDAO<Paciente>{

	@Override
	public boolean cadastrar(Paciente vo) {
		String sql = "insert into Paciente (nome,endereco,cpf,idade) values (?,?,?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getNome());
			ps.setString(2, vo.getEndereco());
			ps.setString(3, vo.getCpf());
			ps.setInt(4, vo.getIdade());
			ps.execute();
			return true;

		}catch (SQLException e) {
			e.printStackTrace();

			return false;
			
		}
	}

	@Override
	public boolean editar(Paciente vo, String cpf) {
		String sql = "UPDATE Paciente SET cpf=?,endereco=?,nome=?,idade=? WHERE cpf=? ";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getCpf());
			ps.setString(2, vo.getEndereco());
			ps.setString(3, vo.getNome());
			ps.setInt(4, vo.getIdade());
			ps.setString(5, cpf);
			ps.executeUpdate();

			return true;		

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}	
		
	}

	public boolean editarPorCpf(Paciente vo) {
		String sql = "UPDATE Paciente SET nome=?,endereco=?,idade=? WHERE cpf=? ";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getNome());
			ps.setString(2, vo.getEndereco());
			ps.setInt(3, vo.getIdade());
			ps.setString(4, vo.getCpf());
			ps.executeUpdate();

			return true;		

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}	
		
	}

	@Override
	public boolean excluirPorId(Paciente vo) {
		String sql = "delete from Paciente where idPaciente = ?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getId());

			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
			
		}
	}
	  
	public boolean excluirPorCPF(Paciente vo) {
		String sql = "delete from Paciente where cpf = ?;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getCpf());

			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
			
		}
	}

	@Override
	public ResultSet listar(){
		String sql = "select * from Paciente";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery(sql);
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
	}

	public Paciente encontrarPorCPF(Paciente vo) {
		String sql = "SELECT * FROM Paciente WHERE cpf=? ;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getCpf());

			ResultSet rs = ps.executeQuery(sql);
			
			Paciente pc = new Paciente(rs.getInt("idPaciente"), rs.getString("nome"), rs.getString("Endereco"), rs.getString("cpf"), rs.getInt("idade")/*, new Vector<Prontuario>()*/);
			return pc;

		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;

		}
	}
	
	public Paciente encontrarPorId(Paciente vo) {
		String sql = "SELECT * FROM Paciente WHERE idPaciente=? ;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getId());

			ResultSet rs = ps.executeQuery();
			
			return new Paciente(rs.getInt("idPaciente"), rs.getString("nome"), rs.getString("Endereco"), rs.getString("cpf"), rs.getInt("idade"), new Vector<Prontuario>());

		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;
			
		}
	}
	
	public ResultSet encontrar(Paciente vo){
        String sql = "SELECT * FROM Paciente WHERE cpf=? ;";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCpf());

            ResultSet rs = ps.executeQuery();
            return rs;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	@Override
	public ResultSet encontrarPorNome(Paciente e) {
		String sql = "SELECT * FROM Paciente WHERE nome=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, e.getNome());
			ResultSet rs = pst.executeQuery();
			
			return rs;
		
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResultSet encontrarPorCampoEspecifico(Paciente e, String field) {
		
		return null;
	}
	public static void main(String args[]) {
		
	}

	@Override
	public ResultSet BuscarPorId(Paciente e) {
		
		return null;
	}

	@Override
	public boolean editar(Consulta vo, int Pacienteid) {
		
		return false;
	}


}
