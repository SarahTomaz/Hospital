package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao extends BaseDaoImpl<Consulta>
{
    @Override
    public Long inserir(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "INSERT INTO consultas (nome_m, nome_p, diamarcado) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getNomeMedico());
            ps.setString(2, entity.getNomePaciente());
            ps.setDate(3, entity.getData());
            ps.execute();
            ps.close();

            return ConsultaDao.buscarId(entity);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}

    }

    @Override
    public void deletar(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM consultas WHERE c_id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getNomeMedico());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    @Override
    public void alterar(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE consultas SET diamarcado = ? WHERE c_id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, entity.getData());
            ps.setLong(2, entity.getId());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public static Long buscarId(Consulta entity)
    {
        Connection con = getConnection();

        try {
            String sql = "SELECT * FROM consultas WHERE nome_m = ? and nome_p = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);

            ps.setString(1, entity.getNomeMedico());
            ps.setString(2, entity.getNomePaciente());
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getLong("c_id");
            else
                return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<Consulta> buscarPorNomeM(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM consultas where nome_m = ?";
        List<Consulta> cons = new ArrayList<Consulta>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNomeMedico());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("c-id"));
                    cs.setNomeMedico(rs.getString("nome_m"));
                    cs.setNomePaciente(rs.getString("nome_p"));
                    cs.setData(rs.getDate("diamarcado"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                cons.add(cs);
                ps.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return cons;
    }

    public List<Consulta> buscarPorNomeP(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM consultas where nome_p = ?";
        List<Consulta> cons = new ArrayList<Consulta>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNomePaciente());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("c-id"));
                    cs.setNomeMedico(rs.getString("nome_m"));
                    cs.setNomePaciente(rs.getString("nome_p"));
                    cs.setData(rs.getDate("diamarcado"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                cons.add(cs);
                ps.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return cons;
    }

    public List<Consulta> buscarPorData(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM consultas where diamarcado = ?";
        List<Consulta> cons = new ArrayList<Consulta>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, entity.getData());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("c-id"));
                    cs.setNomeMedico(rs.getString("nome_m"));
                    cs.setNomePaciente(rs.getString("nome_p"));
                    cs.setData(rs.getDate("diamarcado"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                cons.add(cs);
                ps.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return cons;
    }

    @Override
    public List<Consulta> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM consultas";
        List<Consulta> cons = new ArrayList<Consulta>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("c-id"));
                    cs.setNomeMedico(rs.getString("nome_m"));
                    cs.setNomePaciente(rs.getString("nome_p"));
                    cs.setData(rs.getDate("diamarcado"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                cons.add(cs);
                ps.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return cons;
    }
}
