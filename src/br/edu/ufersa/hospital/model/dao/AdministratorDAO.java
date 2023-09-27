package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.edu.ufersa.hospital.model.entity.Administrator;
import br.edu.ufersa.hospital.model.entity.Consulta;

public class AdministratorDAO extends BaseDAO implements BaseInterDAO<Adm> {

	@Override
	public boolean cadastrar(Administrator vo) {
		String sql = "insert into adm (username,senha) values (?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,vo.getUsername());
			ps.setString(2,vo.getSenha());
			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean excluirPorId(Administrator vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultSet encontrar(Administrator vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrator encontrarPorId(Administrator vo) {
		return null;
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Administrator vo, String field) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultSet encontrarPorUsername(Administrator vo) {
		String sql = "SELECT * FROM Administrator WHERE username=? ;";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, vo.getUsername());

            ResultSet rs = ps.executeQuery();
            return rs;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

	@Override
	public ResultSet encontrarPorNome(Administrator e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluirPorCPF(Administrator e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(Administrator e, String cpf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet BuscarPorId(Administrator e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editar(Consulta vo, int idPaciente) {
		// TODO Auto-generated method stub
		return false;
	}
}