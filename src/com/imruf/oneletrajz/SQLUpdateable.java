package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Adat m�dos�t�s�t t�mogat� interf�sz.
 * 
 * @author imruf84
 */
public interface SQLUpdateable {

	/**
	 * Adat m�dos�t�sa adatb�zisban.
	 * 
	 * @throws SQLException
	 *             kiv�tel
	 * @throws FileNotFoundException
	 *             kiv�tel
	 */
	public void toUpdate() throws SQLException, FileNotFoundException;

	/**
	 * Adat m�dos�t�sa ut�ni esem�ny.
	 * 
	 * @param id
	 *            m�dos�tott adat azonos�t�ja.
	 */
	public void afterUpdate(Object id);
}
