package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends BaseDaoImpl<Funcionario> {

    private static final String INSERT_SQL = "INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM funcionario WHERE crm = ?";
    private static final String UPDATE_SQL = "UPDATE funcionario SET nome_m = ? WHERE crm = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM funcionario WHERE crm = ?";
    private static final String SELECT_BY_FIELD_SQL = "SELECT * FROM funcionario WHERE ";

    @Override
    public Long inserir(Funcionario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, entity.getCrm());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getCpf());
            ps.setString(4, entity.getEndereco());
            ps.setString(5, entity.getSenha());
            ps.setDouble(6, entity.getSalario());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public void deletar(Funcionario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setString(1, entity.getCrm());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Funcionario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCrm());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Funcionario buscarPorCampo(String campo, String valor) {
        String sql = SELECT_BY_FIELD_SQL + campo + " = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createFuncionarioFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> func = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM funcionario");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                func.add(createFuncionarioFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return func;
    }

    private Funcionario createFuncionarioFromResultSet(ResultSet rs) throws SQLException {
        Funcionario usu = new Funcionario();
        usu.setCrm(rs.getString("crm"));
        usu.setNome(rs.getString("nome_m"));
        usu.setCpf(rs.getString("cpf"));
        usu.setEndereco(rs.getString("endereco"));
        usu.setSenha(rs.getString("senha"));
        usu.setSalario(rs.getLong("salario"));
        return usu;
    }
}
