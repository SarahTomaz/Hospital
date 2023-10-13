package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Prontuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioDao extends BaseDaoImpl<Prontuario>
{
    @Override
    public Long inserir(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "INSERT INTO prontuario VALUES (?, ?, ?, ?)";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getP_Cpf());
            ps.setDate(3, entity.getData());
            ps.setString(4, entity.getObservacoes());
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
        return null;
    }

    @Override
    public void deletar(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM prontuario WHERE id = ?";

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

    @Override
    public void alterar(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE prontuario SET id = ?, p_cpf = ?, data_criacao = ?, observacoes = ? WHERE id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getP_Cpf());
            ps.setDate(3, entity.getData());
            ps.setString(4, entity.getObservacoes());
            ps.setLong(5, entity.getId());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public List<Prontuario> buscarPorId(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM prontuario WHERE id = ?";
        List<Prontuario> proList = new ArrayList<>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Prontuario pro = new Prontuario();

                pro.setId(rs.getLong("id"));
                pro.setP_Cpf(rs.getString("p_cpf"));
                pro.setData(rs.getDate("data_criacao"));
                pro.setObservacoes(rs.getString("observacoes"));

                proList.add(pro);
            }
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return proList;
    }

    public List<Prontuario> buscarPorP_Cpf(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM prontuario WHERE p_Cpf = ?";
        List<Prontuario> proList = new ArrayList<>();

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getP_Cpf());
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Prontuario pro = new Prontuario();

                pro.setId(rs.getLong("id"));
                pro.setP_Cpf(rs.getString("p_cpf"));
                pro.setData(rs.getDate("data_criacao"));
                pro.setObservacoes(rs.getString("observacoes"));

                proList.add(pro);
            }
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return proList;
    }

    @Override
    public List<Prontuario> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM prontuario";
        List<Prontuario> proList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Prontuario pro = new Prontuario();

                try
                {
                    pro.setId(rs.getLong("id"));
                    pro.setP_Cpf(rs.getString("p_cpf"));
                    pro.setData(rs.getDate("data_criacao"));
                    pro.setObservacoes(rs.getString("observacoes"));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                proList.add(pro);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return proList;
    }
}
