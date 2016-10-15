package com.imruf.oneletrajz;

/**
 * Adatok helyess�g�t t�mogat� interf�sz.
 * 
 * @author imruf84
 */
public interface Validable {

	/**
	 * Adatok helyess�g�nek a lek�rdez�se.
	 * 
	 * @return igaz eset�n az adatok helyesek, egy�bk�nt hamis
	 */
	public boolean isValid();
}
