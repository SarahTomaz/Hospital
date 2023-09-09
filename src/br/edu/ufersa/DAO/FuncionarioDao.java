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
        String sql = "INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCrm());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getCpf());
            ps.setString(4, entity.getEndereco());
            ps.setString(5, entity.getSenha());
            ps.setDouble(6, entity.getSalario());
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
        String sql = "UPDATE funcionario SET nome_m = ? WHERE crm = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCrm());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public Funcionario buscarPorCrm(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE crm = ?";
        Funcionario uf = new Funcionario();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            rs = ps.executeQuery();

            rs.next();
            uf.setCrm(rs.getString("crm"));
            uf.setNome(rs.getString("nome_m"));
            uf.setCpf(rs.getString("cpf"));
            uf.setEndereco(rs.getString("endereco"));
            uf.setSenha(rs.getString("senha"));
            uf.setSalario(rs.getLong("salario"));

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return uf;
    }

    public Funcionario buscarPorNome(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE nome_m = ?";
        Funcionario uf = new Funcionario();
        ResultSet rs = null;

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            rs = ps.executeQuery();

            rs.next();
            uf.setCrm(rs.getString("crm"));
            uf.setNome(rs.getString("nome_m"));
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
        String patric = "SELECT * FROM funcionario";
        List<Funcionario> func = new ArrayList<Funcionario>();

        try {
            PreparedStatement ps = con.prepareStatement(patric);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Funcionario usu = new Funcionario();

                try {
                    usu.setCrm(rs.getString("crm"));
                    usu.setNome(rs.getString("nome_m"));
                    usu.setCpf(rs.getString("cpf"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setSalario(rs.getLong("salario"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                func.add(usu);
                ps.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return func;
    }
}
