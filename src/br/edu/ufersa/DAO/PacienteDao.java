package br.edu.ufersa.DAO;


import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao extends BaseDaoImpl<Paciente>
{

    @Override
    public Long inserir(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "INSERT INTO paciente VALUES (?, ?, ?, ?)";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCpf());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getEndereco());
            ps.setLong(4, entity.getIdade());
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return null;
    }

    @Override
    public void deletar(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM paciente WHERE cpf = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCpf());
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
    public void alterar(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE paciente SET cpf = ?, nome = ?, endereco = ?, idade = ? WHERE cpf = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCpf());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getEndereco());
            ps.setLong(4, entity.getIdade());
            ps.setString(5, entity.getCpf());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public List<Paciente> buscarPorCpf(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        List<Paciente> pacList = new ArrayList<>();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCpf());
            rs = ps.executeQuery();

            while (rs.next())
            {
                Paciente pc = new Paciente();

                pc.setCpf(rs.getString("cpf"));
                pc.setNome(rs.getString("nome"));
                pc.setEndereco(rs.getString("endereco"));
                pc.setIdade(rs.getInt("idade"));

                pacList.add(pc);
            }
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return pacList;
    }

    public List<Paciente> buscarPorNome(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente WHERE nome = ?";
        List<Paciente> pacList = new ArrayList<>();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            rs = ps.executeQuery();

            while (rs.next())
            {
                Paciente pc = new Paciente();

                pc.setCpf(rs.getString("cpf"));
                pc.setNome(rs.getString("nome"));
                pc.setEndereco(rs.getString("endereco"));
                pc.setIdade(rs.getInt("idade"));

                pacList.add(pc);
            }
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
        return pacList;
    }

    @Override
    public List<Paciente> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente";
        List<Paciente> pc = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Paciente usu = new Paciente();

                try
                {
                    usu.setCpf(rs.getString("cpf"));
                    usu.setNome(rs.getString("nome"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setIdade(rs.getInt("idade"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                //TODO 4 linhas quando tem 2;
                pc.add(usu);
            }
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return pc;
    }
}
