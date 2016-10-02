package com.imruf.oneletrajz;

import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class MyComboBox extends ComboBox {
	@Override
	public void setValue(Object value) {
		
		// Ha nincs az adott érték a listában, akkor felvesszük.
		if ((null != value) && (!this.containsId(value))) addItem(value);
		
		super.setValue(value);
	}
}
