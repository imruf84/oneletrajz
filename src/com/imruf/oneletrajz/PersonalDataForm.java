package com.imruf.oneletrajz;

import java.sql.SQLException;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PersonalDataForm extends FormLayout {
	
	public PersonalDataForm(Object id) throws SQLException {
		initComponents();
	}
	
	private void initComponents() throws SQLException {
		
		TextField vezetekNevTF = new TextField();
		vezetekNevTF.setCaption("Vezet�kn�v:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezet�kn�v megad�sa k�telez�!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ \\-.]{1,20}", true, "Vezet�kn�v form�tuma nem megfelel�!"));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
		
		TextField keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztn�v:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztn�v megad�sa k�telez�!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ \\-.]{1,20}", true, "Keresztn�v form�tuma nem megfelel�!"));
		keresztNevTF.setWidth("20em");
		addComponent(keresztNevTF);
		
		VarosokComboBox szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Sz�let�si hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Sz�let�si hely megad�sa k�telez�!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-Z������������������]{1,30}", true, "Sz�let�si hely form�tuma nem megfelel�!"));
		addComponent(szuletesiHely);
		
		DateField szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Sz�let�si id�:");
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Sz�let�si id� megad�sa k�telez�!");
		szuletesiIdo.addValidator(new NullValidator("Sz�let�si id� megad�sa k�telez�!", false));
		addComponent(szuletesiIdo);
	}
}
