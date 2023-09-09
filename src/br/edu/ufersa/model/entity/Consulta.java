package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

import java.sql.Date;

public class Consulta
{
    Long id;
    String nomeMedico;
    String nomePaciente;
    Date data;

    public void setId(Long id)
    {
        if (id != null && id > 0)
        {
            this.id = id;
        }
        else
            throw new CampoVazioException("Campo 'id' não pode ser vazio");
    }
    public Long getId()
    {return this.id;}

    public void setData(Date data)
    {
        if (data != null)
        {
            this.data = data;
        }
        else
            throw new CampoVazioException("Campo 'data' não pode ser vazio");
    }
    public Date getData()
    {return this.data;}

    public void setNomeMedico(String nomeMedico)
    {
        if (nomeMedico != null && !nomeMedico.isEmpty())
        {
            this.nomeMedico = nomeMedico;
        }
        else
            throw new CampoVazioException("Campo 'nome_medico' não pode ser vazio");
    }
    public String getNomeMedico()
    {return this.nomeMedico;}

    public void setNomePaciente(String nomePaciente)
    {
        if (nomePaciente != null && !nomePaciente.isEmpty())
        {
            this.nomePaciente = nomePaciente;
        }
        else
            throw new CampoVazioException("Campo 'nome_paciente' não pode ser vazio");
    }
    public String getNomePaciente()
    {return this.nomePaciente;}
}
