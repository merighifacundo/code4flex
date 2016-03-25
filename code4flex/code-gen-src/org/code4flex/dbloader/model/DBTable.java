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

package org.code4flex.dbloader.model;

import java.util.List;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class DBTable {
	//Metadata TABLE_CAT
	private String tableCat;
	//Metadata TABLE_SCHEM
	private String tableSchem;
	//Metadata TABLE_NAME => Required
	private String tableName;
	
	private List<DBField> fields = null;
	private List<DBField> fieldsKey = null;
	
	public List<DBField> getFieldsKey() {
		return fieldsKey;
	}
	public void setFieldsKey(List<DBField> fieldsKey) {
		this.fieldsKey = fieldsKey;
	}
	public List<DBField> getFields() {
		return fields;
	}
	public void setFields(List<DBField> fields) {
		this.fields = fields;
	}
	public String getTableCat() {
		return tableCat;
	}
	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}
	public String getTableSchem() {
		return tableSchem;
	}
	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
}
