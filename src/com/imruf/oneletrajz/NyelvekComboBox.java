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
public class NyelvekComboBox extends MyComboBox {

	private boolean onlyStored;

	/**
	 * Konstruktor.
	 * 
	 * @throws SQLException
	 *             kivétel
	 */
	public NyelvekComboBox(boolean onlyStored) throws SQLException {
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
