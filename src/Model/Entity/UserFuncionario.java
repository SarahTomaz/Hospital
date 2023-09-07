package Model.Entity;

public class UserFuncionario extends User
{
    private String senha;
    private String crm;
    private double salario;

    public void setSenha(String senha)
    {
        if (senha != null && senha.isEmpty())
            this.senha = senha;
        //TODO else
    }
    public String getSenha()
    {return this.senha;}

    public void setCrm(String crm)
    {
        if (crm != null && crm.isEmpty())
            this.crm = crm;
        //TODO else
    }
    public String getCrm()
    {return this.crm;}

    public void setSalario(double salario)
    {
        if (salario > 0.0)
            this.salario = salario;
        //TODO else
    }


}
