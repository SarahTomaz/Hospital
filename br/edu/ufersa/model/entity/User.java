package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

public class User
{
    private String nome;
    private String cpf;
    private String endereco;


    public void setNome(String nome)
    {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else
        {
            throw new CampoVazioException("Nome não pode ser vazio");
        }
    }
    public String getNome()
    {return this.nome;}

    public void setCpf(String cpf)
    {
        if (cpf != null && !cpf.isEmpty())
            this.cpf = cpf;
        else
        {
            throw new CampoVazioException("Cpf não pode ser vazio");
        }

    }

    public String getCpf()
    {return this.cpf;}

    public void setEndereco(String endereco)
    {
        if (endereco != null && !endereco.isEmpty())
            this.endereco = endereco;
        else
        {
            throw new CampoVazioException("Endereço não pode ser vazio");
        }
    }
    public String getEndereco()
    {return this.endereco;}
}

