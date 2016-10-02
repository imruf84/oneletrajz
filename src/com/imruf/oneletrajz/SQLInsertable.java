package com.imruf.oneletrajz;

import java.sql.SQLException;

public interface SQLInsertable {
	public Object toInsert() throws SQLException;
	public void afterInsert(Object newID);
}
