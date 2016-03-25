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

import java.math.BigDecimal;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */


public class DBField {
	//Metadata COLUMN_NAME
	private String columnName;
	//Metadata TYPE_NAME
	private String typeName;
	//Metadata COLUMN_SIZE
	private int columnSize;
	//Metadata NULLABLE: nullable == 1
	private int nullable;
	//Primarykey
	private boolean primaryKey;
	//DATA_TYPE
	private short dataType;
	
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	public short getDataType() {
		return dataType;
	}
	public void setDataType(short dataType) {
		this.dataType = dataType;
	}
	
	@SuppressWarnings("unchecked")
	public Class getClassType(){
		
		switch(this.dataType){
			case java.sql.Types.VARCHAR: return String.class;
			case java.sql.Types.DECIMAL: return BigDecimal.class;
			case java.sql.Types.DATE: return java.sql.Date.class;
			case java.sql.Types.BIGINT: return Long.class;
			case java.sql.Types.DOUBLE: return Double.class;
			default: return Object.class;
		}
		
	}
	@SuppressWarnings("unchecked")
	public static Class getClassType(String strDataType) {
		try {
			return Class.forName(strDataType);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
		
	}
}
