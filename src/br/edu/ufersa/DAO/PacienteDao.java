package br.edu.ufersa.DAO;


import br.edu.ufersa.model.entity.Paciente;

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
            ps.setLong(4, entity.getPronturaio().getId());
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
        String sql = "DELETE FROM pacientes WHERE cpf = ?";

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
        String sql = "UPDATE paciente SET nome = ? WHERE cpf = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCpf());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public Paciente buscarPorCpf(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        Paciente pc = new Paciente();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCpf());
            rs = ps.executeQuery();

            rs.next();
            pc.setCpf(rs.getString("cpf"));
            pc.setNome(rs.getString("nome"));
            pc.setEndereco(rs.getString("endereco"));
            pc.getPronturaio().setId(rs.getLong("p_id"));
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return pc;
    }

    public Paciente buscarPorNome(Paciente entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente WHERE nome = ?";
        Paciente pc = new Paciente();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getNome());
            rs = ps.executeQuery();

            rs.next();
            pc.setCpf(rs.getString("cpf"));
            pc.setNome(rs.getString("nome"));
            pc.setEndereco(rs.getString("endereco"));
            pc.getPronturaio().setId(rs.getLong("p_id"));
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return pc;
    }

    @Override
    public List<Paciente> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM paciente";
        List<Paciente> pc = new ArrayList<Paciente>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Paciente usu = new Paciente();

                try {
                    usu.setCpf(rs.getString("cpf"));
                    usu.setNome(rs.getString("nome"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.getPronturaio().setId(rs.getLong("p_id"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                pc.add(usu);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {closeConnection();}
        return pc;
    }
}
