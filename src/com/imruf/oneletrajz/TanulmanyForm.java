package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class TanulmanyForm extends HorizontalLayout implements Validable, SQLInsertable, SQLUpdateable, SQLSelectable {
	
	private final Object id;
	private Object szemelyId;
	private TextField mettolTF;
	private TextField meddigTF;
	private AbstractTextField intezmenyTF;
	private CheckBox torolCB;
	
	public TanulmanyForm(Object id, Object szemelyId) throws SQLException {
		this.id = id;
		this.szemelyId = szemelyId;
		
		initComponents();
	}

	private void initComponents() throws SQLException {
		
		setSpacing(true);
		
		mettolTF = new TextField();
		mettolTF.setMaxLength(4);
		mettolTF.setWidth("4em");
		mettolTF.addValidator(new StringLengthValidator("Évszám megadása kötelezõ!", 1, 4, false));
		mettolTF.addValidator(new RegexpValidator("[0-9]{1,4}", true, "Csak számjegyek használhatóak!"));
		addComponent(mettolTF);
		
		meddigTF = new TextField();
		meddigTF.setMaxLength(4);
		meddigTF.setWidth("4em");
		meddigTF.addValidator(new StringLengthValidator("Évszám megadása kötelezõ!", 0, 4, false));
		meddigTF.addValidator(new RegexpValidator("[0-9]{0,4}", true, "Csak számjegyek használhatóak!"));
		addComponent(meddigTF);
		
		intezmenyTF = new TextField();
		intezmenyTF.setMaxLength(100);
		intezmenyTF.setWidth("22em");
		intezmenyTF.addValidator(new StringLengthValidator("Intézmény nevének a megadása kötelezõ!", 1, 100, false));
		intezmenyTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.,]{1,100}", true, "Intézménynév formátuma nem megfelelõ!"));
		addComponent(intezmenyTF);
		
		torolCB = new CheckBox();
		addComponent(torolCB);
		
		getDataById();
	}

	@Override
	public boolean isValid() {
		return mettolTF.isValid() && meddigTF.isValid() && intezmenyTF.isValid();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		Connection c = ConnectionManager.getConnection();

		boolean toDelete = torolCB.getValue();
		boolean hasId = null != id;
		
		// Ha nincs még az adat tárolva az adatbázisban és törölni szeretnénk, akkor kilépünk.
		if (!hasId && toDelete) return null;
		
		// Tárolt és törlendõ adat esetén törlünk.
		if (hasId && toDelete) {
			PreparedStatement ps = c.prepareStatement("DELETE FROM TANULMANYOK WHERE ID=" + id);
			ps.execute();
			c.commit();
			ps.close();
			return null;
		}
		
		// Tárolt de nem törlendõ adat esetén csak módosítunk.
		PreparedStatement ps;
		if (hasId && !toDelete) {
			ps = c.prepareStatement("UPDATE TANULMANYOK SET SZEMELY_ID=?,METTOL=?,MEDDIG=?,INTEZMENY=? WHERE ID=" + id);
		} else {
		
		// Egyébként beszúrunk.
		ps = c.prepareStatement(
				"INSERT INTO TANULMANYOK (SZEMELY_ID,METTOL,MEDDIG,INTEZMENY) VALUES (?,?,?,?)",
				new String[] { "ID" });
		}

		// Paraméterek megadása.
		ps.setInt(1, Integer.parseInt(szemelyId.toString()));
		ps.setInt(2, Integer.parseInt(mettolTF.getValue()));
		if (meddigTF.isEmpty())
			ps.setNull(3, Types.INTEGER);
		else
			ps.setInt(3, Integer.parseInt(meddigTF.getValue()));
		ps.setString(4, intezmenyTF.getValue());

		ps.executeUpdate();
		c.commit();
		
		return null;
	}
	
	public void setPersonID(Object personID) {
		this.szemelyId = personID;
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
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT METTOL,MEDDIG,INTEZMENY FROM TANULMANYOK WHERE ID=" + id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			mettolTF.setValue(Integer.toString(rs.getInt("METTOL")));
			int meddig = rs.getInt("MEDDIG");
			if (!rs.wasNull()) {
				meddigTF.setValue(Integer.toString(meddig));
			}
			intezmenyTF.setValue(rs.getString("INTEZMENY"));
		}
		rs.close();
		ps.close();
	}

	public TextField getMettolTF() {
		return mettolTF;
	}

	public TextField getMeddigTF() {
		return meddigTF;
	}

	public AbstractTextField getIntezmenyTF() {
		return intezmenyTF;
	}

	public CheckBox getTorolCB() {
		return torolCB;
	}
	

}
