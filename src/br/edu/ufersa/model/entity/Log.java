package br.edu.ufersa.model.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Log
{
    private Date data;
    private Time hora;
    private String user;
    private String modif;
    private String tabela;

    public Date getData()
    {return data;}

    public void setData(Date data)
    {
        if (data != null)
        {
            this.data = data;
        }
    }

    public Time getHora() {return hora;}

    public void setHora(Time hora)
    {
        if (hora != null) {
            this.hora = hora;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user)
    {
        if (user != null && !user.isEmpty()) {
            this.user = user;
        }
    }

    public String getModif() {
        return modif;
    }

    public void setModif(String modif)
    {
        if (modif != null && !modif.isEmpty()) {
            this.modif = modif;
        }
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela)
    {
        if (tabela != null && !tabela.isEmpty()) {
            this.tabela = tabela;
        }
    }
}
