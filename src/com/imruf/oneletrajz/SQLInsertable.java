package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Adat beszúrását támogató interfész.
 * 
 * @author imruf84
 */
public interface SQLInsertable {

	/**
	 * Adat beszúrása adatbázisba.
	 * 
	 * @return az újonnan beszúrt adat azonosítója
	 * @throws SQLException
	 *             kivétel
	 * @throws FileNotFoundException
	 *             kivétel
	 */
	public Object toInsert() throws SQLException, FileNotFoundException;

	/**
	 * Adat beszúrása utáni esemény.
	 * 
	 * @param newID
	 *            újonnan beszúrt adat azonosítója.
	 */
	public void afterInsert(Object newID);
}
