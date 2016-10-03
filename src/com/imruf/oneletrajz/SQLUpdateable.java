package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface SQLUpdateable {
	public void toUpdate() throws SQLException, FileNotFoundException;
	public void afterUpdate(Object id);
}
