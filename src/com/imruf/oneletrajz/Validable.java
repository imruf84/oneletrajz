package com.imruf.oneletrajz;

/**
 * Adatok helyességét támogató interfész.
 * 
 * @author imruf84
 */
public interface Validable {

	/**
	 * Adatok helyességének a lekérdezése.
	 * 
	 * @return igaz esetén az adatok helyesek, egyébként hamis
	 */
	public boolean isValid();
}
