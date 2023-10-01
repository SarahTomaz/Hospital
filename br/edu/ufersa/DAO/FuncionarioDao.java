package br.edu.ufersa.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.model.entity.Funcionario;

public class FuncionarioDao extends BaseDaoImpl<Funcionario>
{

    @Override
    public Long inserir(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCrm());
            ps.setString(2, entity.getCpf());
            ps.setString(3, entity.getNome());
            ps.setString(4, entity.getEndereco());
            ps.setString(5, entity.getSenha());
            ps.setDouble(6, entity.getSalario());
            ps.setBoolean(7, entity.getGerente());
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
    public void deletar(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM funcionario WHERE crm = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCrm());
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
    public void alterar(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE funcionario SET crm = ?, nome = ?, cpf = ?, endereco = ?, senha = ?, salario = ? WHERE crm = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getCpf());
            ps.setString(4, entity.getEndereco());
            ps.setString(5, entity.getSenha());
            ps.setDouble(6, entity.getSalario());
            ps.setString(7, entity.getCrm());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public Funcionario buscarPorCrm(Funcionario entity) throws SQLException
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE crm = ?";
        Funcionario uf = new Funcionario();
        ResultSet rs = null;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            rs = ps.executeQuery();

            rs.next();
            uf.setCrm(rs.getString("crm"));
            uf.setNome(rs.getString("nome"));
            uf.setCpf(rs.getString("cpf"));
            uf.setEndereco(rs.getString("endereco"));
            uf.setSenha(rs.getString("senha"));
            uf.setSalario(rs.getLong("salario"));
            uf.setGerente(rs.getBoolean("gerente"));

            ps.close();

        closeConnection();
        return uf;
    }

    public Funcionario buscarPorNome(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE nome = ?";
        Funcionario uf = new Funcionario();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            rs = ps.executeQuery();

            rs.next();
            uf.setCrm(rs.getString("crm"));
            uf.setNome(rs.getString("nome"));
            uf.setCpf(rs.getString("cpf"));
            uf.setEndereco(rs.getString("endereco"));
            uf.setSenha(rs.getString("senha"));
            uf.setSalario(rs.getLong("salario"));

            ps.close();
            return uf;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
    }

    public Funcionario buscarPorSenha(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE senha = ?";
        Funcionario uf = new Funcionario();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getSenha());
            rs = ps.executeQuery();

            rs.next();
            uf.setCrm(rs.getString("crm"));
            uf.setNome(rs.getString("nome"));
            uf.setCpf(rs.getString("cpf"));
            uf.setEndereco(rs.getString("endereco"));
            uf.setSenha(rs.getString("senha"));
            uf.setSalario(rs.getLong("salario"));

            ps.close();
            return uf;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
    }

    @Override
    public List<Funcionario> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> func = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Funcionario usu = new Funcionario();

                try {
                    usu.setCrm(rs.getString("crm"));
                    usu.setNome(rs.getString("nome"));
                    usu.setCpf(rs.getString("cpf"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setSalario(rs.getLong("salario"));
                    usu.setGerente(rs.getBoolean("gerente"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                func.add(usu);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return func;
    }
}
