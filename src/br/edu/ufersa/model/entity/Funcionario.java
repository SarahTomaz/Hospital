package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

public class Funcionario extends User
{
    private String senha;
    private String crm;
    private Double salario;
    private boolean gerente;

    public void setSenha(String senha)
    {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
        else
        {
            throw new CampoVazioException("Senha não pode ser vazia");
        }
    }
    public String getSenha()
    {return this.senha;}

    public void setCrm(String crm)
    {
        if (crm != null && !crm.isEmpty())
            this.crm = crm;
        else
        {
            throw new CampoVazioException("Crm não pode ser vazia");
        }
    }
    public String getCrm()
    {return this.crm;}

    public void setSalario(double salario)
    {
        if (salario > 0.0)
            this.salario = salario;
        else
        {
            throw new CampoVazioException("Salário deve ser positivo");
        }
    }
    public double getSalario()
    {return this.salario;}


    public void setGerente(boolean gerente)
    {
        this.gerente = gerente;
    }
    public boolean getGerente()
    {return this.gerente;}

    public boolean isValid()
    {
        return this.getNome() != null && !this.getNome().isEmpty() &&
                this.getCpf() != null && !this.getCpf().isEmpty() &&
                this.getEndereco() != null && !this.getEndereco().isEmpty() &&
                this.crm != null && !this.crm.isEmpty() &&
                this.senha != null && !this.senha.isEmpty() &&
                this.salario != null && this.salario > 0;
    }
}
