package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.shared.ui.combobox.FilteringMode;

/**
 * Városok neveit tartalmazó legördülõ lista.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public class VarosokComboBox extends MyComboBox {

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	public VarosokComboBox() throws SQLException {
		setCaption("Város:");
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
