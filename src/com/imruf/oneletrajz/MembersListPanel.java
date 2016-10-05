package com.imruf.oneletrajz;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

/**
 * Szem�lyek list�j�nak komponense.
 * 
 * @author imruf84
 *
 */
@SuppressWarnings("serial")
public abstract class MembersListPanel extends Panel {

	/**
	 * Szem�lyek alapadatait megjelen�t� t�bl�zat.
	 */
	private Table membersTable;
	private String filter = "";

	/**
	 * Konstruktor.
	 */
	public MembersListPanel() {
		super();

		initComponents();
	}

	/**
	 * Komponens inicializ�l�sa.
	 */
	@SuppressWarnings("deprecation")
	private final void initComponents() {

		setSizeFull();

		membersTable = new Table();

		// TODO: lecser�lni egy t�mogatottra
		membersTable.addListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					onDoubleClick(getSelectedId());
				}
			}
		});

		membersTable.setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT_DEFAULTS_ID);
		setContent(membersTable);
		membersTable.setSizeFull();
		updateMembersList();
	}

	/**
	 * Kijel�lt sorban l�b� szem�ly azonos�t�j�nak a lek�rdez�se.
	 * 
	 * @return azonos�t�
	 */
	public Object getSelectedId() {
		return membersTable.getValue();
	}

	/**
	 * Kijel�lt szem�ly megad�sa az azonos�t�ja alapj�n.
	 * 
	 * @param id
	 *            kijel�lni k�v�nt szem�ly azonos�t�ja
	 */
	public void setSelectedItem(Object id) {
		membersTable.select(id);
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	/**
	 * Szem�lyek list�j�nak a friss�t�se.
	 * 
	 * @param filter sz�r�
	 */
	public final void updateMembersList() {

		Object id = getSelectedId();
		
		FreeformQuery query = new FreeformQuery(
				"SELECT * FROM (SELECT ID, VEZETEK_NEV||' '||KERESZT_NEV AS NEV, SZULETESI_IDO, SZULETESI_HELY, DECODE(LENGTH(FOTO), NULL, '', 'X') AS FOTO_VAN "
						+ " FROM SZEMELYEK) " + (filter.isEmpty() ? "" : " WHERE ") + filter + " ORDER BY NEV",
				ConnectionManager.getConnectionPool(), "ID");
		try {

			SQLContainer container = new SQLContainer(query);
			container.setAutoCommit(true);

			membersTable.setContainerDataSource(container);
			membersTable.setSelectable(true);
			membersTable.setNullSelectionAllowed(false);
			membersTable.setImmediate(true);
			membersTable.setVisibleColumns(new Object[] { "NEV", "SZULETESI_IDO", "SZULETESI_HELY", "FOTO_VAN" });
			membersTable.setColumnHeader("NEV", "N�v");
			membersTable.setColumnHeader("SZULETESI_IDO", "Sz�let�si id�");
			membersTable.setColumnHeader("SZULETESI_HELY", "Sz�let�si hely");
			membersTable.setColumnHeader("FOTO_VAN", "Fot�");

		} catch (SQLException e) {
			Notification.show("Hiba az adatok lek�rdez�se sor�n:\n" + e.getLocalizedMessage(),
					Notification.Type.ERROR_MESSAGE);
		}
		
		setSelectedItem(id);
	}
	
	/**
	 * T�bl�zat sor�ra val� dupla kattint�s esem�nye.
	 * 
	 * @param id
	 *            adott sorhoz tartoz� szem�ly azonos�t�ja
	 */
	public abstract void onDoubleClick(Object id);

}
