package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.shared.ui.combobox.FilteringMode;

/**
 * V�rosok neveit tartalmaz� leg�rd�l� lista.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public class VarosokComboBox extends MyComboBox {

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kiv�tel
	 */
	public VarosokComboBox() throws SQLException {
		setCaption("V�ros:");
		setWidth("20em");
		setFilteringMode(FilteringMode.CONTAINS);
		setNewItemsAllowed(true);

		Connection c = ConnectionManager.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM VAROSOK ORDER BY NEV");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addItem(rs.getString(1));
		}

		ps.close();
	}
}
