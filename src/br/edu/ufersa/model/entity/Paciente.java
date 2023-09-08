package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

public class Paciente extends User
{
    private Prontuario prontuario;
    public void setProntuario(Prontuario prontuario)
    {
        this.prontuario = prontuario;
    }
    public Prontuario getPronturaio()
    {return this.prontuario;}
}
