package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class KnownLanguagesPanel extends VerticalLayout implements Validable, SQLInsertable, SQLUpdateable, Closable, SQLSelectable {

	/**
	 * Szem�ly azonos�t�ja.
	 */
	private Object personID;
	/**
	 * Besz�lt nyelvek �rlapjainak t�rol�ja.
	 */
	private final LinkedList<LanguageForm> nyelvForms = new LinkedList<>();
	/**
	 * T�rol�.
	 */
	private VerticalLayout vl[];

	/**
	 * Konstruktor.
	 * 
	 * @param personID szem�ly azonos�t�ja
	 * @throws SQLException kiv�tel
	 */
	public KnownLanguagesPanel(Object personID) throws SQLException {
		this.personID = personID;
		initComponents();
		getDataById();
	}

	/**
	 * Komponensek inicializ�l�sa.
	 */
	private void initComponents() {
		
		setCaption("Nyelvtud�s");
		setMargin(true);
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		String captions[] = {"Nyelv:", "T�r�l:"};
		vl = new VerticalLayout[captions.length];
		for (int i = 0; i < vl.length; i++) {
			vl[i] = new VerticalLayout();
			hl.addComponent(vl[i]);
			vl[i].addComponent(new Label(captions[i]));
		}
		addComponent(hl);
		
		Button newRowButton = new Button("�j sor", new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					addRow(null);
				} catch (SQLException e) {
				}
			}
		});
		newRowButton.addStyleName(BaseTheme.BUTTON_LINK);
		addComponent(newRowButton);
	}

	/**
	 * Szem�ly azonos�t�j�nak a megad�sa.
	 * 
	 * @param personID szem�ly azonos�t�ja
	 */
	public void setPersonID(Object personID) {
		this.personID = personID;
		
		for (LanguageForm tf : nyelvForms) {
			tf.setPersonID(personID);
		}
	}
	
	@Override
	public void onClose() {
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		
		for (LanguageForm tf : nyelvForms) {
			tf.toUpdate();
		}
	}

	@Override
	public void afterUpdate(Object id) {
		onClose();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		for (LanguageForm tf : nyelvForms) {
			tf.toInsert();
		}
		
		return null;
	}

	@Override
	public void afterInsert(Object newID) {
		
		for (LanguageForm tf : nyelvForms) {
			tf.afterInsert(newID);
		}
		
		onClose();
	}

	@Override
	public boolean isValid() {
		
		for (LanguageForm tf : nyelvForms) {
			if (!tf.isValid()) return false;
		}
		
		return true;
	}

	/**
	 * �j sor besz�r�sa.
	 * 
	 * @param id adott besz�lt nyelv azonos�t�ja
	 * @throws SQLException kiv�tel
	 */
	private void addRow(Object id) throws SQLException {
		
		LanguageForm tf = new LanguageForm(id, personID);
		vl[0].addComponent(tf.getLanguageTF());
		vl[1].addComponent(tf.getRemoveCB());
		vl[1].setComponentAlignment(tf.getRemoveCB(), Alignment.MIDDLE_CENTER);
		nyelvForms.add(tf);
		
		// Ablak k�z�pre igaz�t�sa a megn�vekedett m�ret miatt.
		Utils.centerWindows();
	}
	
	@Override
	public void getDataById() throws SQLException {
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT ID FROM NYELVTUDAS WHERE SZEMELY_ID=" + personID + " ORDER BY NYELV");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addRow(rs.getInt("ID"));
		}
		rs.close();
		ps.close();
		
	}
	
}
