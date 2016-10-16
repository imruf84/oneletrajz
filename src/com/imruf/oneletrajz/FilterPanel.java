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
	 * Személyek listája.
	 */
	private final MembersListPanel mlp;
	/**
	 * Személyek legördülõ listája.
	 */
	private MembersComboBox nameCB;
	/**
	 * Nyelvek listája.
	 */
	private LanguagesComboBox languageCB;
	/**
	 * Intézmények listája.
	 */
	private SchoolsComboBox schoolCB;
	
	/**
	 * Konstruktor.
	 * @param mlp személyek listája
	 * @throws SQLException kivétel
	 */
	public FilterPanel(MembersListPanel mlp) throws SQLException {
		
		this.mlp = mlp;
		
		initComponents();
	}
	
	/**
	 * Komponensek inicializálása.
	 * 
	 * @throws SQLException kivétel
	 */
	private void initComponents() throws SQLException {
		
		setWidth("100%");
		
		HorizontalLayout mhl = new HorizontalLayout();
		mhl.setMargin(true);
		mhl.setSpacing(true);
		mhl.setWidth("100%");
		setContent(mhl);
		
		// Név.
		nameCB = new MembersComboBox();
		nameCB.setNullSelectionAllowed(true);
		mhl.addComponent(nameCB);
		
		// Nyelvtudás.
		languageCB = new LanguagesComboBox(true);
		languageCB.setNullSelectionAllowed(true);
		mhl.addComponent(languageCB);
		
		// Tanulmányok.
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
				
				// Név.
				if (null != nameCB.getValue()) filters.add(" UPPER(NEV) LIKE '%" + nameCB.getValue().toString().toUpperCase() + "%'");
				
				// Nyelvtudás.
				if (null != languageCB.getValue()) filters.add(" UPPER(NYELV) LIKE '%" + languageCB.getValue().toString().toUpperCase() + "%'");
				
				// Tanulmányok.
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
	 * Beviteli mezõk tartalmának frissítése (pl. tárolt adatok módosítása esetén).
	 * 
	 * @throws SQLException kivétel
	 */
	public void refresh() throws SQLException {
		nameCB.refresh();
		languageCB.refresh();
		schoolCB.refresh();
	}
	
	/**
	 * Frissítés utáni esemény.
	 * 
	 * @param filter szûrõfeltétel
	 */
	public void onUpdate(String filter) {
		mlp.setFilter(filter);
		mlp.updateMembersList();
	}

}
