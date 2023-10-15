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
        String sql = "INSERT INTO consulta VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getMedico());
            ps.setString(3, entity.getPaciente());
            ps.setDate(4, entity.getData_consulta());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return null;
    }

    @Override
    public void deletar(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM consulta WHERE id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public void alterar(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE consulta SET id = ?, medico = ?, paciente = ?, data_consulta = ? WHERE id = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getMedico());
            ps.setString(3, entity.getPaciente());
            ps.setDate(4, entity.getData_consulta());
            ps.setLong(5, entity.getId());
            ps.execute();
            ps.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        closeConnection();
    }

    public List<Consulta> buscarPorId(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM agenda where id = ?";
        List<Consulta> conList = new ArrayList<>();


        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("nome_m"));
                    cs.setPaciente(rs.getString("nome_p"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }

    public List<Consulta> buscarPorIdC(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM consulta where id = ?";
        List<Consulta> conList = new ArrayList<>();


        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("medico"));
                    cs.setPaciente(rs.getString("paciente"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }

    public List<Consulta> buscarPorNomeM(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM agenda where nome_m = ?";
        List<Consulta> conList = new ArrayList<>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getMedico());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("nome_m"));
                    cs.setPaciente(rs.getString("nome_p"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }

    public List<Consulta> buscarPorNomeP(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM agenda where nome_p = ?";
        List<Consulta> conList = new ArrayList<>();


        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getPaciente());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("nome_m"));
                    cs.setPaciente(rs.getString("nome_p"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }

    public List<Consulta> buscarPorData(Consulta entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM agenda where data_consulta = ?";
        List<Consulta> conList = new ArrayList<>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, entity.getData_consulta());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();
                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("nome_m"));
                    cs.setPaciente(rs.getString("nome_p"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }

    @Override
    public List<Consulta> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM agenda";
        List<Consulta> conList = new ArrayList<>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Consulta cs = new Consulta();

                try
                {
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("nome_m"));
                    cs.setPaciente(rs.getString("nome_p"));
                    cs.setData_consulta(rs.getDate("data_consulta"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                conList.add(cs);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return conList;
    }
}
