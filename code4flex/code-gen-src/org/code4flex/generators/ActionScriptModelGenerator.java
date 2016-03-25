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

package org.code4flex.generators;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.dbloader.model.DBField;
import org.code4flex.generators.model.ActionScriptProperty;
import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.mapper.JavaActionScriptMapper;
import org.code4flex.generators.model.properties.AbstractProperty;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */
 

public class ActionScriptModelGenerator extends ModelGenerator {

	private List<AbstractClass> actionScriptClases;
	protected List<AbstractClass> clases =null;

	public List<AbstractClass> getActionScriptClases() {
		return actionScriptClases;
	}

	public void setActionScriptClases(List<AbstractClass> actionScriptClases) {
		this.actionScriptClases = actionScriptClases;
	}


	
	
	
	public void generateModel()throws Exception{
		actionScriptClases = new ArrayList<AbstractClass>();
		for (AbstractClass claseModelo : clases) {
			ActionScriptClass aClass = new ActionScriptClass();
			aClass.setClassName(claseModelo.getClassName());
			//Using the namespace setted by the model
			aClass.setNamespace(claseModelo.getNamespace());
			for (AbstractProperty prop : claseModelo.getProperties()) {
				ActionScriptProperty property = new ActionScriptProperty();
				property.setPropertyName(prop.getPropertyName());
				property.setPropertyType(JavaActionScriptMapper.getType(DBField.getClassType(prop.getPropertyType())));
//				prop.setPropertyType(JavaActionScriptMapper.getType(field.getClassType()));
				aClass.getProperties().add(property);
				
			}
			actionScriptClases.add(aClass);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(Object obj) {
		clases = (List<AbstractClass>) obj;
		
	}
	
//	@Override
//	public void generateModel(List<DBTable> tables) throws Exception {
//		actionScriptClases = new ArrayList<AbstractClass>();
//		
//		for (DBTable table : tables) {
//			ActionScriptClass aClass = new ActionScriptClass();
//			String className = this.getClassNameForTableEntity(table.getTableName());
//			aClass.setClassName(className);
//			aClass.setNamespace(this.nameSpace);
//			List<AbstractProperty> properties = new ArrayList<AbstractProperty>();
//			for (DBField field : table.getFields()) {
//				ActionScriptProperty prop = new ActionScriptProperty();
//				String propertyName = this.getPropertyNameForTableColumnEntity(field.getColumnName());
//				prop.setPropertyName(propertyName);
//				
//				prop.setPropertyType(JavaActionScriptMapper.getType(field.getClassType()));
//				properties.add(prop);
//			}
//			aClass.setProperties(properties);
//			actionScriptClases.add(aClass);
//		}
//
//	}

	
	
	
	
	
}
