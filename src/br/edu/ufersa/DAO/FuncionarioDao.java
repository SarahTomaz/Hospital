package br.edu.ufersa.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.model.entity.UserFuncionario;

public class FuncionarioDao extends BaseDaoImpl<UserFuncionario>
{

    @Override
    public Long inserir(UserFuncionario entity)
    {
        Connection con = getConnection();
        String sql = "INSERT INTO userfuncionario VALUES (?, ?, ?, ?, ?, ?)";

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
    public void deletar(UserFuncionario entity)
    {
        Connection con = getConnection();
        String sql = "DELETE FROM userfuncionario WHERE crm = ?";

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
    public void alterar(UserFuncionario entity)
    {
        Connection con = getConnection();
        String sql = "UPDATE userfuncionario SET nome = ? WHERE crm = ?";

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

    public UserFuncionario buscarPorCrm(UserFuncionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM userfuncionario WHERE crm = ?";
        UserFuncionario uf = new UserFuncionario();
        ResultSet rs = null;

        try
        {
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

            ps.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return uf;
    }

    public UserFuncionario buscarPorNome(UserFuncionario entity)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM userfuncionario WHERE Nome = ?";
        UserFuncionario uf = new UserFuncionario();
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
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return uf;
    }

    @Override
    public List<UserFuncionario> listar()
    {
        Connection con = getConnection();
        String patric = "SELECT * FROM userfuncionario";
        List<UserFuncionario> func = new ArrayList<UserFuncionario>();

        try {
            PreparedStatement ps = con.prepareStatement(patric);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                UserFuncionario usu = new UserFuncionario();

                try {
                    usu.setCrm(rs.getString("crm"));
                    usu.setNome(rs.getString("nome"));
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
