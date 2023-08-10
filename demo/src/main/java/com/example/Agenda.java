package com.example;
//import paciente;
//import medico;

import java.time.LocalDateTime;


public class Agenda {
    private String NomePaciente;
    private String NomeMedico;
    private LocalDateTime HoradaConsulta;
    private boolean Status;

    public Agenda(){}
    public Agenda(String NomePaciente, String NomeMedico, LocalDateTime HoradaConsulta, boolean Status)
    {
        this.NomePaciente = NomePaciente;
        this.NomeMedico = NomeMedico;
        this.HoradaConsulta = HoradaConsulta;
        this.Status = Status;
    }

    public boolean cancelarConsulta()
    {
        System.out.println("Consulta cancelada");
        return true;
    }


    public boolean confirmarConsulta(String nomePaciente, String nomeMedico)
    {
        System.out.println(nomePaciente + "Consulta confirmada");
        System.out.println(nomeMedico + "Consulta confirmada");
        return true;
    }
    public boolean confirmarConsulta(LocalDateTime horadaConsulta)
    {
        System.out.println(horadaConsulta + "Consulta confirmada");
        return true;
    }
}

