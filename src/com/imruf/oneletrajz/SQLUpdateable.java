package com.imruf.oneletrajz;

import java.sql.SQLException;

public interface SQLUpdateable {
	public void toUpdate() throws SQLException;
	public void afterUpdate(Object id);
}
