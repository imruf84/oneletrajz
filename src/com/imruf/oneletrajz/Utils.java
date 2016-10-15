package com.imruf.oneletrajz;

import java.util.Iterator;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class Utils {
	public static void centerWindows() {
		Iterator<Window> it = UI.getCurrent().getWindows().iterator();
		if (it.hasNext()) it.next().center();
	}
}
