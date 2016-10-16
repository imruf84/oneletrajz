package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class LanguageForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable, SQLSelectable {

	/**
	 * Besz�lt nyelv azonos�t�ja.
	 */
	private final Object id;
	/**
	 * Szem�ly azonos�t�ja.
	 */
	private Object memberId;
	/**
	 * Nyelv.
	 */
	private LanguagesComboBox languageCB;
	/**
	 * T�rl�s jel�l�je.
	 */
	private CheckBox removeCB;
	
	/**
	 * Konstruktor.
	 * 
	 * @param id besz�lt nyelv azonos�t�ja
	 * @param memberId szem�ly azonos�t�ja
	 * @throws SQLException kiv�tel
	 */
	public LanguageForm(Object id, Object memberId) throws SQLException {
		this.id = id;
		this.memberId = memberId;
		
		initComponents();
	}

	/**
	 * Komponensek inicializ�l�sa.
	 * 
	 * @throws SQLException kiv�tel
	 */
	private void initComponents() throws SQLException {
		
		setSpacing(true);
		
		languageCB = new LanguagesComboBox(false);
		languageCB.setCaption(null);
		addComponent(languageCB);
		
		removeCB = new CheckBox();
		addComponent(removeCB);
		
		getDataById();
	}

	@Override
	public boolean isValid() {
		return languageCB.isValid();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		Connection c = ConnectionManager.getConnection();

		boolean toDelete = removeCB.getValue();
		boolean hasId = (null != id);
		
		// Ha nincs m�g az adat t�rolva az adatb�zisban �s t�r�lni szeretn�nk, akkor kil�p�nk.
		if (!hasId && toDelete) return null;
		
		// T�rolt �s t�rlend� adat eset�n t�rl�nk.
		if (hasId && toDelete) {
			PreparedStatement ps = c.prepareStatement("DELETE FROM NYELVTUDAS WHERE ID=" + id);
			ps.execute();
			c.commit();
			ps.close();
			return null;
		}
		
		// T�rolt de nem t�rlend� adat eset�n csak m�dos�tunk.
		PreparedStatement ps;
		if (hasId && !toDelete) {
			ps = c.prepareStatement("UPDATE NYELVTUDAS SET SZEMELY_ID=?,NYELV=? WHERE ID=" + id);
		} else {
		
		// Egy�bk�nt besz�runk.
		ps = c.prepareStatement(
				"INSERT INTO NYELVTUDAS (SZEMELY_ID,NYELV) VALUES (?,?)",
				new String[] { "ID" });
		}

		// Param�terek megad�sa.
		ps.setInt(1, Integer.parseInt(memberId.toString()));
		ps.setString(2, languageCB.getValue().toString());

		ps.executeUpdate();
		c.commit();
		
		return null;
	}

	@Override
	public void afterInsert(Object newID) {
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		toInsert();
	}

	@Override
	public void afterUpdate(Object id) {
	}

	@Override
	public void getDataById() throws SQLException {
		
		if (null == id) return;
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT NYELV FROM NYELVTUDAS WHERE ID=" + id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			languageCB.setValue(rs.getString("NYELV"));
		}
		rs.close();
		ps.close();
	}

	/**
	 * Nyelvek list�j�nak a lek�rdez�se.
	 * 
	 * @return nyelvek list�ja
	 */
	public LanguagesComboBox getLanguageTF() {
		return languageCB;
	}

	/**
	 * T�rl�s jel�l�j�nek a lek�rdez�se.
	 * 
	 * @return t�rl�s jel�l�je
	 */
	public CheckBox getRemoveCB() {
		return removeCB;
	}

	/**
	 * Szem�ly azonos�t�j�nak a megad�sa.
	 * 
	 * @param personID szem�ly azonos�t�ja
	 */
	public void setPersonID(Object personID) {
		this.memberId = personID;
	}
	
}
