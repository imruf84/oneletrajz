package com.imruf.oneletrajz;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

/**
 * Adatb�zis kapcsolat�t kezel� oszt�ly.
 * 
 * @author imruf84
 *
 */
public class ConnectionManager {
	/**
	 * Kapcsolat.
	 */
	private static JDBCConnectionPool pool = null;
	/**
	 * Kapcsolat.
	 */
	private static Connection connection = null;

	/**
	 * Kapcsolat l�trehoz�sa.
	 * 
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		pool = new SimpleJDBCConnectionPool("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe",
				"ONELETRAJZ", "12345");
		connection = pool.reserveConnection();
	}

	/**
	 * Kapcsolat lek�rdez�se.
	 * 
	 * @return kapcsolat
	 */
	public static JDBCConnectionPool getConnectionPool() {
		return pool;
	}

	/**
	 * Kapcsolat lek�rdez�se.
	 * 
	 * @return kapcsolat
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * Azonos�t� l�trehoz�sa objektumb�l.
	 * 
	 * @param o
	 *            objektum
	 * @return azonos�t�
	 */
	public static RowId objectToRowId(final Object o) {
		return new RowId(new Object[] { new BigDecimal(Integer.parseInt(o.toString())) });
	}

}
