package com.imruf.oneletrajz;

import com.vaadin.ui.ComboBox;

/**
 * Saj�t ComboBox megval�s�t�s, ami lehet�v� teszi �j adatok felv�tel�t a
 * list�ba.
 * 
 * @author imruf84
 */
@SuppressWarnings("serial")
public class MyComboBox extends ComboBox {
	@Override
	public void setValue(Object value) {

		// Ha nincs az adott �rt�k a list�ban, akkor felvessz�k.
		if ((null != value) && (!this.containsId(value)))
			addItem(value);

		super.setValue(value);
	}
}
