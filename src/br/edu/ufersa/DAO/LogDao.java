package br.edu.ufersa.DAO;

import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDao extends BaseDaoImpl<Log>
{
    @Override
    public Long inserir(Log entity) {return null;}

    @Override
    public void deletar(Log entity) {}

    @Override
    public void alterar(Log entity) {}

    public List<Log> buscarPorData(Log log)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM log where data = ?";
        List<Log> logList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, log.getData());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                try
                {
                    log.setData(rs.getDate("data"));
                    log.setHora(rs.getTime("hora"));
                    log.setUser(rs.getString("usuario"));
                    log.setModif(rs.getString("modificacao"));
                    log.setTabela(rs.getString("tabela"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                logList.add(log);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return logList;
    }

    public List<Log> buscarPorUsuario(Log log)
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM log where usuario = ?";
        List<Log> logList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, log.getUser());
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                try {
                    log.setData(rs.getDate("data"));
                    log.setHora(rs.getTime("hora"));
                    log.setUser(rs.getString("usuario"));
                    log.setModif(rs.getString("modificacao"));
                    log.setTabela(rs.getString("tabela"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                logList.add(log);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return logList;
    }

    public List<Log> listar()
    {
        Connection con = getConnection();
        String sql = "SELECT * FROM log";
        List<Log> logList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Log log = new Log();
                try
                {
                    log.setData(rs.getDate("data"));
                    log.setHora(rs.getTime("hora"));
                    log.setUser(rs.getString("usuario"));
                    log.setModif(rs.getString("modificacao"));
                    log.setTabela(rs.getString("tabela"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                logList.add(log);
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return logList;
    }
}
