package com.imruf.oneletrajz;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class NyelvtudasPanel extends VerticalLayout implements Validable, SQLInsertable, SQLUpdateable, Closable, SQLSelectable {

	private Object personID;
	private final LinkedList<NyelvForm> nyelvForms = new LinkedList<>();
	private VerticalLayout vl[];

	public NyelvtudasPanel(Object personID) throws SQLException {
		this.personID = personID;
		initComponents();
		getDataById();
	}

	private void initComponents() {
		
		setCaption("Nyelvtudás");
		setMargin(true);
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		String captions[] = {"Nyelv:", "Töröl:"};
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
		
		for (NyelvForm tf : nyelvForms) {
			tf.setPersonID(personID);
		}
	}
	
	@Override
	public void onClose() {
	}

	@Override
	public void toUpdate() throws SQLException, FileNotFoundException {
		
		for (NyelvForm tf : nyelvForms) {
			tf.toUpdate();
		}
	}

	@Override
	public void afterUpdate(Object id) {
		onClose();
	}

	@Override
	public Object toInsert() throws SQLException, FileNotFoundException {
		
		for (NyelvForm tf : nyelvForms) {
			tf.toInsert();
		}
		
		return null;
	}

	@Override
	public void afterInsert(Object newID) {
		
		for (NyelvForm tf : nyelvForms) {
			tf.afterInsert(newID);
		}
		
		onClose();
	}

	@Override
	public boolean isValid() {
		
		for (NyelvForm tf : nyelvForms) {
			if (!tf.isValid()) return false;
		}
		
		return true;
	}

	private void addRow(Object id) throws SQLException {
		
		NyelvForm tf = new NyelvForm(id, personID);
		vl[0].addComponent(tf.getNyelvTF());
		vl[1].addComponent(tf.getTorolCB());
		vl[1].setComponentAlignment(tf.getTorolCB(), Alignment.MIDDLE_CENTER);
		nyelvForms.add(tf);
		
		// Ablak középre igazítása a megnövekedett méret miatt.
		Utils.centerWindows();
	}
	
	@Override
	public void getDataById() throws SQLException {
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT ID FROM NYELVTUDAS WHERE SZEMELY_ID=" + personID + " ORDER BY NYELV");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			addRow(rs.getInt("ID"));
		}
		rs.close();
		ps.close();
		
	}
	
}
