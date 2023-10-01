package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

import java.sql.Date;

public class Prontuario
{
    private Long id;
    private Date data;
    private String Observacoes;

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
            this.Observacoes = observacoes;
        }
        else
        {
            throw new CampoVazioException("Campo 'observações' mão pode ser vazio");
        }
    }
    public String getObservacoes()
    {return this.Observacoes;}
}
