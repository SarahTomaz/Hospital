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
        String sql = "UPDATE funcionario SET crm = ?, nome = ?, cpf = ?, endereco = ?, senha = ?, salario = ?, gerente = ? WHERE crm = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            ps.setString(2, entity.getNome());
            ps.setString(3, entity.getCpf());
            ps.setString(4, entity.getEndereco());
            ps.setString(5, entity.getSenha());
            ps.setDouble(6, entity.getSalario());
            ps.setBoolean(7, entity.getGerente());
            ps.setString(8, entity.getCrm());
            ps.execute();
            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public List<Funcionario> buscarPorCrm(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario WHERE crm = ?";
        List<Funcionario> funcList = new ArrayList<>();
        ResultSet rs = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            rs = ps.executeQuery();

            while (rs.next())
            {
                Funcionario uf = new Funcionario();

                uf.setCrm(rs.getString("crm"));
                uf.setNome(rs.getString("nome"));
                uf.setCpf(rs.getString("cpf"));
                uf.setEndereco(rs.getString("endereco"));
                uf.setSenha(rs.getString("senha"));
                uf.setSalario(rs.getLong("salario"));
                uf.setGerente(rs.getBoolean("gerente"));

                funcList.add(uf);
            }
            ps.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return funcList;
    }

    public List<Funcionario> buscarPorNome(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario where nome = ?";
        List<Funcionario> funcList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Funcionario usu = new Funcionario();

                try
                {
                    usu.setCrm(rs.getString("crm"));
                    usu.setNome(rs.getString("nome"));
                    usu.setCpf(rs.getString("cpf"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setSalario(rs.getLong("salario"));
                    usu.setGerente(rs.getBoolean("gerente"));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                funcList.add(usu);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return funcList;
    }

    public List<Funcionario> buscarPorSenha(Funcionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario where senha = ?";
        List<Funcionario> funcList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getSenha());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Funcionario usu = new Funcionario();

                try
                {
                    usu.setCrm(rs.getString("crm"));
                    usu.setNome(rs.getString("nome"));
                    usu.setCpf(rs.getString("cpf"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setSenha(rs.getString("senha"));
                    usu.setSalario(rs.getLong("salario"));
                    usu.setGerente(rs.getBoolean("gerente"));

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                funcList.add(usu);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return funcList;
    }

    @Override
    public List<Funcionario> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcList = new ArrayList<>();

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
                funcList.add(usu);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return funcList;
    }
}
