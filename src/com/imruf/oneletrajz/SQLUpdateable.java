package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Adat módosítását támogató interfész.
 * 
 * @author imruf84
 */
public interface SQLUpdateable {

	/**
	 * Adat módosítása adatbázisban.
	 * 
	 * @throws SQLException
	 *             kivétel
	 * @throws FileNotFoundException
	 *             kivétel
	 */
	public void toUpdate() throws SQLException, FileNotFoundException;

	/**
	 * Adat módosítása utáni esemény.
	 * 
	 * @param id
	 *            módosított adat azonosítója.
	 */
	public void afterUpdate(Object id);
}
