package br.edu.ufersa.hospital.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO{
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3307/hospital";	// remover esse ":3307" ou adicionar a porta q seu BD tá
	String user = "root";
	String password = "admin";	

	synchronized public Connection getConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return conn;
		}
		else return conn;
	}
}
