package Model.Entity;

public class Paciente extends User
{
    private Prontuario prontuario;

    public void setProntuario(Prontuario prontuario)
    {
        if (prontuario != null)
            this.prontuario = prontuario;
    }
    public Prontuario getPronturaio()
    {return this.prontuario;}
}
