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
public class SzemelyekComboBox extends MyComboBox {

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	public SzemelyekComboBox() throws SQLException {
		setCaption("Név:");
		setWidth("20em");
		setFilteringMode(FilteringMode.CONTAINS);
		setNullSelectionAllowed(false);
		setNewItemsAllowed(true);
		
		refresh();
	}
	
	@Override
	public void refresh() throws SQLException {
		
		Object val = getValue();
		
		removeAllItems();
		
		Connection c = ConnectionManager.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT VEZETEK_NEV || ' ' || KERESZT_NEV AS NEV FROM SZEMELYEK ORDER BY NEV");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addItem(rs.getString("NEV"));
		}

		rs.close();
		ps.close();
		
		setValue(val);
	}
}
