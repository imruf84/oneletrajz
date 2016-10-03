package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface SQLInsertable {
	public Object toInsert() throws SQLException, FileNotFoundException;
	public void afterInsert(Object newID);
}
