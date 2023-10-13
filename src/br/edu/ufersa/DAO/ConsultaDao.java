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
            ps.setDate(4, entity.getData());
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
            ps.setDate(4, entity.getData());
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
                    cs.setData(rs.getDate("data_consulta"));
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
        String sql = "SELECT * FROM consulta where medico = ?";
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
                    cs.setMedico(rs.getString("medico"));
                    cs.setPaciente(rs.getString("paciente"));
                    cs.setData(rs.getDate("data_consulta"));
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
        String sql = "SELECT * FROM consulta where paciente = ?";
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
                    cs.setMedico(rs.getString("medico"));
                    cs.setPaciente(rs.getString("paciente"));
                    cs.setData(rs.getDate("data_consulta"));
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
        String sql = "SELECT * FROM consulta where data_consulta = ?";
        List<Consulta> conList = new ArrayList<>();

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
                    cs.setId(rs.getLong("id"));
                    cs.setMedico(rs.getString("medico"));
                    cs.setPaciente(rs.getString("paciente"));
                    cs.setData(rs.getDate("data_consulta"));
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
        String sql = "SELECT * FROM consulta";
        List<Consulta> conList = new ArrayList<Consulta>();

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
                    cs.setMedico(rs.getString("medico"));
                    cs.setPaciente(rs.getString("paciente"));
                    cs.setData(rs.getDate("data_consulta"));
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
