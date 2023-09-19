package br.edu.ufersa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDaoImpl<E> implements BaseDao<E> {
    private static final String URL = "jdbc:postgresql://localhost/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "2635";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Trate ou lance a exceção, não apenas imprima
                e.printStackTrace();
            }
        }
    }

    @Override
    public abstract Long inserir(E entity);

    @Override
    public abstract void deletar(E entity);

    @Override
    public abstract void alterar(E entity);

    @Override
    public abstract List<E> listar();
}
