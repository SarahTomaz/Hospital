package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Usuario;

public class UsuarioDAO extends BaseDAO implements BaseInterDAO<Usuario> {

	@Override
	public boolean cadastrar(Usuario vo) {
		String sql = "insert into usuario (username,senha) values (?,?);";
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
	public boolean editar(Usuario vo, String valor) {
		
		return false;
	}

	@Override
	public boolean excluirPorId(Usuario vo) {
		
		return false;
	}

	@Override
	public ResultSet listar() {
		
		return null;
	}
	
	@Override
	public ResultSet encontrar(Usuario vo) {
		
		return null;
	}

	@Override
	public Usuario encontrarPorId(Usuario vo) {
		return null;
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Usuario vo, String field) {
		
		return null;
	}
	public ResultSet encontrarPorUsername(Usuario vo) {
		String sql = "SELECT * FROM usuario WHERE username=? ;";

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
	public ResultSet encontrarPorNome(Usuario e) {
		
		return null;
	}

	@Override
	public boolean excluirPorCPF(Usuario e) {
		
		return false;
	}

	@Override
	public ResultSet BuscarPorId(Usuario e) {
		
		return null;
	}

	@Override
	public boolean editar(Consulta vo, int Pacienteid) {
		
		return false;
	}
}
