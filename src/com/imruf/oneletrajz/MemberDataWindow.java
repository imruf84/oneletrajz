package com.imruf.oneletrajz;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Személy adat ûrlapjának az ablaka.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public abstract class MemberDataWindow extends Window implements SQLInsertable, SQLUpdateable, Closable {

	/**
	 * Személy adatainak az ûrlapjainak a tárolója.
	 */
	private final MemberDataPanel memberDataPanel;

	/**
	 * Kontruktor.
	 * 
	 * @param id
	 *            személy azonosítója
	 */
	public MemberDataWindow(Object id) {

		final MemberDataWindow mdw = this;

		this.memberDataPanel = new MemberDataPanel(id) {
			public void afterInsert(Object newID) {
				mdw.close();
				mdw.afterInsert(newID);
				onClose();
			}

			public void afterUpdate(Object id) {
				mdw.close();
				mdw.afterUpdate(id);
				onClose();
			}

			public void onCancel() {
				mdw.close();
				onClose();
			}
		};

		initComponents();
	}

	/**
	 * Objektum inicializálása.
	 */
	private void initComponents() {

		setContent(getMemberDataPanel());

		setCaption(
				getMemberDataPanel().isInsertMode() ? "Új önéletrajz létrehozása" : "Adatok megtekintése/módosítása");

		UI.getCurrent().addWindow(this);
		setWindowMode(WindowMode.NORMAL);
		setSizeUndefined();
		setVisible(true);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		setClosable(false);
	}

	/**
	 * Személy ûrlaptárolójának a lekérdezése.
	 * 
	 * @return személy ûrlap tárolója
	 */
	public MemberDataPanel getMemberDataPanel() {
		return memberDataPanel;
	}

	/**
	 * Ûrlap bezárásának az eseménye.
	 */
	public void onClose() {
		memberDataPanel.onClose();
	}

}
