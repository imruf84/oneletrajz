package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class TanulmanyokPanel extends VerticalLayout implements Validable, SQLInsertable, SQLUpdateable, Closable, SQLSelectable {
	
	private Object personID;
	private final LinkedList<TanulmanyForm> tanulmanyForms = new LinkedList<>();
	private VerticalLayout vl[];

	public TanulmanyokPanel(Object personID) throws SQLException {
		this.personID = personID;
		initComponents();
		getDataById();
	}

	private void initComponents() {
		
		setCaption("Tanulmányok");
		setMargin(true);
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		String captions[] = {"Mettõl:", "Meddig:", "Intézmény:", "Töröl:"};
		vl = new VerticalLayout[captions.length];
		for (int i = 0; i < vl.length; i++) {
			vl[i] = new VerticalLayout();
			hl.addComponent(vl[i]);
			vl[i].addComponent(new Label(captions[i]));
		}
		addComponent(hl);
		
		Button newRowButton = new Button("Új sor", new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					addRow(null);
				} catch (SQLException e) {
				}
			}
		});
		newRowButton.addStyleName(BaseTheme.BUTTON_LINK);
		addComponent(newRowButton);
	}

	public void setPersonID(Object personID) {
		this.personID = personID;
		
		for (TanulmanyForm tf : tanulmanyForms) {
			tf.setPersonID(personID);
		}
	}
	
	@Override
	public void onClose() {
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		
		for (TanulmanyForm tf : tanulmanyForms) {
			tf.toUpdate();
		}
	}

	@Override
	public void afterUpdate(Object id) {
		onClose();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		for (TanulmanyForm tf : tanulmanyForms) {
			tf.toInsert();
		}
		
		return null;
	}

	@Override
	public void afterInsert(Object newID) {
		
		for (TanulmanyForm tf : tanulmanyForms) {
			tf.afterInsert(newID);
		}
		
		onClose();
	}

	@Override
	public boolean isValid() {
		
		for (TanulmanyForm tf : tanulmanyForms) {
			if (!tf.isValid()) return false;
		}
		
		return true;
	}

	private void addRow(Object id) throws SQLException {
		
		TanulmanyForm tf = new TanulmanyForm(id, personID);
		vl[0].addComponent(tf.getMettolTF());
		vl[1].addComponent(tf.getMeddigTF());
		vl[2].addComponent(tf.getIntezmenyTF());
		vl[3].addComponent(tf.getTorolCB());
		vl[3].setComponentAlignment(tf.getTorolCB(), Alignment.MIDDLE_CENTER);
		tanulmanyForms.add(tf);
		
		// Ablak középre igazítása a megnövekedett méret miatt.
		Utils.centerWindows();
	}
	
	@Override
	public void getDataById() throws SQLException {
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT ID FROM TANULMANYOK WHERE SZEMELY_ID=" + personID + " ORDER BY METTOL,MEDDIG");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addRow(rs.getInt("ID"));
		}
		rs.close();
		ps.close();
		
	}
}
