 /*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Knowledgeit.
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
import org.code4flex.dbloader.model.DBTable;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.php.PhpPropertyMapped;
import org.code4flex.generators.model.php.classes.PHPClass;
import org.code4flex.generators.model.properties.AbstractProperty;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.3 $
 */

public class PhpScriptModelGenerator extends ModelGenerator{

	private List<AbstractClass> phpClases;
	protected List<DBTable> tables;
	
	public List<AbstractClass> getPHPClases() {
		return phpClases;
	}

	public void setPHPClases(List<AbstractClass> phpClases) {
		this.phpClases = phpClases;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(Object obj) {
		 tables = (List<DBTable>) obj;
		
	}

	
	
	@Override
	public void generateModel() throws Exception {
		phpClases = new ArrayList<AbstractClass>();
		
		for (DBTable table : tables) {
			PHPClass aClass = new PHPClass();
			String className = this.getClassNameForTableEntity(table.getTableName());
			aClass.setClassName(className);
			aClass.setComment(table.getTableName());
			aClass.setNamespace(this.nameSpace);
			List<AbstractProperty> properties = new ArrayList<AbstractProperty>();
			for (DBField field : table.getFields()) {
				PhpPropertyMapped prop = new PhpPropertyMapped();
				String propertyName = this.getPropertyNameForTableColumnEntity(field.getColumnName());
				prop.setPropertyName(propertyName);
				if(field.isPrimaryKey())
					prop.setKey(true);
				prop.setPropertyType(field.getClassType().getName());
				prop.setComment("TABLE FIELD: " + field.getColumnName());
				prop.setTableColumnName(field.getColumnName());
				properties.add(prop);
			}
			aClass.setProperties(properties);
			phpClases.add(aClass);
		}

	}

	
	
	
	
}
