package br.edu.ufersa.model.entity;

public class Paciente extends User
{
    private Prontuario prontuario;
    public void setProntuario(Prontuario prontuario)
    {
        this.prontuario = prontuario;
    }
    public Prontuario getProntuario()
    {return this.prontuario;}
}
