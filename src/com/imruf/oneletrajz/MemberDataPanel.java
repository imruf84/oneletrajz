package com.imruf.oneletrajz;

import java.sql.SQLException;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
		vl.setSizeFull();
		vl.setMargin(true);
		setContent(vl);
		
		try {
			vl.addComponent(new PersonalDataForm(getMemberId()));
		} catch (SQLException e) {
			Notification.show(e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
		
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setSpacing(true);
		buttonsLayout.setSizeFull();
		
		Button okButton = new Button("Ment�s");
		buttonsLayout.addComponent(okButton);
		
		Button cancelButton = new Button("M�gsem");
		buttonsLayout.addComponent(cancelButton);
		
		vl.addComponent(buttonsLayout);
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
