package Model.Entity;

import java.time.LocalDate;

public abstract class User
{
    private String nome;
    private String cpf;
    private String endereco;


    public void setNome(String nome)
    {
        if (nome != null && nome.isEmpty())
            this.nome = nome;
        //TODO else
    }
    public String getNome()
    {return this.nome;}

    public void setCpf(String cpf)
    {
        if (cpf != null && cpf.isEmpty())
            this.cpf = cpf;}
        //TODO else
    public String getCpf()
    {return this.cpf;}

    public void setEndereco(String endereco)
    {
        if (endereco != null && endereco.isEmpty())
            this.endereco = endereco;
        //TODO else
    }
    public String getEndereco()
    {return this.endereco;}

}

