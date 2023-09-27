package br.edu.ufersa.hospital.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ufersa.hospital.model.entity.Conta;
import br.edu.ufersa.hospital.model.entity.Usuario;
import br.edu.ufersa.hospital.model.entity.Adm;
import br.edu.ufersa.hospital.model.entity.Consulta;

public class ContaDAO<entity extends Conta> extends BaseDAO implements BaseInterDAO<entity> {

	@Override
	public boolean cadastrar(entity e) {
		String sql = "insert into conta (username,senha) values (?,?);";

		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,e.getUsername());
			ps.setString(2,e.getSenha());
			ps.execute();
			return true;

		} catch (SQLException x) {
			x.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean excluirPorId(entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet encontrar(entity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public entity encontrarPorId(entity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(entity e, String field) {
		// TODO Auto-generated method stub
		return null;
	}
	public ResultSet encontrarPorUsername(entity e) {
		String sql = "SELECT * FROM conta WHERE username=? ;";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, e.getUsername());

            ResultSet rs = ps.executeQuery();
            return rs;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

	@Override
	public ResultSet encontrarPorNome(entity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluirPorCPF(entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editar(entity e, String valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet BuscarPorId(entity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editar(Consulta vo, int idPaciente) {
		// TODO Auto-generated method stub
		return false;
	}
}
