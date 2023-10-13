package br.edu.ufersa;

import br.edu.ufersa.model.Bo.*;
import br.edu.ufersa.model.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args)
    {
        PacienteBo pacBo = new PacienteBo();
        List<Paciente> pacList = pacBo.listar();

        while (!pacList.isEmpty())
        {
            System.out.println(pacList.get(0).getNome());
            pacList.remove(0);
        }
    }
}