package com.imruf.oneletrajz;

import java.sql.SQLException;

/**
 * Adatok lekérdezését végzõ interfész.
 * 
 * @author imruf84
 */
public interface SQLSelectable {
	/**
	 * Lekérdezés eseménye.
	 * 
	 * @throws SQLException kivétel
	 */
	public void getDataById() throws SQLException;
}
