package br.edu.ufersa.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            sql = "SELECT * FROM userfuncionario where crm = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getCrm());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong("id");
            else return null;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally {closeConnection();}
    }

    @Override
    public void deletar(UserFuncionario entity) {

    }

    @Override
    public void alterar(UserFuncionario entity) {

    }

    @Override
    public UserFuncionario buscar(UserFuncionario entity) {
        return null;
    }

    @Override
    public List<UserFuncionario> listar() {
        return null;
    }
}
