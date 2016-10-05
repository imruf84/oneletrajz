package com.imruf.oneletrajz;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

/**
 * Személyek listájának komponense.
 * 
 * @author imruf84
 *
 */
@SuppressWarnings("serial")
public abstract class MembersListPanel extends Panel {

	/**
	 * Személyek alapadatait megjelenítõ táblázat.
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
	 * Komponens inicializálása.
	 */
	@SuppressWarnings("deprecation")
	private final void initComponents() {

		setSizeFull();

		membersTable = new Table();

		// TODO: lecserélni egy támogatottra
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
	 * Kijelölt sorban lébõ személy azonosítójának a lekérdezése.
	 * 
	 * @return azonosító
	 */
	public Object getSelectedId() {
		return membersTable.getValue();
	}

	/**
	 * Kijelölt személy megadása az azonosítója alapján.
	 * 
	 * @param id
	 *            kijelölni kívánt személy azonosítója
	 */
	public void setSelectedItem(Object id) {
		membersTable.select(id);
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	/**
	 * Személyek listájának a frissítése.
	 * 
	 * @param filter szûrõ
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
			membersTable.setColumnHeader("NEV", "Név");
			membersTable.setColumnHeader("SZULETESI_IDO", "Születési idõ");
			membersTable.setColumnHeader("SZULETESI_HELY", "Születési hely");
			membersTable.setColumnHeader("FOTO_VAN", "Fotó");

		} catch (SQLException e) {
			Notification.show("Hiba az adatok lekérdezése során:\n" + e.getLocalizedMessage(),
					Notification.Type.ERROR_MESSAGE);
		}
		
		setSelectedItem(id);
	}
	
	/**
	 * Táblázat sorára való dupla kattintás eseménye.
	 * 
	 * @param id
	 *            adott sorhoz tartozó személy azonosítója
	 */
	public abstract void onDoubleClick(Object id);

}
