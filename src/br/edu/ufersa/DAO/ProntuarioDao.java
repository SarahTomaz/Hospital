package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Prontuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioDao extends BaseDaoImpl<Prontuario> {

    private static final String INSERT_SQL = "INSERT INTO prontuario (horario, observacoes) VALUES (?, ?)";
    private static final String DELETE_SQL = "DELETE FROM prontuario WHERE p_id = ?";
    private static final String UPDATE_SQL = "UPDATE prontuario SET observacoes = ? WHERE p_id = ?";
    private static final String SELECT_BY_FIELD_SQL = "SELECT * FROM prontuario WHERE ";

    @Override
    public Long inserir(Prontuario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, entity.getData());
            ps.setString(2, entity.getObservacoes());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
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
    public void deletar(Prontuario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setLong(1, entity.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Prontuario entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getObservacoes());
            ps.setLong(2, entity.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Prontuario buscarPorCampo(String campo, String valor) {
        String sql = SELECT_BY_FIELD_SQL + campo + " = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createProntuarioFromResultSet(rs);
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
    public List<Prontuario> listar() {
        List<Prontuario> prontuarios = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prontuario");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                prontuarios.add(createProntuarioFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return prontuarios;
    }

    private Prontuario createProntuarioFromResultSet(ResultSet rs) throws SQLException {
        Prontuario prontuario = new Prontuario();
        prontuario.setId(rs.getLong("p_id"));
        prontuario.setData(rs.getDate("horario"));
        prontuario.setObservacoes(rs.getString("observacoes"));
        return prontuario;
    }
}
