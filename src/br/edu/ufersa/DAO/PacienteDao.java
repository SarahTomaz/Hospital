package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao extends BaseDaoImpl<Paciente> {

    private static final String INSERT_SQL = "INSERT INTO paciente VALUES (?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM paciente WHERE cpf = ?";
    private static final String UPDATE_SQL = "UPDATE paciente SET nome_p = ? WHERE cpf = ?";
    private static final String SELECT_BY_FIELD_SQL = "SELECT * FROM paciente WHERE ";

    @Override
    public Long inserir(Paciente entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, entity.getCpf());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getEndereco());
            ps.setLong(4, entity.getProntuario().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public void deletar(Paciente entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setString(1, entity.getCpf());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Paciente entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCpf());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Paciente buscarPorCampo(String campo, String valor) {
        String sql = SELECT_BY_FIELD_SQL + campo + " = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createPacienteFromResultSet(rs);
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
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pacientes.add(createPacienteFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return pacientes;
    }

    private Paciente createPacienteFromResultSet(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        Prontuario prontuario = new Prontuario();
        paciente.setProntuario(prontuario);
        paciente.setCpf(rs.getString("cpf"));
        paciente.setNome(rs.getString("nome_p"));
        paciente.setEndereco(rs.getString("endereco"));
        paciente.getProntuario().setId(rs.getLong("p_id"));
        return paciente;
    }
}
