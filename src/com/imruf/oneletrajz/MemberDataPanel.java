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
public abstract class MemberDataPanel extends Panel implements SQLInsertable, SQLUpdateable, Validable, Closable {

	/**
	 * Ûrlap módjai.
	 */
	public static enum MODE {
		INSERT, MODIFY
	};
	// TODO: általános ûrlap tároló az egységesen végrehajtandó mûveletek könnyítésére (isValid, toInsert, toUpdate stb.)
	/**
	 * Aktuális személy azonosítója.
	 */
	private Object id = null;
	/**
	 * Ûrlap aktuális módja.
	 */
	private final MODE mode;
	/**
	 * Személyes adatok ûrlapja.
	 */
	private PersonalDataForm personalDataForm;
	/**
	 * Tanulmányok tárolója.
	 */
	private TanulmanyokPanel tanulmanyokPanel;

	/**
	 * Konstruktor.
	 * 
	 * @param id
	 *            azonosító (null esetén üres ûrlap beszúrás módban)
	 */
	public MemberDataPanel(Object id) {

		this.id = id;
		this.mode = (null == id ? MODE.INSERT : MODE.MODIFY);

		initComponents();
	}

	/**
	 * Objektum inicializálása.
	 */
	private void initComponents() {

		setSizeUndefined();

		VerticalLayout vl = new VerticalLayout();
		vl.setSizeFull();
		vl.setMargin(true);
		setContent(vl);

		try {
			personalDataForm = new PersonalDataForm(getMemberId());
			vl.addComponent(personalDataForm);
			
			tanulmanyokPanel = new TanulmanyokPanel(getMemberId());
			vl.addComponent(tanulmanyokPanel);
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
						Notification.show("Néhány adat nem megfelelõ, vagy hiányzik!", Notification.Type.ERROR_MESSAGE);
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
					Notification.show("Hiba az adatok tárolása során:\n" + e.getLocalizedMessage(),
							Notification.Type.ERROR_MESSAGE);
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

	/**
	 * Ûrlap kitöltésének félbe szakítása (pl. Mégsem gombra kattintással).
	 */
	public void onCancel() {
		onClose();
	}

	/**
	 * Ûrlap módjának a lekérdezése.
	 * 
	 * @return ûrlap módja
	 */
	public MODE getMode() {
		return mode;
	}

	/**
	 * Létrehozás módban vagyunk?
	 * 
	 * @return igaz esetén létrehozás módban vagyunk
	 */
	public boolean isInsertMode() {
		return mode == MODE.INSERT;
	}

	/**
	 * Módosítás módban vagyunk?
	 * 
	 * @return igaz esetén módosítás módban vagyunk
	 */
	public boolean isModifyMode() {
		return mode == MODE.MODIFY;
	}

	/**
	 * Személy azonosítájának a lekérdezése.
	 * 
	 * @return személy azonosítója
	 */
	public Object getMemberId() {
		return id;
	}

	/**
	 * Ûrlap helyességének a lekérdezése.
	 * 
	 * @return igaz esetén az ûrlap helyesen van kitöltve
	 */
	public boolean isValid() {
		return personalDataForm.isValid() && tanulmanyokPanel.isValid();
	}

	/**
	 * Új adat beszúrása adatbázisba.
	 * 
	 * @return az újonnan beszúrt személy azonosítója
	 */
	public Object toInsert() throws SQLException, FileNotFoundException {
		Object newID = personalDataForm.toInsert();
		tanulmanyokPanel.setPersonID(newID);
		tanulmanyokPanel.toInsert();
		afterInsert(newID);
		return newID;
	}

	/**
	 * Adott személy adatainak módosítása az adatbázisban.
	 */
	public void toUpdate() throws SQLException, FileNotFoundException {
		personalDataForm.toUpdate();
		tanulmanyokPanel.toUpdate();
		afterUpdate(id);
	}

	/**
	 * Ûrlap bezárásának az eseménye.
	 */
	public void onClose() {
		personalDataForm.onClose();
		tanulmanyokPanel.onClose();
	}
}
