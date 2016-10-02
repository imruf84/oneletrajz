package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.shared.ui.combobox.FilteringMode;

/**
 * V�rosok neveit tartalmaz� leg�rd�l� lista.
 * @author imruf84
 */
@SuppressWarnings("serial")
public class VarosokComboBox extends MyComboBox {
	
	/**
	 * Konstruktor.
	 * 
	 * @param connection adatb�zis kapcsolat
	 */
	public VarosokComboBox() throws SQLException {
		setCaption("V�ros:");
		setWidth("20em");
		setFilteringMode(FilteringMode.CONTAINS);
		setNewItemsAllowed(true);
		
		Connection c = ConnectionManager.getConnectionPool().reserveConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM VAROSOK ORDER BY NEV");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addItem(rs.getString(1));
		}
		
		ps.close();
	}
}