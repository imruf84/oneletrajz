package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Adat besz�r�s�t t�mogat� interf�sz.
 * 
 * @author imruf84
 */
public interface SQLInsertable {

	/**
	 * Adat besz�r�sa adatb�zisba.
	 * 
	 * @return az �jonnan besz�rt adat azonos�t�ja
	 * @throws SQLException
	 *             kiv�tel
	 * @throws FileNotFoundException
	 *             kiv�tel
	 */
	public Object toInsert() throws SQLException, FileNotFoundException;

	/**
	 * Adat besz�r�sa ut�ni esem�ny.
	 * 
	 * @param newID
	 *            �jonnan besz�rt adat azonos�t�ja.
	 */
	public void afterInsert(Object newID);
}
