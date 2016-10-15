package com.imruf.oneletrajz;

import java.sql.SQLException;
import java.util.LinkedList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class KeresesPanel extends Panel {
	
	private final MembersListPanel mlp;
	private SzemelyekComboBox nevCB;
	private NyelvekComboBox nyelvCB;
	private IntezmenyekComboBox intezmenyCB;
	
	public KeresesPanel(MembersListPanel mlp) throws SQLException {
		
		this.mlp = mlp;
		
		initComponents();
	}
	
	private void initComponents() throws SQLException {
		
		setWidth("100%");
		
		HorizontalLayout mhl = new HorizontalLayout();
		mhl.setMargin(true);
		mhl.setSpacing(true);
		mhl.setWidth("100%");
		setContent(mhl);
		
		// Név.
		nevCB = new SzemelyekComboBox();
		nevCB.setNullSelectionAllowed(true);
		mhl.addComponent(nevCB);
		
		// Nyelvtudás.
		nyelvCB = new NyelvekComboBox(true);
		nyelvCB.setNullSelectionAllowed(true);
		mhl.addComponent(nyelvCB);
		
		// Tanulmányok.
		intezmenyCB = new IntezmenyekComboBox();
		intezmenyCB.setNullSelectionAllowed(true);
		mhl.addComponent(intezmenyCB);
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		mhl.addComponent(hl);
		mhl.setComponentAlignment(hl, Alignment.MIDDLE_RIGHT);
		mhl.setExpandRatio(hl, 1f);
		
		Button keresBtn = new Button("Keres", new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				
				LinkedList<String> filters = new LinkedList<>();
				
				// Név.
				if (null != nevCB.getValue()) filters.add(" UPPER(NEV) LIKE '%" + nevCB.getValue().toString().toUpperCase() + "%'");
				
				// Nyelvtudás.
				if (null != nyelvCB.getValue()) filters.add(" UPPER(NYELV) LIKE '%" + nyelvCB.getValue().toString().toUpperCase() + "%'");
				
				// Tanulmányok.
				if (null != intezmenyCB.getValue()) filters.add(" UPPER(INTEZMENY) LIKE '%" + intezmenyCB.getValue().toString().toUpperCase() + "%'");
				
				onUpdate(String.join(" AND ", filters));
			}
		});
		hl.addComponent(keresBtn);
		
		Button allBtn = new Button("Mind mutat", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				nevCB.setValue(null);
				nyelvCB.setValue(null);
				intezmenyCB.setValue(null);
				
				onUpdate("");
			}
		});
		hl.addComponent(allBtn);

	}
	
	public void refresh() throws SQLException {
		nevCB.refresh();
		nyelvCB.refresh();
		intezmenyCB.refresh();
	}
	
	public void onUpdate(String filter) {
		mlp.setFilter(filter);
		mlp.updateMembersList();
	}

}
