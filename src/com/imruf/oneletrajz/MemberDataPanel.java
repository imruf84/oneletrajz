package com.imruf.oneletrajz;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Felhaszn�l� adatainak az �rlap t�rol�ja.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public class MemberDataPanel extends Panel {
	
	public static enum MODE {INSERT, MODIFY};
	
	private Object id = null;
	private final MODE mode;
	
	/**
	 * Konstruktor.
	 * 
	 * @param id azonos�t� (null eset�n �res �rlap besz�r�s m�dban)
	 */
	public MemberDataPanel(Object id) {
		
		this.id = id;
		this.mode = (null == id ? MODE.INSERT : MODE.MODIFY);
		
		initComponents();
	}
	
	private void initComponents() {
		
		setSizeUndefined();
		
		VerticalLayout vl = new VerticalLayout();
		vl.setMargin(true);
		setContent(vl);
		
		vl.addComponent(new PersonalDataForm(getMemberId()));
	}

	public MODE getMode() {
		return mode;
	}
	
	public boolean isInsertMode() {
		return mode == MODE.INSERT;
	}
	
	public boolean isModifyMode() {
		return mode == MODE.MODIFY;
	}

	public Object getMemberId() {
		return id;
	}
	
	
}
