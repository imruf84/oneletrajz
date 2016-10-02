package com.imruf.oneletrajz;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

/**
 * Felhaszn�l�k list�j�nak komponense.
 * 
 * @author imruf84
 *
 */
@SuppressWarnings("serial")
public class MembersListPanel extends Panel {
	
	private Table membersTable;

	/**
	 * Konstruktor.
	 * 
	 * @param connection adatb�zis kapcsolat
	 */
	public MembersListPanel(final JDBCConnectionPool connection) {
		super();
		setSizeFull();
		initComponents();
	}
	
	/**
	 * Komponens inicializ�l�sa.
	 */
	private final void initComponents() {
		
		membersTable = new Table();
		membersTable.setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT_DEFAULTS_ID);
		setContent(membersTable);
		membersTable.setSizeFull();
		updateMembersList();
	}
	
	/**
	 * Kijel�lt sor azonos�t�j�nak a lek�rdez�se.
	 * 
	 * @return azonos�t�
	 */
	public Object getSelectedId() {
		return membersTable.getValue();
	}
	
	/**
	 * Kijel�lt elem megad�sa.
	 * 
	 * @param id kijel�lt elem azonos�t�ja
	 */
	public void setSelectedItem(Object id) {
		membersTable.select(id);
	}
	
	/**
	 * Felhaszn�l�k list�j�nak friss�t�se.
	 */
	public final void updateMembersList() {
		
		FreeformQuery query = new FreeformQuery(
				"SELECT ID, VEZETEK_NEV||' '||KERESZT_NEV AS NEV, SZULETESI_IDO, SZULETESI_HELY " + 
				" FROM SZEMELYEK ORDER BY NEV", ConnectionManager.getConnectionPool(), "ID");
		try {

			SQLContainer container = new SQLContainer(query);
			container.setAutoCommit(true);

			membersTable.setContainerDataSource(container);
			membersTable.setSelectable(true);
			membersTable.setNullSelectionAllowed(false);
			membersTable.setImmediate(true);
			membersTable.setVisibleColumns(new Object[] { "NEV", "SZULETESI_IDO", "SZULETESI_HELY" });
			membersTable.setColumnHeader("NEV", "N�v");
			membersTable.setColumnHeader("SZULETESI_IDO", "Sz�let�si id�");
			membersTable.setColumnHeader("SZULETESI_HELY", "Sz�let�si hely");
			
		} catch (SQLException e) {
			Notification.show("Hiba az adatok lek�rdez�se sor�n:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}
	
}