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
 * Felhaszn�l� adatainak az �rlap t�rol�ja.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public abstract class MemberDataPanel extends Panel implements SQLInsertable, SQLUpdateable, Validable, Closable {

	/**
	 * �rlap m�djai.
	 */
	public static enum MODE {
		INSERT, MODIFY
	};
	// TODO: �ltal�nos �rlap t�rol� az egys�gesen v�grehajtand� m�veletek k�nny�t�s�re (isValid, toInsert, toUpdate stb.)
	/**
	 * Aktu�lis szem�ly azonos�t�ja.
	 */
	private Object id = null;
	/**
	 * �rlap aktu�lis m�dja.
	 */
	private final MODE mode;
	/**
	 * Szem�lyes adatok �rlapja.
	 */
	private PersonalDataForm personalDataForm;
	/**
	 * Tanulm�nyok t�rol�ja.
	 */
	private TanulmanyokPanel tanulmanyokPanel;

	/**
	 * Konstruktor.
	 * 
	 * @param id
	 *            azonos�t� (null eset�n �res �rlap besz�r�s m�dban)
	 */
	public MemberDataPanel(Object id) {

		this.id = id;
		this.mode = (null == id ? MODE.INSERT : MODE.MODIFY);

		initComponents();
	}

	/**
	 * Objektum inicializ�l�sa.
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

		Button okButton = new Button("Ment�s");
		okButton.addStyleName("primary");
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {

					if (!isValid()) {
						Notification.show("N�h�ny adat nem megfelel�, vagy hi�nyzik!", Notification.Type.ERROR_MESSAGE);
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
					Notification.show("Hiba az adatok t�rol�sa sor�n:\n" + e.getLocalizedMessage(),
							Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		buttonsLayout.addComponent(okButton);

		Button cancelButton = new Button("M�gsem");
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
	 * �rlap kit�lt�s�nek f�lbe szak�t�sa (pl. M�gsem gombra kattint�ssal).
	 */
	public void onCancel() {
		onClose();
	}

	/**
	 * �rlap m�dj�nak a lek�rdez�se.
	 * 
	 * @return �rlap m�dja
	 */
	public MODE getMode() {
		return mode;
	}

	/**
	 * L�trehoz�s m�dban vagyunk?
	 * 
	 * @return igaz eset�n l�trehoz�s m�dban vagyunk
	 */
	public boolean isInsertMode() {
		return mode == MODE.INSERT;
	}

	/**
	 * M�dos�t�s m�dban vagyunk?
	 * 
	 * @return igaz eset�n m�dos�t�s m�dban vagyunk
	 */
	public boolean isModifyMode() {
		return mode == MODE.MODIFY;
	}

	/**
	 * Szem�ly azonos�t�j�nak a lek�rdez�se.
	 * 
	 * @return szem�ly azonos�t�ja
	 */
	public Object getMemberId() {
		return id;
	}

	/**
	 * �rlap helyess�g�nek a lek�rdez�se.
	 * 
	 * @return igaz eset�n az �rlap helyesen van kit�ltve
	 */
	public boolean isValid() {
		return personalDataForm.isValid() && tanulmanyokPanel.isValid();
	}

	/**
	 * �j adat besz�r�sa adatb�zisba.
	 * 
	 * @return az �jonnan besz�rt szem�ly azonos�t�ja
	 */
	public Object toInsert() throws SQLException, FileNotFoundException {
		Object newID = personalDataForm.toInsert();
		tanulmanyokPanel.setPersonID(newID);
		tanulmanyokPanel.toInsert();
		afterInsert(newID);
		return newID;
	}

	/**
	 * Adott szem�ly adatainak m�dos�t�sa az adatb�zisban.
	 */
	public void toUpdate() throws SQLException, FileNotFoundException {
		personalDataForm.toUpdate();
		tanulmanyokPanel.toUpdate();
		afterUpdate(id);
	}

	/**
	 * �rlap bez�r�s�nak az esem�nye.
	 */
	public void onClose() {
		personalDataForm.onClose();
		tanulmanyokPanel.onClose();
	}
}
