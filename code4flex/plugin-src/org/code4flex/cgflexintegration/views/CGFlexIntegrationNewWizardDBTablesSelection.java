/*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by KnowledgeIt.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 * 
 * Visit our site: http://knowledgeit.com.ar/code4flex/
 *
 */

package org.code4flex.cgflexintegration.views;

import org.code4flex.cgflexintegration.widgets.ComboDBCatalogSelector;
import org.code4flex.cgflexintegration.widgets.ComboDBSchemaSelector;
import org.code4flex.cgflexintegration.widgets.TableDBModelSelector;
import org.code4flex.cgflexintegration.widgets.listeners.CboSchemaSelectionListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */
 

public class CGFlexIntegrationNewWizardDBTablesSelection extends WizardPage  {

	private ComboDBSchemaSelector cboSchema  = new ComboDBSchemaSelector();
	private ComboDBCatalogSelector cboCatalog = new ComboDBCatalogSelector();
	@SuppressWarnings("unused")
	private ISelection selection;
	private TableDBModelSelector dbTable  = new TableDBModelSelector();

	@Override
	public IWizardPage getNextPage() {
		System.out.println("Next");
		return super.getNextPage();
	}

	@Override
	public boolean isPageComplete() {
		
		return super.isPageComplete();
	}

	public CGFlexIntegrationNewWizardDBTablesSelection(ISelection selection) {
		super("wizardPage3");
		setTitle("KnowledgeIt - CG Flex Integration");
		setDescription("This wizard creates a new Code Generation Flex Integration");
		this.selection = selection;
		
	}

	public void createControl(Composite arg0) {
		
		CboSchemaSelectionListener listener= new CboSchemaSelectionListener();
		Composite container = new Composite(arg0, SWT.NULL);

		container.setLayout(null);
		setControl(container);
		
		
		dbTable.setComposite(container);
		listener.setTableToFilter(dbTable);
		
		cboCatalog.setContainer(container);
		cboCatalog.createCombo();
		cboCatalog.setBounds(90, 5, 61, 21);

		
		cboSchema.setContainer(container);
		cboSchema.createCombo();
		cboSchema.setBounds(232, 5, 61, 21);
		cboSchema.addListener(listener);
		final Label catalogLabel = new Label(container, SWT.NONE);
		catalogLabel.setText("Catalog");
		catalogLabel.setBounds(15, 10, 48, 13);

		final Label catalogLabel_1 = new Label(container, SWT.NONE);
		catalogLabel_1.setBounds(165, 10, 48, 13);
		catalogLabel_1.setText("Schema");
		dbTable.createTable();
		dbTable.setBounds(20, 35, 611, 318);
		
//		dbTable.chargeTable();
		
//		table = new Table(container, SWT.BORDER);
//		table.setLinesVisible(true);
//		table.setHeaderVisible(true);
//		table.setBounds(116, 28, 312, 100);
//
//		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
//		newColumnTableColumn.setWidth(100);
//		newColumnTableColumn.setText("New column");
//
//		final TableItem newItemTableItem = new TableItem(table, SWT.BORDER);
//		newItemTableItem.setText("New item");
		
	}

	public TableDBModelSelector getDbTable() {
		return dbTable;
	}

	public ComboDBSchemaSelector getCboSchema() {
		return cboSchema;
	}

	public ComboDBCatalogSelector getCboCatalog() {
		return cboCatalog;
	}

	


	
	
}
