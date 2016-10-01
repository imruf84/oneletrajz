package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class MainMenuPanel extends HorizontalLayout {
	
	private final MembersListPanel mlp;
	
	/**
	 * Konstruktor.
	 */
	public MainMenuPanel(final MembersListPanel mlp) {
		super();
		setMargin(true);

		this.mlp = mlp;
		
		initComponents();
	}
	
	private MembersListPanel getMembersListPanel() {
		return this.mlp;		
	}
	
	/**
	 * Komponens inicializálása.
	 */
	private final void initComponents() {
		
		setSpacing(true);
		
		Button ujOneletrajz = new Button("Új önéletrajz");
		ujOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				MemberDataWindow mdw = new MemberDataWindow(null);
				
			}
		});
		addComponent(ujOneletrajz);
		
		Button torolOneletrajz = new Button("Kijelölt önéletrajz törlése");
		torolOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				// Kijelölt sor azonosítójának a lekérdezése.
				Object ID = getMembersListPanel().getSelectedId();
				
				// Ha nincs kijelölt elem, akkor jelezzük.
				if (null == ID) {
					Notification.show("Nincs kijelölt önéletrajz!", Notification.Type.ERROR_MESSAGE);
					return;
				}
				
				// Rákérdezünk a törlésre.
				ConfirmDialog.show(getUI(), "Törlés", "Valóban törli a kijelölt önéletrajzot?", "Igen", "Nem", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							// Törlés.
							try {
								Connection c = ConnectionManager.getConnectionPool().reserveConnection();
								PreparedStatement ps = c.prepareStatement("DELETE FROM SZEMELYEK WHERE ID=" + ID.toString());
								ps.executeQuery();
								c.commit();
								ps.close();

								getMembersListPanel().updateMembersList();
								getMembersListPanel().setSelectedItem(null);
							} catch (SQLException e) {
								Notification.show("Hiba az adat törlése során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
							}
						}
					}
				});
			}
		});
		
		addComponent(torolOneletrajz);
	}
	
}
