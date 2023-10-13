package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.entity.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioBo
{
    public void criar(Funcionario func) throws Exception
    {
        if (func.isValid())
        {
            FuncionarioDao funcDao = new FuncionarioDao();

            List<Funcionario> funcList = funcDao.buscarPorCrm(func);

            if (funcList.isEmpty()) {
                funcDao.inserir(func);
            } else {
                throw new Exception();
            }
        }
        else
        {
            throw new CampoVazioException("Funcionário inválido");
        }
    }

    public void deletar(Funcionario func)
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        List<Funcionario> funcList = funcDao.buscarPorCrm(func);

        if (!funcList.isEmpty()) {
            funcDao.deletar(func);
        }
        else
        {
            throw new CampoVazioException("O funcionário não foi encontrado");
        }
    }

    public void alterar(Funcionario func) throws Exception
    {
        if (func.isValid())
        {
            FuncionarioDao funcDao = new FuncionarioDao();
            List<Funcionario> funcList = funcDao.buscarPorCrm(func);

            if (!funcList.isEmpty()) {
                funcDao.alterar(func);
            }
            else
            {
                throw new Exception();
            }
        }
        else
        {
            throw new CampoVazioException("A alteração não é válida, pois um dos campos é vazio");
        }
    }

    public List<Funcionario> buscarPorCrm(Funcionario func)
    {
        if (func.getCrm() != null && !func.getCrm().isEmpty())
        {
            FuncionarioDao funcDao = new FuncionarioDao();

            return funcDao.buscarPorCrm(func);
        }
        else
        {
            throw new CampoVazioException("Esta CRM não está presente no sistema");
        }
    }

    public List<Funcionario> buscarPorNome(Funcionario func)
    {
        if (func.getNome() != null && !func.getNome().isEmpty())
        {
            FuncionarioDao funcDao = new FuncionarioDao();

            return funcDao.buscarPorNome(func);
        }
        else
        {
            throw new CampoVazioException("Este nome não está presente no sistema");
        }
    }

    public Funcionario autenticar(Funcionario func) throws Exception
    {
        FuncionarioDao funcDao = new FuncionarioDao();

        List<Funcionario> temp = funcDao.buscarPorCrm(func);
        if (temp.get(0).getCrm().equals(func.getCrm()) && temp.get(0).getSenha().equals(func.getSenha()))
        {
            return temp.get(0);
        }
        else
        {
            throw new Exception();
        }
    }

    public List<Funcionario> listar()
    {
        FuncionarioDao funcDao = new FuncionarioDao();
        List<Funcionario> funcList = funcDao.listar();

        return funcList;
    }
}
