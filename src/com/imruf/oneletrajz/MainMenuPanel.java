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
	 * Személyek alapadatait tartalmazó tároló.
	 */
	private final MembersListPanel membersPanel;

	/**
	 * Konstruktor.
	 * 
	 * @param mlp
	 *            személyek alapadatait tároló objektum
	 */
	public MainMenuPanel(final MembersListPanel mlp) {
		super();

		this.membersPanel = mlp;

		initComponents();
	}

	/**
	 * Személyek alapadatainak a tárolójának a lekérdezése.
	 * 
	 * @return személyek alapadatainak a tárolója
	 */
	private MembersListPanel getMembersListPanel() {
		return this.membersPanel;
	}

	/**
	 * Komponens inicializálása.
	 */
	private final void initComponents() {

		setMargin(true);
		setSpacing(true);

		Button ujOneletrajz = new Button("Új önéletrajz");
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

		Button modositOneletrajz = new Button("Módosít");
		modositOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Kijelölt sor azonosítójának a lekérdezése.
				Object ID = getMembersListPanel().getSelectedId();

				// Ha nincs kijelölt elem, akkor jelezzük.
				if (null == ID) {
					Notification.show("Nincs kijelölt önéletrajz!", Notification.Type.ERROR_MESSAGE);
					return;
				}

				// Adatok megjelenítése.
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

		Button torolOneletrajz = new Button("Kijelölt önéletrajz törlése");
		torolOneletrajz.addStyleName("danger");
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
				ConfirmDialog.show(getUI(), "Törlés", "Valóban törli a kijelölt önéletrajzot?", "Igen", "Nem",
						new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									// Törlés.
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
										Notification.show("Hiba az adat törlése során:\n" + e.getLocalizedMessage(),
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
