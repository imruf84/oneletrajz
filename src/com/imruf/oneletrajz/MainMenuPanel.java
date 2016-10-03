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

	/**
	 * Szem�lyek alapadatait tartalmaz� t�rol�.
	 */
	private final MembersListPanel membersPanel;

	/**
	 * Konstruktor.
	 * 
	 * @param mlp
	 *            szem�lyek alapadatait t�rol� objektum
	 */
	public MainMenuPanel(final MembersListPanel mlp) {
		super();

		this.membersPanel = mlp;

		initComponents();
	}

	/**
	 * Szem�lyek alapadatainak a t�rol�j�nak a lek�rdez�se.
	 * 
	 * @return szem�lyek alapadatainak a t�rol�ja
	 */
	private MembersListPanel getMembersListPanel() {
		return this.membersPanel;
	}

	/**
	 * Komponens inicializ�l�sa.
	 */
	private final void initComponents() {

		setMargin(true);
		setSpacing(true);

		Button ujOneletrajz = new Button("�j �n�letrajz");
		ujOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				new MemberDataWindow(null) {
					public void afterInsert(Object newID) {
						getMembersListPanel().updateMembersList();
						getMembersListPanel().setSelectedItem(newID);
					}

					public void afterUpdate(Object id) {
					}

					public Object toInsert() {
						return -1;
					}

					public void toUpdate() {
					}
				};

			}
		});
		addComponent(ujOneletrajz);

		Button modositOneletrajz = new Button("M�dos�t");
		modositOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Kijel�lt sor azonos�t�j�nak a lek�rdez�se.
				Object ID = getMembersListPanel().getSelectedId();

				// Ha nincs kijel�lt elem, akkor jelezz�k.
				if (null == ID) {
					Notification.show("Nincs kijel�lt �n�letrajz!", Notification.Type.ERROR_MESSAGE);
					return;
				}

				// Adatok megjelen�t�se.
				new MemberDataWindow(ID) {
					public void afterInsert(Object newID) {
					}

					public void afterUpdate(Object id) {
						getMembersListPanel().updateMembersList();
						getMembersListPanel().setSelectedItem(id);
					}

					public Object toInsert() {
						return -1;
					}

					public void toUpdate() {
					}
				};
			}
		});
		addComponent(modositOneletrajz);

		Button torolOneletrajz = new Button("Kijel�lt �n�letrajz t�rl�se");
		torolOneletrajz.addStyleName("danger");
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
				ConfirmDialog.show(getUI(), "T�rl�s", "Val�ban t�rli a kijel�lt �n�letrajzot?", "Igen", "Nem",
						new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									// T�rl�s.
									try {
										Connection c = ConnectionManager.getConnection();
										PreparedStatement ps = c
												.prepareStatement("DELETE FROM SZEMELYEK WHERE ID=" + ID.toString());
										ps.executeQuery();
										c.commit();
										ps.close();

										getMembersListPanel().updateMembersList();
										getMembersListPanel().setSelectedItem(null);
									} catch (SQLException e) {
										Notification.show("Hiba az adat t�rl�se sor�n:\n" + e.getLocalizedMessage(),
												Notification.Type.ERROR_MESSAGE);
									}
								}
							}
						});
			}
		});

		addComponent(torolOneletrajz);
		setExpandRatio(torolOneletrajz, 1.0f);
	}

}
