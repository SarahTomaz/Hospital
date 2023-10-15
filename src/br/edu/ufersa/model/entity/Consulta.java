package br.edu.ufersa.model.entity;

import br.edu.ufersa.exception.CampoVazioException;

import java.sql.Date;

public class Consulta
{
    Long id;
    String medico;
    String paciente;
    Date data_consulta;

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

    public void setData_consulta(Date data_consulta)
    {
        if (data_consulta != null)
        {
            this.data_consulta = data_consulta;
        }
        else
            throw new CampoVazioException("Campo 'data' não pode ser vazio");
    }
    public Date getData_consulta()
    {return this.data_consulta;}

    public void setMedico(String nomeMedico)
    {
        if (nomeMedico != null && !nomeMedico.isEmpty())
        {
            this.medico = nomeMedico;
        }
        else
            throw new CampoVazioException("Campo 'nome_medico' não pode ser vazio");
    }
    public String getMedico()
    {return this.medico;}

    public void setPaciente(String nomePaciente)
    {
        if (nomePaciente != null && !nomePaciente.isEmpty())
        {
            this.paciente = nomePaciente;
        }
        else
            throw new CampoVazioException("Campo 'nome_paciente' não pode ser vazio");
    }
    public String getPaciente()
    {return this.paciente;}

    public boolean isValid()
    {
        return (this.id != null && this.id > 0 &&
                this.medico != null && !this.medico.isEmpty() &&
                this.paciente != null && !this.paciente.isEmpty() &&
                this.data_consulta != null);
    }
}
