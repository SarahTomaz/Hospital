package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Paciente;
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
        String sql = "INSERT INTO prontuario (datadeatemdimento, observacoes) (VALUES (?, ?)";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(2, entity.getData());
            ps.setString(3, entity.getObservacoes());
            ps.execute();
            ps.close();

            sql = "SELECT * FROM prontuario WHERE datadeatendimento = ?";
            ps = con.prepareStatement(sql);
            ps.setDate(1, entity.getData());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getLong("p_id");
            else
                return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
    }

    @Override
    public void deletar(Prontuario entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM prontuario WHERE p_id = ?";

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
        String sql = "UPDATE paciente SET observacoes = ? WHERE p_id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getObservacoes());
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

    @Override
    public List<Prontuario> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM prontuario";
        List<Prontuario> pr = new ArrayList<Prontuario>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Prontuario usu = new Prontuario();

                try
                {
                    usu.setId(rs.getLong("p_id"));
                    usu.setData(rs.getDate("datadeatendimento"));
                    usu.setObservacoes(rs.getString("endereco"));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                pr.add(usu);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return pr;
    }
}
