package br.edu.ufersa;
/*
import br.edu.ufersa.DAO.FuncionarioDao;
import br.edu.ufersa.DAO.BaseDao;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.entity.UserFuncionario;

public class Teste
{
    public  static void main (String[] args)
    {
        UserFuncionario func = new UserFuncionario();
        FuncionarioBo funcBo= new FuncionarioBo();

        try
        {
            func.setCrm("CRM/RN 123456");
            func.setNome("Luis Castro da Silva");
            func.setCpf("123.456.789-01");
            func.setEndereco("Rua das flores altas, N°74");
            func.setSenha("123456");
            func.setSalario(100.00);
            funcBo.criar(func);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author postgresqltutorial.com
 */
public class Teste{

    private final String url = "jdbc:postgresql:5432//localhost/postgres";
    private final String user = "postgres";
    private final String password = "2635";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Teste app = new Teste();
            app.connect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}