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
		vezetekNevTF.setCaption("Vezet�kn�v:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezet�kn�v megad�sa k�telez�!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ \\-.]{1,20}", true, "Vezet�kn�v form�tuma nem megfelel�!"));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
		
		keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztn�v:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztn�v megad�sa k�telez�!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ \\-.]{1,20}", true, "Keresztn�v form�tuma nem megfelel�!"));
		keresztNevTF.setWidth("20em");
		addComponent(keresztNevTF);
		
		szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Sz�let�si hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Sz�let�si hely megad�sa k�telez�!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-Z������������������]{1,30}", true, "Sz�let�si hely form�tuma nem megfelel�!"));
		addComponent(szuletesiHely);
		
		szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Sz�let�si id�:");
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Sz�let�si id� megad�sa k�telez�!");
		szuletesiIdo.addValidator(new NullValidator("Sz�let�si id� megad�sa k�telez�!", false));
		addComponent(szuletesiIdo);
		
		// Azonos�t� eset�n kit�ltj�k adatokkal az �rlapot.
		fillFieldsById();
	}
	
	private void fillFieldsById() throws SQLException {
		
		if (null == id) return;
		
		// Adatok lek�rdez�se.
		FreeformQuery query = new FreeformQuery("SELECT * FROM SZEMELYEK", ConnectionManager.getConnectionPool(), "ID");
		SQLContainer container = new SQLContainer(query);
		Item data = container.getItem(ConnectionManager.objectToRowId(id));

		// Adatok megjelen�t�se.
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
