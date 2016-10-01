package com.example.oneletrajz;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

public class ConnectionManager {
	
	private static JDBCConnectionPool pool = null;
	private static Connection connection = null;
	
	public static void createConnection() throws SQLException {
		pool = new SimpleJDBCConnectionPool(
				"oracle.jdbc.OracleDriver",
				"jdbc:oracle:thin:@localhost:1521:xe", 
				"ONELETRAJZ", 
				"12345");
		connection = pool.reserveConnection();
	}
	
	public static JDBCConnectionPool getConnectionPool() {
		return pool;
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static RowId objectToRowId(final Object o) {
		return new RowId(new Object[]{new BigDecimal(Integer.parseInt(o.toString()))});
	}

}
