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
	 * Beszélt nyelv azonosítója.
	 */
	private final Object id;
	/**
	 * Személy azonosítója.
	 */
	private Object memberId;
	/**
	 * Nyelv.
	 */
	private LanguagesComboBox languageCB;
	/**
	 * Törlés jelölõje.
	 */
	private CheckBox removeCB;
	
	/**
	 * Konstruktor.
	 * 
	 * @param id beszélt nyelv azonosítója
	 * @param memberId személy azonosítója
	 * @throws SQLException kivétel
	 */
	public LanguageForm(Object id, Object memberId) throws SQLException {
		this.id = id;
		this.memberId = memberId;
		
		initComponents();
	}

	/**
	 * Komponensek inicializálása.
	 * 
	 * @throws SQLException kivétel
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
		
		// Ha nincs még az adat tárolva az adatbázisban és törölni szeretnénk, akkor kilépünk.
		if (!hasId && toDelete) return null;
		
		// Tárolt és törlendõ adat esetén törlünk.
		if (hasId && toDelete) {
			PreparedStatement ps = c.prepareStatement("DELETE FROM NYELVTUDAS WHERE ID=" + id);
			ps.execute();
			c.commit();
			ps.close();
			return null;
		}
		
		// Tárolt de nem törlendõ adat esetén csak módosítunk.
		PreparedStatement ps;
		if (hasId && !toDelete) {
			ps = c.prepareStatement("UPDATE NYELVTUDAS SET SZEMELY_ID=?,NYELV=? WHERE ID=" + id);
		} else {
		
		// Egyébként beszúrunk.
		ps = c.prepareStatement(
				"INSERT INTO NYELVTUDAS (SZEMELY_ID,NYELV) VALUES (?,?)",
				new String[] { "ID" });
		}

		// Paraméterek megadása.
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
	 * Nyelvek listájának a lekérdezése.
	 * 
	 * @return nyelvek listája
	 */
	public LanguagesComboBox getLanguageTF() {
		return languageCB;
	}

	/**
	 * Törlés jelölõjének a lekérdezése.
	 * 
	 * @return törlés jelölõje
	 */
	public CheckBox getRemoveCB() {
		return removeCB;
	}

	/**
	 * Személy azonosítójának a megadása.
	 * 
	 * @param personID személy azonosítója
	 */
	public void setPersonID(Object personID) {
		this.memberId = personID;
	}
	
}
