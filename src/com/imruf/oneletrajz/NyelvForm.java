package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class NyelvForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable, SQLSelectable {

	private final Object id;
	private Object szemelyId;
	private NyelvekComboBox nyelvCB;
	private CheckBox torolCB;
	
	public NyelvForm(Object id, Object szemelyId) throws SQLException {
		this.id = id;
		this.szemelyId = szemelyId;
		
		initComponents();
	}

	private void initComponents() throws SQLException {
		
		setSpacing(true);
		
		nyelvCB = new NyelvekComboBox(false);
		nyelvCB.setCaption(null);
		/*nyelvTF.setMaxLength(10);
		nyelvTF.setWidth("10em");
		nyelvTF.addValidator(new StringLengthValidator("Nyelv megad�sa k�telez�!", 1, 10, false));
		nyelvTF.addValidator(new RegexpValidator("[a-zA-Z������������������]{1,10}", true, "Nyelv form�tuma nem megfelel�!"));*/
		addComponent(nyelvCB);
		
		torolCB = new CheckBox();
		addComponent(torolCB);
		
		getDataById();
	}

	@Override
	public boolean isValid() {
		return nyelvCB.isValid();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		Connection c = ConnectionManager.getConnection();

		boolean toDelete = torolCB.getValue();
		boolean hasId = null != id;
		
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
		ps.setInt(1, Integer.parseInt(szemelyId.toString()));
		ps.setString(2, nyelvCB.getValue().toString());

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
			nyelvCB.setValue(rs.getString("NYELV"));
		}
		rs.close();
		ps.close();
	}

	public NyelvekComboBox getNyelvTF() {
		return nyelvCB;
	}

	public CheckBox getTorolCB() {
		return torolCB;
	}

	public void setPersonID(Object personID) {
		this.szemelyId = personID;
	}
	
}
