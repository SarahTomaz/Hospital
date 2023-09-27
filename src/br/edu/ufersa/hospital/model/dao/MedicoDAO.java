package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Medico;

public class MedicoDAO extends BaseDAO implements BaseInterDAO<Medico> {

	@Override
	public boolean cadastrar(Medico vo) { 
		String sql = "insert into Medico (nome, endereco, cpf, idMedico, codigoDoConselho, valorDaConsulta) values (?,?,?,?,?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getNome());
			ps.setString(2, vo.getEndereco());
			ps.setString(3, vo.getCpf());
			ps.setInt(4, vo.getId());
			ps.setInt(5, vo.getCodigoDoConselho());
			ps.setDouble(6, vo.getValorDaConsulta());
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}

	@Override
	public boolean editar(Medico vo, String cpf) { 
		String sql = "UPDATE Medico SET nome=?,endereco=?,cpf=?,codigoDoConselho=?,ValorDaConsulta=? WHERE cpf=? ";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getNome() );
			ps.setString(2, vo.getEndereco());
			ps.setString(3, vo.getCpf());
			ps.setInt(4, vo.getCodigoDoConselho());
			ps.setDouble(5, vo.getValorDaConsulta());
			ps.setString(6, cpf);
			ps.executeUpdate();

			return true;		
		
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}	
	}
	
	@Override
	public ResultSet listar() { 
		String sql = "SELECT * FROM Medico;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			return rs;

		} catch (SQLException ex) {
			ex.printStackTrace();

			return null;
			
		}
	}

	@Override
	public boolean excluirPorId(Medico vo) { 
		String sql = "delete from Medico where idMedico = ?;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, vo.getId());

			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}
	
	public boolean excluirPorCPF(Medico vo) { 
		String sql = "delete from Medico where cpf = ?;";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, vo.getCpf());

			return ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}

	  public Medico encontrarPorCodigoDoConselho(Medico e) { 
		  String sql = "SELECT * FROM Medico WHERE codigoDoConselho=? ;";
			try {
				PreparedStatement pst = getConnection().prepareStatement(sql);
				pst.setInt(1, e.getCodigoDoConselho());
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					Medico a = new Medico();
					a.setCpf(rs.getString("cpf"));
					a.setEndereco(rs.getString("endereco"));
					a.setNome(rs.getString("nome"));
					a.setValorDaConsulta(rs.getDouble("ValorDaConsulta"));
					a.setCodigoDoConselho(rs.getInt("CodigoDoConselho"));
					a.setId(rs.getInt("IdMedico"));
					a.setCodigoDoConselho(e.getCodigoDoConselho());
					return a;
				}
				else return null;
			
			} catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}
	  }

	  public Medico encontrarPorCpf(Medico e) { 
			String sql = "SELECT * FROM Medico WHERE cpf=? ;";
			try {
				PreparedStatement pst = getConnection().prepareStatement(sql);
				pst.setString(1, e.getCpf());
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					Medico a = new Medico();
					a.setEndereco(rs.getString("endereco"));
					a.setNome(rs.getString("nome"));
					a.setValorDaConsulta(rs.getDouble("ValorDaConsulta"));
					a.setCodigoDoConselho(rs.getInt("CodigoDoConselho"));
					a.setId(rs.getInt("IdMedico"));
					a.setCpf(e.getCpf());
					return a;
				}
				else return null;
			
			} catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}
		}
	  
	  public ResultSet encontrarPorNome(Medico e) { 
			String sql = "SELECT * FROM Medico WHERE nome=? ;";
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
	
	public ResultSet encontrar(Medico vo){ 
        String sql = "SELECT * FROM Medico WHERE cpf=? ;";

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
	
	
	public Medico encontrarPorId(Medico e) { 
		String sql = "SELECT * FROM Medico WHERE idMedico=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, e.getId());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Medico a = new Medico();
				a.setCpf(rs.getString("cpf"));
				a.setEndereco(rs.getString("endereco"));
				a.setNome(rs.getString("nome"));
				a.setCodigoDoConselho(rs.getInt("codigoDoConselho"));
				a.setValorDaConsulta(rs.getDouble("valorDaConsulta"));
				a.setId(e.getId());
				return a;
			}
			else return null;
		
		} catch (SQLException ex) 
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Medico e, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet BuscarPorId(Medico e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editar(Consulta vo, int Pacienteid) {
		// TODO Auto-generated method stub
		return false;
	}
}
