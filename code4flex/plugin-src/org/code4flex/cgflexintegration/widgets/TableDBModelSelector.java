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

package org.code4flex.cgflexintegration.widgets;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.observer.Observer;
import org.code4flex.cgflexintegration.observer.Subject;
import org.code4flex.dbloader.model.DBTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class TableDBModelSelector implements Observer {

	
	private Composite composite;
	private List<DBTable> dbTables;
	private Table dbmodelselectiontable = null;
	
	
	public void createTable(){
		

		
		dbmodelselectiontable = new Table(composite,SWT.CHECK|SWT.HIDE_SELECTION);
		dbmodelselectiontable.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		    	 
		    	  String string = event.detail == SWT.CHECK ? "Checked"
				            : "Selected";
		    	  
		    	  
		    	  
		        System.out.println(event.item + " " + string);
		      }
		    });
		dbmodelselectiontable.setHeaderVisible(true);
		dbmodelselectiontable.setLinesVisible(true);
	}
	
	public void setBounds(int a,int b, int c, int d){
		
		dbmodelselectiontable.setBounds(a, b, c,d);
		
	}
	
	public void chargeTable(){
		try {
			if(!this.dbmodelselectiontable.isDisposed()){
			this.dbmodelselectiontable.removeAll();
			this.dbmodelselectiontable.clearAll();
			}else{
				this.createTable();
			}
		} catch (Exception e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();
		}
		
		final TableColumn tc = new TableColumn(this.dbmodelselectiontable,SWT.LEFT);
		tc.setText("Table Name");
		tc.setWidth(150);
		final TableColumn tc2 = new TableColumn(this.dbmodelselectiontable,SWT.LEFT);
		tc2.setText("Table schema");
		tc2.setWidth(100);
		final TableColumn tc3 = new TableColumn(this.dbmodelselectiontable,SWT.LEFT);
		tc3.setText("Table catalog");
		tc3.setWidth(100);
		for (DBTable dbTable : dbTables) {
			TableItem item = new TableItem(this.dbmodelselectiontable,SWT.NONE);
			item.setText(new String[]{dbTable.getTableName(),(dbTable.getTableSchem()==null)?"":dbTable.getTableSchem(),(dbTable.getTableCat()==null)?"":dbTable.getTableCat()});
			item.setChecked(true);
		}
		this.dbmodelselectiontable.redraw();
	}
	public List<DBTable> getDbTables() {
		return dbTables;
	}
	public void setDbTables(List<DBTable> dbTables) {
		this.dbTables = dbTables;
	}
	
	public Composite getComposite() {
		return composite;
	}
	public void setComposite(Composite composite) {
		this.composite = composite;
	}
	
	public void update(Subject o) {
		PluginModel model = (PluginModel) o;
		this.setDbTables(model.getModel());
		this.chargeTable();
		
	}
	public void filterSchema(String schema){
		chargeTable();
		TableItem [] items = this.dbmodelselectiontable.getItems();
		ArrayList<Integer> titem = new ArrayList<Integer>();
		for (int i = 0; i < items.length; i++) {
			if(!schema.equalsIgnoreCase(items[i].getText(1))){
				titem.add(i);
			}
		}
		int array[] = new int[titem.size()];
		int i=0;
		for (Integer integer : titem) {
			array[i] = integer;
			i++;
		}

		this.dbmodelselectiontable.remove(array);
	}
	public List<DBTable> getCheckedRows(){
		List<DBTable> listaDBTable = new ArrayList<DBTable>();
		TableItem [] items = this.dbmodelselectiontable.getItems();
		
		for (int i = 0; i < items.length; i++) {
			DBTable table = null;
			if(items[i].getChecked()){
				
				String tableName = items[i].getText(0);
				String tableSchema = items[i].getText(1);
				String tableCatalog = items[i].getText(2);
				table = buscarItem(tableName,tableSchema,tableCatalog);
				if(table!=null){
					listaDBTable.add(table);
				}
			}
		}
		return listaDBTable;
		
		
	}

	private DBTable buscarItem(String tableName, String tableSchema,
			String tableCatalog) {
		for (DBTable dbTable : this.dbTables) {
			if(dbTable.getTableName()!= null && tableName!= null && dbTable.getTableName().equalsIgnoreCase(tableName)){
				if(dbTable.getTableSchem()!=null && tableSchema != null && dbTable.getTableSchem().equalsIgnoreCase(tableSchema))
					return dbTable;
				if("".equalsIgnoreCase(tableSchema))
					return dbTable;
				
			}
		}
		return null;
		
	}
}
