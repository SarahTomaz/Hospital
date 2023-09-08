package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.model.entity.UserFuncionario;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioBo
{
    UserFuncionario uf = null;
    public void criar(UserFuncionario func)
    {
        BaseDao<UserFuncionario> funcDao = new FuncionarioDao();

        funcDao.inserir(func);
    }

    public void deletar(UserFuncionario func)
    {
        BaseDao<UserFuncionario> funcDao = new FuncionarioDao();

        funcDao.deletar(func);
    }

    public void alterar(UserFuncionario func)
    {
        BaseDao<UserFuncionario> funcDao = new FuncionarioDao();

        funcDao.alterar(func);
    }

    public UserFuncionario buscarPorCrm(UserFuncionario func)
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        uf = funcDao.buscarPorCrm(func);
        return uf;
    }

    public UserFuncionario buscarPorNome(UserFuncionario func)
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        uf = funcDao.buscarPorNome(func);
        return uf;
    }

    public List<UserFuncionario> listar()
    {
        List<UserFuncionario> func = new ArrayList<UserFuncionario>();
        BaseDao<UserFuncionario> funcDao = new FuncionarioDao();

        func = funcDao.listar();
        return func;
    }
}
