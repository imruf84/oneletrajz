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
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public class ToolbarPanel extends VerticalLayout {

	/**
	 * Szem�lyek alapadatait tartalmaz� t�rol�.
	 */
	private final MembersListPanel membersPanel;
	private FilterPanel keresesPanel;

	/**
	 * Konstruktor.
	 * 
	 * @param mlp
	 *            szem�lyek alapadatait t�rol� objektum
	 */
	public ToolbarPanel(final MembersListPanel mlp) {
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

		HorizontalLayout hl = new HorizontalLayout();
		hl.setMargin(true);
		hl.setSpacing(true);
		hl.setWidth("100%");
		
		addComponent(hl);
		
		CssLayout bl = new CssLayout();
		bl.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hl.addComponent(bl);

		Button ujOneletrajz = new Button("�j l�trehoz�sa...");
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
		bl.addComponent(ujOneletrajz);

		Button modositOneletrajz = new Button("Kijel�lt m�dos�t�sa...");
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
		bl.addComponent(modositOneletrajz);
		
		Button frissites = new Button("Lista friss�t�se", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getMembersListPanel().updateMembersList();
				try {
					keresesPanel.refresh();
				} catch (SQLException e) {
					Notification.show("Hiba a szem�lynevek lek�rdez�se sor�n:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		bl.addComponent(frissites);
		
		Button kereses = new Button("Keres�s...", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				keresesPanel.setVisible(!keresesPanel.isVisible());
			}
		});
		bl.addComponent(kereses);
		
		Button torolOneletrajz = new Button("Kijel�lt t�rl�se...");
		torolOneletrajz.addStyleName(ValoTheme.BUTTON_DANGER);
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
										
										getMembersListPanel().setSelectedItem(null);

										refresh(null);
									} catch (SQLException e) {
										Notification.show("Hiba az adat t�rl�se sor�n:\n" + e.getLocalizedMessage(),
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
			keresesPanel = new FilterPanel(getMembersListPanel());
			keresesPanel.setVisible(false);
			addComponent(keresesPanel);
		} catch (SQLException e) {
			Notification.show("Hiba az adatok let�lt�se sor�n:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}

	public FilterPanel getKeresesPanel() {
		return keresesPanel;
	}
	
	public void refresh(Object selectedID) {
		getMembersListPanel().updateMembersList();
		getMembersListPanel().setSelectedItem(selectedID);
		try {
			keresesPanel.refresh();
		} catch (SQLException e) {
			Notification.show("Hiba a szem�lynevek lek�rdez�se sor�n:\n" + e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
		}
	}

}
