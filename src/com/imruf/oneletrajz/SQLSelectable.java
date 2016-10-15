package com.imruf.oneletrajz;

import java.sql.SQLException;

/**
 * Adatok lek�rdez�s�t v�gz� interf�sz.
 * 
 * @author imruf84
 */
public interface SQLSelectable {
	/**
	 * Lek�rdez�s esem�nye.
	 * 
	 * @throws SQLException kiv�tel
	 */
	public void getDataById() throws SQLException;
}
