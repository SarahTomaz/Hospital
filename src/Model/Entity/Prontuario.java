package Model.Entity;

public class Prontuario
{
    private String tipoABO;
    private String alergias;
    private String medicacao;
    private String sinaisvitais;

    public void setTipoABO(String tipoABO)
    {
        if (tipoABO != null && tipoABO.isEmpty())
            this.tipoABO = tipoABO;
        //TODO else
    }
    public String getTipoABO()
    {return this.tipoABO;}

    public void setAlergias(String alergias)
    {
        if (alergias != null && alergias.isEmpty())
            this.alergias = alergias;
        //TODO else
    }
    public String getAlergias()
    {return this.alergias;}

    public void setMedicacao(String medicacao)
    {
        if (medicacao != null && medicacao.isEmpty())
            this.medicacao = medicacao;
        //TODO else
    }
    public String getMedicacao()
    {return this.medicacao;}

    public void setSinaisVitais(String sinaisvitais)
    {
        if (sinaisvitais != null && sinaisvitais.isEmpty())
            this.sinaisvitais = sinaisvitais;
        //TODO else
    }
    public String getSinaisVitais()
    {return this.sinaisvitais;}
}
