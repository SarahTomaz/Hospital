package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.model.entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioBo
{
    Funcionario uf = null;
    public void criar(Funcionario func)
    {
        BaseDao<Funcionario> funcDao = new FuncionarioDao();

        funcDao.inserir(func);
    }

    public void deletar(Funcionario func)
    {
        BaseDao<Funcionario> funcDao = new FuncionarioDao();

        funcDao.deletar(func);
    }

    public void alterar(Funcionario func)
    {
        BaseDao<Funcionario> funcDao = new FuncionarioDao();

        funcDao.alterar(func);
    }

    public Funcionario buscarPorCrm(Funcionario func)
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        uf = funcDao.buscarPorCrm(func);
        return uf;
    }

    public Funcionario buscarPorNome(Funcionario func)
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        uf = funcDao.buscarPorNome(func);
        return uf;
    }

    public List<Funcionario> listar()
    {
        List<Funcionario> func = new ArrayList<Funcionario>();
        BaseDao<Funcionario> funcDao = new FuncionarioDao();

        func = funcDao.listar();
        return func;
    }
}
