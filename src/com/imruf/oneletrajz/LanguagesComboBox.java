package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.shared.ui.combobox.FilteringMode;

/**
 * Nyelvek neveit tartalmazó legördülõ lista.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public class LanguagesComboBox extends MyComboBox {

	/**
	 * Igaz esetén csak az adatbázisban tárolt nyelvek jelennek meg.
	 */
	private boolean onlyStored;

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	public LanguagesComboBox(boolean onlyStored) throws SQLException {
		
		this.onlyStored = onlyStored;
		setCaption("Nyelv:");
		setWidth("10em");
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
		PreparedStatement ps;
		if (onlyStored)
			ps = c.prepareStatement("SELECT DISTINCT NYELV FROM NYELVTUDAS ORDER BY NYELV");			
		else
			ps = c.prepareStatement("SELECT * FROM ((SELECT DISTINCT NYELV FROM NYELVTUDAS) UNION (SELECT DISTINCT NYELV FROM NYELVEK)) ORDER BY NYELV");
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addItem(rs.getString(1));
		}

		rs.close();
		ps.close();
		
		setValue(val);
	}
	
	
}
