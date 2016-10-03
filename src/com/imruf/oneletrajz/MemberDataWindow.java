package com.imruf.oneletrajz;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Tag adatainak az ablaka.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public abstract class MemberDataWindow extends Window implements SQLInsertable, SQLUpdateable, Closable {
	
	private final MemberDataPanel mdp;
	
	/**
	 * Kontruktor.
	 * 
	 * @param id tag azonosítója
	 */
	public MemberDataWindow(Object id) {
		
		final MemberDataWindow mdw = this;
		
		this.mdp = new MemberDataPanel(id) {
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
	
	private void initComponents() {
		
		setContent(getMemberDataPanel());
		
		setCaption(getMemberDataPanel().isInsertMode() ? "Új önéletrajz" : "Adatok módosítása");
		
		UI.getCurrent().addWindow(this);
		setWindowMode(WindowMode.NORMAL);
		setSizeUndefined();
		setVisible(true);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		setClosable(false);		
	}

	public MemberDataPanel getMemberDataPanel() {
		return mdp;
	}
	
	public void onClose() {
		mdp.onClose();
	}
	
}
