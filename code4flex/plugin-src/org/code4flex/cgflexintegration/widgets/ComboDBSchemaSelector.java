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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.observer.Observer;
import org.code4flex.cgflexintegration.observer.Subject;
import org.code4flex.dbloader.model.DBTable;
import org.eclipse.swt.events.SelectionListener;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */


public class ComboDBSchemaSelector extends ComboDBModel implements Observer {
	List<String> schemas = new ArrayList<String>();
	public void update(Subject o) {
		schemas = new ArrayList<String>();
		PluginModel model = (PluginModel) o;
		List<DBTable> list =model.getModel();
		Hashtable<String, String> table  = new Hashtable<String,String>();
		for (DBTable dbtable : list) {
			String sch = dbtable.getTableSchem();
			if(sch!=null){
				
				table.put(sch, sch);
				
			}
		}
		Enumeration<String> enumeration = table.keys();
		while(enumeration.hasMoreElements()){
			schemas.add(enumeration.nextElement());
			
		}
		chargeCombo();
		
	}
	@Override
	public void chargeCombo() {
		for (String key : schemas) {
			this.combo.add(key);
			this.combo.setData(key, key);
		}
		this.combo.redraw();
		
	}
	
	public void addListener(SelectionListener listener){
		
		this.combo.addSelectionListener(listener);
		
	}
	

}
