package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.ConsultaDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.DAO.PacienteDao;
import br.edu.ufersa.DAO.ProntuarioDao;
import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.exception.SemNomeException;
import br.edu.ufersa.model.entity.Consulta;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class ConsultasBo
{
    public boolean autenticarNomes(Consulta cons)
    {
        FuncionarioBo funcBo = new FuncionarioBo();
        Funcionario func = new Funcionario();
        func.setNome(cons.getMedico());

        PacienteBo pacBo = new PacienteBo();
        Paciente pac = new Paciente();
        pac.setNome(cons.getPaciente());

        List<Funcionario> funcList = funcBo.buscarPorNome(func);
        List<Paciente> pacList = pacBo.buscarPorNome(pac);

        return (!funcList.isEmpty() && !pacList.isEmpty());
    }
    public Long stableId()
    {
        ConsultaDao consDao = new ConsultaDao();
        List<Consulta> consList = consDao.listar();

        if (consList.isEmpty())
        {
            return 1L;
        }
        else
        {
            return ((consList.get(consList.size() - 1).getId()) + 1);
        }
    }
    public void criar(Consulta cons)
    {
        cons.setId(stableId());

        boolean autenticado = autenticarNomes(cons);
        if (cons.isValid() && autenticado) {
            ConsultaDao conDao = new ConsultaDao();

            conDao.inserir(cons);
        }
        else
        {
            if (autenticado)
            {
                throw new CampoVazioException("Um dos campos é inválido");
            }
            else
            {
                throw new CampoVazioException("Nome do Medico ou Paciente não encontrado");
            }
        }
    }

    public void deletar(Consulta cons)
    {
        ConsultaDao consDao = new ConsultaDao();
        List<Consulta> consList = consDao.buscarPorId(cons);

        if (!consList.isEmpty())
        {
            consDao.deletar(cons);
        }
        else
        {
            throw new CampoVazioException("O id dessa consulta não foi encontrado");
        }
    }

    public void alterar(Consulta cons) throws Exception {
        if (cons.isValid())
        {
            ConsultaDao consDao = new ConsultaDao();
            List<Consulta> consList = consDao.buscarPorId(cons);

            if (!consList.isEmpty())
            {
                consDao.alterar(cons);
            }
            else
            {
                throw new Exception();
            }
        }
        else
        {
            throw new CampoVazioException("A consulta não existe");
        }
    }

    public List<Consulta> buscarPorNomeM(Consulta cons)
    {
        if (cons.getMedico() != null && !cons.getPaciente().isEmpty())
        {
            ConsultaDao consDao = new ConsultaDao();

            return consDao.buscarPorNomeM(cons);
        }
        else
        {
            throw new CampoVazioException("Este médico não esta presente no sistema");
        }
    }

    public List<Consulta> buscarPorNomeP(Consulta cons)
    {
        if (cons.getPaciente() != null && !cons.getPaciente().isEmpty())
        {
            ConsultaDao consDao = new ConsultaDao();

            return consDao.buscarPorNomeP(cons);
        }
        else
        {
            throw new CampoVazioException("Este paciente não esta presente no sistema");
        }
    }

    public List<Consulta> buscarPorData(Consulta cons)
    {
        if (cons.getData() != null)
        {
            ConsultaDao  consDao = new ConsultaDao();

            return consDao.buscarPorData(cons);
        }
        else
        {
            throw new CampoVazioException("Não há nenhuma consulta marcada para esta data");
        }
    }

    public List<Consulta> listar()
    {
        ConsultaDao consDao = new ConsultaDao();

        return consDao.listar();
    }
}
