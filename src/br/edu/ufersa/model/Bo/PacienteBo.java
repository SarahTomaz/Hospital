package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.PacienteDao;
import br.edu.ufersa.DAO.ProntuarioDao;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class PacienteBo
{
    public void criar(Paciente pac) throws Exception {
        if (pac.isValid())
        {
            PacienteDao pacDao = new PacienteDao();

            List<Paciente> pacList = pacDao.buscarPorCpf(pac);

            if (pacList.isEmpty())
            {
                pacDao.inserir(pac);
            }
            else
            {
                throw new Exception();
            }
        }
        else
        {
            throw new CampoVazioException("Paciente Inválido");
        }
    }

    public void deletar(Paciente pac) throws Exception
    {
            PacienteDao pacDao = new PacienteDao();
            List<Paciente> pacList = pacDao.buscarPorCpf(pac);

            if (!pacList.isEmpty())
            {
                pacDao.deletar(pac);
            }
            else
            {
                throw new Exception();
            }
    }

    public void alterar(Paciente pac) throws Exception
    {
        if (pac.isValid())
        {
            PacienteDao pacDao = new PacienteDao();
            List<Paciente> pacList = pacDao.buscarPorCpf(pac);

            if (!pacList.isEmpty())
            {
                pacDao.alterar(pac);
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

    public List<Paciente> buscarPorCpf(Paciente pac)
    {
        if (pac.getCpf() != null && !pac.getCpf().isEmpty())
        {
            PacienteDao pacDao = new PacienteDao();

            return pacDao.buscarPorCpf(pac);
        }
        else
        {
            throw new CampoVazioException("Este CPF não está presente no sistema");
        }
    }

    public List<Paciente> buscarPorNome(Paciente pac)
    {
        if (pac.getNome() != null && !pac.getNome().isEmpty())
        {
            PacienteDao pacDao = new PacienteDao();

            return pacDao.buscarPorNome(pac);
        }
        else
        {
            throw new CampoVazioException("Nome não está presente no sistema");
        }
    }

    public List<Paciente> listar()
    {
        BaseDao<Paciente> pacDao = new PacienteDao();

        return pacDao.listar();
    }
}
