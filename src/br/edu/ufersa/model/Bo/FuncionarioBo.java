package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.model.entity.UserFuncionario;

public class FuncionarioBo
{
    public void criar(UserFuncionario func)
    {
        BaseDao<UserFuncionario> funcDao = new FuncionarioDao();

        funcDao.inserir(func);
    }
}
