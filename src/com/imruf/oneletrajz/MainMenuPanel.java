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
	 * Komponens inicializ�l�sa.
	 */
	private final void initComponents() {
		
		setSpacing(true);
		
		Button ujOneletrajz = new Button("�j �n�letrajz");
		ujOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				MemberDataWindow mdw = new MemberDataWindow(null);
				
			}
		});
		addComponent(ujOneletrajz);
		
		Button torolOneletrajz = new Button("Kijel�lt �n�letrajz t�rl�se");
		torolOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				// Kijel�lt sor azonos�t�j�nak a lek�rdez�se.
				Object ID = getMembersListPanel().getSelectedId();
				
				// Ha nincs kijel�lt elem, akkor jelezz�k.
				if (null == ID) {
					Notification.show("Nincs kijel�lt �n�letrajz!", Notification.Type.ERROR_MESSAGE);
					return;
				}
				
				// R�k�rdez�nk a t�rl�sre.
				ConfirmDialog.show(getUI(), "T�rl�s", "Val�ban t�rli a kijel�lt �n�letrajzot?", "Igen", "Nem", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							// T�rl�s.
							try {
								Connection c = ConnectionManager.getConnectionPool().reserveConnection();
								PreparedStatement ps = c.prepareStatement("DELETE FROM SZEMELYEK WHERE ID=" + ID.toString());
								ps.executeQuery();
								c.commit();
								ps.close();

								getMembersListPanel().updateMembersList();
								getMembersListPanel().setSelectedItem(null);
							} catch (SQLException e) {
								Notification.show("Hiba az adat t�rl�se sor�n:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
							}
						}
					}
				});
			}
		});
		
		addComponent(torolOneletrajz);
	}
	
}
