package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.PacienteDao;
import br.edu.ufersa.DAO.ProntuarioDao;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioBo
{
    public boolean autenticar_Cpf(Prontuario pro)
    {
        PacienteBo pacBo = new PacienteBo();
        Paciente pac = new Paciente();
        pac.setCpf(pro.getP_Cpf());

        List<Paciente> pacList = pacBo.buscarPorCpf(pac);

        return !pacList.isEmpty();
    }
    public Long stableId()
    {
        ProntuarioDao proDao = new ProntuarioDao();
        List<Prontuario> proList = proDao.listar();

        if (proList.isEmpty())
        {
           return 1L;
        }
        else
        {
            return ((proList.get(proList.size() - 1).getId()) + 1);
        }
    }
    public void criar(Prontuario pro) throws Exception
    {
        pro.setId(stableId());

        boolean autenticado = autenticar_Cpf(pro);
        if (autenticado && pro.isValid())
        {
            ProntuarioDao proDao = new ProntuarioDao();

            proDao.inserir(pro);
        }
        else
        {
            if (!autenticado)
            {
                throw new CampoVazioException("O paciente relacionado não esta no sistema");
            }
            else
            {
                throw new CampoVazioException("As informações inseridas não são válidas");
            }
        }
    }

    public void deletar(Prontuario pro)
    {
        ProntuarioDao proDao = new ProntuarioDao();
        List<Prontuario> proList = proDao.buscarPorId(pro);

        if (!proList.isEmpty())
        {
            proDao.deletar(pro);
        }
        else
        {
            throw new CampoVazioException("O id desse prontuário não foi encontrado");
        }
    }

    public void alterar(Prontuario pro) throws Exception
    {
        if (pro.isValid())
        {
            ProntuarioDao proDao = new ProntuarioDao();
            List<Prontuario> proList = proDao.buscarPorId(pro);

            if (!proList.isEmpty())
            {
                proDao.alterar(pro);
            }
            else
            {
                throw new Exception();
            }
        }
        else
        {
            throw new Exception();
        }
    }

    public List<Prontuario> buscarPorId(Prontuario pro)
    {
        if (pro.getId() > 0 && pro.getId() != null)
        {
            ProntuarioDao proDao = new ProntuarioDao();

            return proDao.buscarPorId(pro);
        }
        else
        {
            throw new CampoVazioException("Este Id não está presente");
        }
    }

    public List<Prontuario> buscarPorP_Cpf (Prontuario pro)
    {
        if (pro.getP_Cpf() != null && !pro.getP_Cpf().isEmpty())
        {
            ProntuarioDao proDao = new ProntuarioDao();

            return proDao.buscarPorP_Cpf(pro);
        }
        else
        {
            throw new CampoVazioException("Este paciente não possuí um prontuário registrado");
        }
    }

    public List<Prontuario> listar()
    {
        BaseDao<Prontuario> proDao = new ProntuarioDao();

        return proDao.listar();
    }
}
