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
		vezetekNevTF.setCaption("Vezet�kn�v:");
		vezetekNevTF.setMaxLength(20);
		//vezetekNevTF.setRequiredError("Vezet�kn�v megad�sa k�telez�!");
		//vezetekNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ ]{1,20}", true, "Vezet�kn�v megad�sa k�telez�, �s kiz�r�lag bet�ket valamint sz�k�zt tartalmazhat legfeljebb 20 karakterig!"));
		vezetekNevTF.addValidator(new RegexpValidator("[a-zA-Z������������������ ]{1,20}", true, "Vezet�kn�v megad�sa k�telez�."));
		vezetekNevTF.setWidth("20em");
		addComponent(vezetekNevTF);
	}
}
