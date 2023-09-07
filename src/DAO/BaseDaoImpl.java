package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDaoImpl<E> implements BaseDao<E>
{
        final static String URL = "jdbc:mysql://localhost/Artur";
        final static String USER = "Luis";
        final static String PASS = "medicina";
        static Connection con = null;

        public static Connection getConnection() {
            if(con == null)
            {
                try
                {
                    con = DriverManager.getConnection(URL,USER,PASS);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return con;

        }

    public static void closeConnection()
    {
        if(con !=null)
        {
            try {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            con = null;
        }
    }

    public abstract Long inserir(E entity);
    public abstract void deletar(E entity);
    public abstract void alterar(E entity);
    public abstract E buscar(E entity);
    public abstract List<E> listar();
}
