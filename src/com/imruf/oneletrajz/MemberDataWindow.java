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
public class MemberDataWindow extends Window {
	
	private final MemberDataPanel mdp;
	
	/**
	 * Kontruktor.
	 * 
	 * @param id tag azonos�t�ja
	 */
	public MemberDataWindow(Object id) {
		
		this.mdp = new MemberDataPanel(id);
		initComponents();
	}
	
	private void initComponents() {
		
		setContent(getMemberDataPanel());
		
		setCaption(getMemberDataPanel().isInsertMode() ? "�j �n�letrajz" : "Adatok m�dos�t�sa");
		
		UI.getCurrent().addWindow(this);
		setWindowMode(WindowMode.NORMAL);
		//setWidth("70%");
		//setHeight("90%");
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
	
	
	
}
