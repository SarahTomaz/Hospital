package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao extends BaseDaoImpl<Consulta> {

    private static final String INSERT_SQL = "INSERT INTO consultas (nome_m, nome_p, diamarcado) VALUES (?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM consultas WHERE c_id = ?";
    private static final String UPDATE_SQL = "UPDATE consultas SET diamarcado = ? WHERE c_id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM consultas WHERE c_id = ?";
    private static final String SELECT_BY_FIELD_SQL = "SELECT * FROM consultas WHERE ";

    @Override
    public Long inserir(Consulta entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getNomeMedico());
            ps.setString(2, entity.getNomePaciente());
            ps.setDate(3, entity.getData());
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
    public void deletar(Consulta entity) {
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
    public void alterar(Consulta entity) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setDate(1, entity.getData());
            ps.setLong(2, entity.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Consulta> listar() {
        List<Consulta> cons = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM consultas");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cons.add(createConsultaFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cons;
    }

    public List<Consulta> buscarPorCampo(String campo, String valor) {
        List<Consulta> cons = new ArrayList<>();
        String sql = SELECT_BY_FIELD_SQL + campo + " = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cons.add(createConsultaFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cons;
    }

    private Consulta createConsultaFromResultSet(ResultSet rs) throws SQLException {
        Consulta cs = new Consulta();
        cs.setId(rs.getLong("c_id"));
        cs.setNomeMedico(rs.getString("nome_m"));
        cs.setNomePaciente(rs.getString("nome_p"));
        cs.setData(rs.getDate("diamarcado"));
        return cs;
    }
}
