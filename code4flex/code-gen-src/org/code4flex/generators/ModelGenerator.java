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


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public abstract class ModelGenerator {

	
	protected String nameSpace;
	
	
	public abstract void init(Object obj);
	
	public abstract void generateModel() throws Exception;
	
	protected String getClassNameForTableEntity(String tableName) {
		String strings[] = tableName.split("_");
		String outString = "";
		for (int i = 0; i < strings.length; i++) {

			outString += strings[i].substring(0, 1).toUpperCase()
					+ strings[i].substring(1, strings[i].length())
							.toLowerCase();

		}
		return outString;

		
	}

	protected String getPropertyNameForTableColumnEntity(String fieldName) {
		String strings[] = fieldName.split("_");
		String outString = strings[0].toLowerCase();
		for (int i = 1; i < strings.length; i++) {

			outString += strings[i].substring(0, 1).toUpperCase()
					+ strings[i].substring(1, strings[i].length())
							.toLowerCase();

		}
		return outString;

		
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	

}
