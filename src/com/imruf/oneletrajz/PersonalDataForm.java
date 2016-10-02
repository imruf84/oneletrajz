package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.imruf.oneletrajz.ConnectionManager;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PersonalDataForm extends FormLayout implements Validable, SQLInsertable, SQLUpdateable {
	
	private TextField vezetekNevTF;
	private TextField keresztNevTF;
	private VarosokComboBox szuletesiHely;
	private DateField szuletesiIdo;
	private Object id;

	public PersonalDataForm(Object id) throws SQLException {
		this.id = id;
		initComponents();
	}
	
	private void initComponents() throws SQLException {
		
		vezetekNevTF = new TextField();
		vezetekNevTF.setCaption("Vezetéknév:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Vezetéknév formátuma nem megfelelõ!"));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
		
		keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztnév:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztnév megadása kötelezõ!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Keresztnév formátuma nem megfelelõ!"));
		keresztNevTF.setWidth("20em");
		addComponent(keresztNevTF);
		
		szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Születési hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési hely megadása kötelezõ!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ]{1,30}", true, "Születési hely formátuma nem megfelelõ!"));
		addComponent(szuletesiHely);
		
		szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Születési idõ:");
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési idõ megadása kötelezõ!");
		szuletesiIdo.addValidator(new NullValidator("Születési idõ megadása kötelezõ!", false));
		addComponent(szuletesiIdo);
		
		// Azonosító esetén kitöltjük adatokkal az ûrlapot.
		fillFieldsById();
	}
	
	private void fillFieldsById() throws SQLException {
		
		if (null == id) return;
		
		// Adatok lekérdezése.
		FreeformQuery query = new FreeformQuery("SELECT * FROM SZEMELYEK", ConnectionManager.getConnectionPool(), "ID");
		SQLContainer container = new SQLContainer(query);
		Item data = container.getItem(ConnectionManager.objectToRowId(id));

		// Adatok megjelenítése.
		vezetekNevTF.setValue((String) data.getItemProperty("VEZETEK_NEV").getValue());
		keresztNevTF.setValue((String) data.getItemProperty("KERESZT_NEV").getValue());
		szuletesiHely.setValue((String) data.getItemProperty("SZULETESI_HELY").getValue());
		try {
			szuletesiIdo.setValue(new SimpleDateFormat("yyyy.MM.dd").parse(((String)data.getItemProperty("SZULETESI_IDO").getValue())));
		} catch (ReadOnlyException | ConversionException | ParseException e) {
		}
	}
	
	@Override
	public boolean isValid() {
		return vezetekNevTF.isValid() && keresztNevTF.isValid() && szuletesiHely.isValid() && szuletesiIdo.isValid();
	}

	@Override
	public void toUpdate() throws SQLException {
		Connection c = ConnectionManager.getConnectionPool().reserveConnection();
		PreparedStatement ps = c.prepareStatement(
				"UPDATE SZEMELYEK SET "
						+ "VEZETEK_NEV='" + vezetekNevTF.getValue() + "',"
						+ "KERESZT_NEV='" + keresztNevTF.getValue() + "',"
						+ "SZULETESI_HELY='" + szuletesiHely.getValue() + "',"
						+ "SZULETESI_IDO='" + new SimpleDateFormat("yyyy.MM.dd").format(szuletesiIdo.getValue()) + "'"
						+ " WHERE ID=" + id);
		ps.executeUpdate();
		c.commit();
		
		ps.close();
	}

	@Override
	public void afterUpdate(Object id) {
	}

	@Override
	public Object toInsert() throws SQLException {
		Connection c = ConnectionManager.getConnectionPool().reserveConnection();
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO SZEMELYEK (VEZETEK_NEV,KERESZT_NEV,SZULETESI_HELY,SZULETESI_IDO) VALUES ("
						+ "'" + vezetekNevTF.getValue() + "',"
						+ "'" + keresztNevTF.getValue() + "',"
						+ "'" + szuletesiHely.getValue() + "',"
						+ "'" + new SimpleDateFormat("yyyy.MM.dd").format(szuletesiIdo.getValue()) + "'"
						+ ")", 
						new String[] { "ID" });
		ps.executeUpdate();
		
		c.commit();
		
		return ps.getGeneratedKeys().next() ? ConnectionManager.objectToRowId(ps.getGeneratedKeys().getInt(1)) : -1;
	}

	@Override
	public void afterInsert(Object newID) {
	}
}
