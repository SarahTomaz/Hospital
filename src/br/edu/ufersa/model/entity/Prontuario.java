package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

import java.sql.Date;

public class Prontuario
{
    private Long id;
    private String p_Cpf;
    private Date data;
    private String observacoes;

    public void setId(Long id)
    {
        if (id > 0)
        {
            this.id = id;
        }
        else
            throw new CampoVazioException("Campo 'Id' não pode ser negativo");
    }
    public Long getId()
    {return this.id;}

    public void setP_Cpf(String p_Cpf)
    {
        if (p_Cpf != null && !p_Cpf.isEmpty())
        {
            this.p_Cpf = p_Cpf;
        }
        else
        {
            throw new CampoVazioException("Campo 'p_Cpf' não pode ser vazio");
        }
    }
    public String getP_Cpf()
    {return this.p_Cpf;}

    public void setData(Date data)
    {
        if (data != null)
        {


            this.data = data;
        }
        else {
            throw new CampoVazioException("Campo 'Data' não pode ser vazio");
        }
    }
    public Date getData()
    {return this.data;}

    public void setObservacoes(String observacoes)
    {
        if (observacoes != null && !observacoes.isEmpty())
        {
            this.observacoes = observacoes;
        }
        else
        {
            throw new CampoVazioException("Campo 'observações' mão pode ser vazio");
        }
    }
    public String getObservacoes()
    {return this.observacoes;}

    public boolean isValid()
    {
        return this.id > 0 && this.data != null &&
                this.observacoes != null && !this.observacoes.isEmpty() &&
                this.p_Cpf != null && !this.p_Cpf.isEmpty();
    }
}
