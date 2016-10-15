package com.imruf.oneletrajz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class MainMenuPanel extends VerticalLayout {

	/**
	 * Személyek alapadatait tartalmazó tároló.
	 */
	private final MembersListPanel membersPanel;
	private KeresesPanel keresesPanel;

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

		HorizontalLayout hl = new HorizontalLayout();
		hl.setMargin(true);
		hl.setSpacing(true);
		hl.setWidth("100%");
		
		addComponent(hl);

		Button ujOneletrajz = new Button("Új létrehozása...");
		ujOneletrajz.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				new MemberDataWindow(null) {
					public void afterInsert(Object newID) {
						refresh(newID);
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
		hl.addComponent(ujOneletrajz);

		Button modositOneletrajz = new Button("Kijelölt módosítása...");
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
						refresh(id);
					}

					public Object toInsert() {
						return -1;
					}

					public void toUpdate() {
					}
				};
			}
		});
		hl.addComponent(modositOneletrajz);
		
		Button frissites = new Button("Lista frissítése", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getMembersListPanel().updateMembersList();
				try {
					keresesPanel.refresh();
				} catch (SQLException e) {
					Notification.show("Hiba a személynevek lekérdezése során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		hl.addComponent(frissites);
		
		Button kereses = new Button("Keresés...", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				keresesPanel.setVisible(!keresesPanel.isVisible());
			}
		});
		hl.addComponent(kereses);
		
		Button torolOneletrajz = new Button("Kijelölt törlése...");
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
										
										getMembersListPanel().setSelectedItem(null);

										refresh(null);
									} catch (SQLException e) {
										Notification.show("Hiba az adat törlése során:\n" + e.getLocalizedMessage(),
												Notification.Type.ERROR_MESSAGE);
									}
								}
							}
						});
			}
		});
		hl.addComponent(torolOneletrajz);
		hl.setComponentAlignment(torolOneletrajz, Alignment.MIDDLE_RIGHT);
		hl.setExpandRatio(torolOneletrajz, 1f);
		
		try {
			keresesPanel = new KeresesPanel(getMembersListPanel());
			keresesPanel.setVisible(false);
			addComponent(keresesPanel);
		} catch (SQLException e) {
			Notification.show("Hiba az adatok letöltése során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}

	public KeresesPanel getKeresesPanel() {
		return keresesPanel;
	}
	
	public void refresh(Object selectedID) {
		getMembersListPanel().updateMembersList();
		getMembersListPanel().setSelectedItem(selectedID);
		try {
			keresesPanel.refresh();
		} catch (SQLException e) {
			Notification.show("Hiba a személynevek lekérdezése során:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}

}
