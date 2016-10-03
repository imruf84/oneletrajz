package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Felhasználó adatainak az ûrlap tárolója.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public abstract class MemberDataPanel extends Panel implements SQLInsertable, SQLUpdateable, Validable {
	
	public static enum MODE {INSERT, MODIFY};
	
	private Object id = null;
	private final MODE mode;
	private PersonalDataForm personalDataForm;
	
	/**
	 * Konstruktor.
	 * 
	 * @param id azonosító (null esetén ûres ûrlap beszúrás módban)
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
			personalDataForm = new PersonalDataForm(getMemberId());
			vl.addComponent(personalDataForm);
		} catch (SQLException e) {
			Notification.show(e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
		
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setSpacing(true);
		buttonsLayout.setSizeFull();
		
		Button okButton = new Button("Mentés");
		okButton.addStyleName("primary");
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					
					if (!isValid()) {
						Notification.show("Néhány adat nem megfelelõ!", Notification.Type.ERROR_MESSAGE);
						return;
					}
					
					if (isInsertMode()) {
						toInsert();
						return;
					}
					
					if (isModifyMode()) {
						toUpdate();
						return;
					}
					
				} catch (SQLException | FileNotFoundException e) {
					Notification.show("Hiba az adatok tárolása során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		buttonsLayout.addComponent(okButton);
		
		Button cancelButton = new Button("Mégsem");
		cancelButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				onCancel();
			}
		});
		buttonsLayout.addComponent(cancelButton);
		
		vl.addComponent(buttonsLayout);
	}

	public abstract void onCancel();
	
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

	public boolean isValid() {
		return personalDataForm.isValid();
	}
	
	public Object toInsert() throws SQLException, FileNotFoundException {
		Object newID = personalDataForm.toInsert();
		afterInsert(newID);
		return newID;
	}
	
	public void toUpdate() throws SQLException, FileNotFoundException {
		personalDataForm.toUpdate();
		afterUpdate(id);
	}
}
