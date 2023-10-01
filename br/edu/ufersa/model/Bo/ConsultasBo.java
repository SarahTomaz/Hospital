package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.ConsultaDao;
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.DAO.PacienteDao;
import br.edu.ufersa.exception.SemNomeException;
import br.edu.ufersa.model.entity.Consulta;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;

import java.util.ArrayList;
import java.util.List;

public class ConsultasBo
{
    public void criar(Consulta cons)
    {
        Funcionario t0 = new Funcionario();
        t0.setNome(cons.getNomeMedico());
        Paciente t1 = new Paciente();
        t1.setNome(cons.getNomePaciente());

        FuncionarioDao t0Dao = new FuncionarioDao();
        PacienteDao t1Dao = new PacienteDao();
        ConsultaDao consDao = new ConsultaDao();

        t0 = t0Dao.buscarPorNome(t0);
        t1 = t1Dao.buscarPorNome(t1);
        if (t0 != null && t1 != null)
        {
            consDao.inserir(cons);
        }
        else
        {
            if (t0.getNome() == null)
                throw new SemNomeException("Nome do medico não encontrado");
            else
                throw new SemNomeException("Nome do paciente não encontrado");
        }
    }

    public void deletar(Consulta cons)
    {
        ConsultaDao consDao = new ConsultaDao();

        cons.setId(ConsultaDao.buscarId(cons));
        consDao.deletar(cons);
    }

    public void alterar(Consulta cons)
    {
        ConsultaDao consDao = new ConsultaDao();

        cons.setId(ConsultaDao.buscarId(cons));
        consDao.alterar(cons);
    }

    public List<Consulta> buscarPorNomeM(Consulta cons)
    {
        ConsultaDao consDao = new ConsultaDao();

        return consDao.buscarPorNomeM(cons);
    }

    public List<Consulta> buscarPorNomeP(Consulta cons)
    {
        ConsultaDao consDao = new ConsultaDao();

        return consDao.buscarPorNomeP(cons);
    }

    public List<Consulta> listar()
    {
        ConsultaDao consDao = new ConsultaDao();

        return consDao.listar();
    }
}
