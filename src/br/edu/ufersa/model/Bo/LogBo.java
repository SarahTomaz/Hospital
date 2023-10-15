package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.LogDao;
import br.edu.ufersa.model.entity.Log;

import java.util.List;

public class LogBo
{
    LogDao logDao = new LogDao();
    public List<Log> buscarPorData(Log log)
    {
        return logDao.buscarPorData(log);
    }

    public List<Log> buscarPorUsuario(Log log)
    {
        return logDao.buscarPorUsuario(log);
    }

    public List<Log> listar()
    {
        return logDao.listar();
    }
}
