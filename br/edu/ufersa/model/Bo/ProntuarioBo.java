package br.edu.ufersa.model.Bo;

import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.DAO.ProntuarioDao;
import br.edu.ufersa.model.entity.Prontuario;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioBo
{
    public void alterar(Prontuario pro)
    {
        BaseDao<Prontuario> proDao = new ProntuarioDao();

        proDao.alterar(pro);
    }

    public List<Prontuario> listar()
    {
        BaseDao<Prontuario> proDao = new ProntuarioDao();
        List<Prontuario> pr = new ArrayList<Prontuario>();

        pr = proDao.listar();
        return pr;
    }
}
