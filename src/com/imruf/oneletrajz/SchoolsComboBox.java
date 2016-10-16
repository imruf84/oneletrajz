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
public class SchoolsComboBox extends MyComboBox {

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	public SchoolsComboBox() throws SQLException {
		setCaption("Intézmény:");
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
		PreparedStatement ps = c.prepareStatement("SELECT DISTINCT INTEZMENY FROM TANULMANYOK ORDER BY INTEZMENY");			
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addItem(rs.getString(1));
		}

		rs.close();
		ps.close();
		
		setValue(val);
	}
}
