package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.PacienteDao;
import br.edu.ufersa.DAO.ProntuarioDao;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.model.entity.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class PacienteBo
{
    Paciente pc = null;

    BaseDao<Paciente> pacDao = new PacienteDao();
    public void criar(Paciente pac)
    {
        Prontuario pro = pac.getProntuario();
        BaseDao<Prontuario> proDao = new ProntuarioDao();

        pro.setId(proDao.inserir(pro));
        pac.setProntuario(pro);
        pacDao.inserir(pac);
    }

    public void deletar(Paciente pac)
    {
        Prontuario pro = pac.getProntuario();
        ProntuarioDao proDao = new ProntuarioDao();

        pacDao.deletar(pac);
        pro.setId(proDao.buscarId(pro));
        proDao.deletar(pro);
        pro = null;
    }

    public void alterar(Paciente pac)
    {
        pacDao.alterar(pac);
    }

    public Paciente buscarPorCpf(Paciente pac)
    {
       PacienteDao pacDao1 = new PacienteDao();

           pc = pacDao1.buscarPorCpf(pac);
           return pc;
    }

    public Paciente buscarPorNome(Paciente pac)
    {
        PacienteDao pacDao1 = new PacienteDao();

        pc = pacDao1.buscarPorNome(pac);
        return pc;
    }

    public List<Paciente> listar()
    {
        List<Paciente> pac = new ArrayList<Paciente>();
        BaseDao<Paciente> pacDao = new PacienteDao();

        pac = pacDao.listar();
        return pac;
    }

}
