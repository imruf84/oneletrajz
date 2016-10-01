package com.imruf.oneletrajz;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PersonalDataForm extends FormLayout {
	
	public PersonalDataForm(Object id) {
		initComponents();
	}
	
	private void initComponents() {
		
		TextField vezetekNevTF = new TextField();
		vezetekNevTF.setCaption("Vezetéknév:");
		vezetekNevTF.setMaxLength(20);
		//vezetekNevTF.setRequiredError("Vezetéknév megadása kötelezõ!");
		//vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ ]{1,20}", true, "Vezetéknév megadása kötelezõ, és kizárólag betûket valamint szóközt tartalmazhat legfeljebb 20 karakterig!"));
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-ZöüóõúéáûíÖÜÓÕÚÉÁÛÍ ]{1,20}", true, "Vezetéknév megadása kötelezõ."));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
	}
}
