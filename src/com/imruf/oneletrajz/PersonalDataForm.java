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
		vezetekNevTF.setCaption("Vezetéknév:");
		vezetekNevTF.setMaxLength(20);
		vezetekNevTF.setRequired(true);
		vezetekNevTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Vezetéknév formátuma nem megfelelõ!"));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
		
		TextField keresztNevTF = new TextField();
		keresztNevTF.setCaption("Keresztnév:");
		keresztNevTF.setMaxLength(20);
		keresztNevTF.setRequired(true);
		keresztNevTF.setRequiredError("Keresztnév megadása kötelezõ!");
		keresztNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ \\-.]{1,20}", true, "Keresztnév formátuma nem megfelelõ!"));
		keresztNevTF.setWidth("20em");
		addComponent(keresztNevTF);
		
		VarosokComboBox szuletesiHely = new VarosokComboBox();
		szuletesiHely.setCaption("Születési hely:");
		szuletesiHely.setNullSelectionAllowed(false);
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési hely megadása kötelezõ!");
		szuletesiHely.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ]{1,30}", true, "Születési hely formátuma nem megfelelõ!"));
		addComponent(szuletesiHely);
		
		DateField szuletesiIdo = new DateField();
		szuletesiIdo.setCaption("Születési idõ:");
		szuletesiHely.setRequired(true);
		szuletesiHely.setRequiredError("Születési idõ megadása kötelezõ!");
		szuletesiIdo.addValidator(new NullValidator("Születési idõ megadása kötelezõ!", false));
		addComponent(szuletesiIdo);
	}
}
