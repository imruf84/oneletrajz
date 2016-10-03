package com.imruf.oneletrajz;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("tests-valo-reindeer")
public class OneletrajzUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = OneletrajzUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		try {
			ConnectionManager.createConnection();
		} catch (SQLException e) {
			Notification.show("Hiba az adatbázishoz való kapcsolódás során:\n" + e.getLocalizedMessage(),
					Notification.Type.ERROR_MESSAGE);
		}

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		layout.setSizeFull();
		setContent(layout);

		MembersListPanel mlp = new MembersListPanel() {
			public void onDoubleClick(Object id) {

				// Adatok megjelenítése.
				new MemberDataWindow(id) {
					public void afterInsert(Object newID) {
					}

					public void afterUpdate(Object id) {
						updateMembersList();
						setSelectedItem(id);
					}

					public Object toInsert() {
						return -1;
					}

					public void toUpdate() {
					}
				};
			}
		};
		layout.addComponent(new MainMenuPanel(mlp));
		layout.addComponent(mlp);
		layout.setExpandRatio(mlp, 1.0f);
	}

}