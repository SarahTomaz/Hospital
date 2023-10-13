package br.edu.ufersa.model.entity;

public class Paciente extends User
{
    private int idade;
    public void setIdade(int idade)
    {
        if (idade > -1)
        {
            this.idade = idade;
        }
    }
    public int getIdade()
    {return this.idade;}

    public boolean isValid()
    {
        return this.getNome() != null && !this.getNome().isEmpty() &&
                this.getCpf() != null && !this.getCpf().isEmpty() &&
                this.getEndereco() != null && !this.getEndereco().isEmpty() &&
                this.idade > -1;
    }
}
