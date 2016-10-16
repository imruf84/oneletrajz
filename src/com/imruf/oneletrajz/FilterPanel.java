package com.imruf.oneletrajz;

import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public class FilterPanel extends Panel {
	
	/**
	 * Szem�lyek list�ja.
	 */
	private final MembersListPanel mlp;
	/**
	 * Szem�lyek leg�rd�l� list�ja.
	 */
	private MembersComboBox nameCB;
	/**
	 * Nyelvek list�ja.
	 */
	private LanguagesComboBox languageCB;
	/**
	 * Int�zm�nyek list�ja.
	 */
	private SchoolsComboBox schoolCB;
	
	/**
	 * Konstruktor.
	 * @param mlp szem�lyek list�ja
	 * @throws SQLException kiv�tel
	 */
	public FilterPanel(MembersListPanel mlp) throws SQLException {
		
		this.mlp = mlp;
		
		initComponents();
	}
	
	/**
	 * Komponensek inicializ�l�sa.
	 * 
	 * @throws SQLException kiv�tel
	 */
	private void initComponents() throws SQLException {
		
		setWidth("100%");
		
		HorizontalLayout mhl = new HorizontalLayout();
		mhl.setMargin(true);
		mhl.setSpacing(true);
		mhl.setWidth("100%");
		setContent(mhl);
		
		// N�v.
		nameCB = new MembersComboBox();
		nameCB.setNullSelectionAllowed(true);
		mhl.addComponent(nameCB);
		
		// Nyelvtud�s.
		languageCB = new LanguagesComboBox(true);
		languageCB.setNullSelectionAllowed(true);
		mhl.addComponent(languageCB);
		
		// Tanulm�nyok.
		schoolCB = new SchoolsComboBox();
		schoolCB.setNullSelectionAllowed(true);
		mhl.addComponent(schoolCB);
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		mhl.addComponent(hl);
		mhl.setComponentAlignment(hl, Alignment.MIDDLE_RIGHT);
		mhl.setExpandRatio(hl, 1f);
		
		CssLayout bl = new CssLayout();
		bl.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hl.addComponent(bl);
		
		Button findBtn = new Button("Keres", new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				
				LinkedList<String> filters = new LinkedList<>();
				
				// N�v.
				if (null != nameCB.getValue()) filters.add(" UPPER(NEV) LIKE '%" + nameCB.getValue().toString().toUpperCase() + "%'");
				
				// Nyelvtud�s.
				if (null != languageCB.getValue()) filters.add(" UPPER(NYELV) LIKE '%" + languageCB.getValue().toString().toUpperCase() + "%'");
				
				// Tanulm�nyok.
				if (null != schoolCB.getValue()) filters.add(" UPPER(INTEZMENY) LIKE '%" + schoolCB.getValue().toString().toUpperCase() + "%'");
				
				onUpdate(String.join(" AND ", filters));
			}
		});
		bl.addComponent(findBtn);
		
		Button showAllBtn = new Button("Mind mutat", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				nameCB.setValue(null);
				languageCB.setValue(null);
				schoolCB.setValue(null);
				
				onUpdate("");
			}
		});
		bl.addComponent(showAllBtn);

	}
	
	/**
	 * Beviteli mez�k tartalm�nak friss�t�se (pl. t�rolt adatok m�dos�t�sa eset�n).
	 * 
	 * @throws SQLException kiv�tel
	 */
	public void refresh() throws SQLException {
		nameCB.refresh();
		languageCB.refresh();
		schoolCB.refresh();
	}
	
	/**
	 * Friss�t�s ut�ni esem�ny.
	 * 
	 * @param filter sz�r�felt�tel
	 */
	public void onUpdate(String filter) {
		mlp.setFilter(filter);
		mlp.updateMembersList();
	}

}
