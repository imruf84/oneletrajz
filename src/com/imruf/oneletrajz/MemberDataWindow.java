package com.imruf.oneletrajz;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Szem�ly adat �rlapj�nak az ablaka.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public abstract class MemberDataWindow extends Window implements SQLInsertable, SQLUpdateable, Closable {

	/**
	 * Szem�ly adatainak az �rlapjainak a t�rol�ja.
	 */
	private final MemberDataPanel memberDataPanel;

	/**
	 * Kontruktor.
	 * 
	 * @param id
	 *            szem�ly azonos�t�ja
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
	 * Objektum inicializ�l�sa.
	 */
	private void initComponents() {

		setContent(getMemberDataPanel());

		setCaption(
				getMemberDataPanel().isInsertMode() ? "�j �n�letrajz l�trehoz�sa" : "Adatok megtekint�se/m�dos�t�sa");

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
	 * Szem�ly �rlapt�rol�j�nak a lek�rdez�se.
	 * 
	 * @return szem�ly �rlap t�rol�ja
	 */
	public MemberDataPanel getMemberDataPanel() {
		return memberDataPanel;
	}

	/**
	 * �rlap bez�r�s�nak az esem�nye.
	 */
	public void onClose() {
		memberDataPanel.onClose();
	}

}
