package br.edu.ufersa;

import br.edu.ufersa.model.Bo.*;
import br.edu.ufersa.model.entity.*;

import java.sql.Date;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        Funcionario fc = new Funcionario();
        Paciente pc = new Paciente();
        Prontuario pr = new Prontuario();
        Consulta cons = new Consulta();

        FuncionarioBo fcBo = new FuncionarioBo();
        PacienteBo pcBo = new PacienteBo();
        ProntuarioBo prBo = new ProntuarioBo();
        ConsultasBo consBo = new ConsultasBo();

        fc.setNome("a");
        fc.setCpf("1");
        fc.setEndereco("a");
        fc.setCrm("1");
        fc.setSenha("a");
        fc.setSalario(1);
        fc.setGerente(true);

        pc.setNome("b");
        pc.setEndereco("b");
        pc.setCpf("2");
        pc.setProntuario(pr);

        pr.setData(Date.valueOf("2000-09-09"));
        pr.setObservacoes("ccc");

        cons.setNomeMedico("a");
        cons.setNomePaciente("b");
        cons.setData(Date.valueOf("2000-09-09"));
    }
}