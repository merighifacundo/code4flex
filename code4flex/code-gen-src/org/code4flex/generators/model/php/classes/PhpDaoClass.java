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
package org.code4flex.generators.model.php.classes;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.dbloader.model.DBTable;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;
import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.properties.AbstractProperty;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

/**
 * Si se va a publicar como un servicio debe implementar ademas ServiceAbstractClass
 */
public class PhpDaoClass implements AbstractServiceClass {

	private final static String defaultNamespace = "dao";
	private String className;
	private String comment;
	private List<AbstractProperty> properties;
	private PHPClass dtoClass;
	private DBTable dtoTable;
	private List<AbstractMethod> methods = new ArrayList<AbstractMethod>();
	private String namespace;
	private String parentClass;
	private String[] interfaces;
	
	public DBTable getDtoTable() {
		return dtoTable;
	}

	public void setDtoTable(DBTable dtoTable) {
		this.dtoTable = dtoTable;
	}

	public PHPClass getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(PHPClass dtoClass) {
		this.dtoClass = dtoClass;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setProperties(List<AbstractProperty> properties) {
		this.properties = properties;
	}

	public String getClassName() {
		return className;
	}

	public String getComment() {
		return comment;
	}

	public List<AbstractProperty> getProperties() {
		return properties;
	}

	public List<AbstractMethod> getMethods() {
		return this.methods;
	}

	public void setMethods(List<AbstractMethod> methods) {
		this.methods = methods;
	}

	public String getNamespace() {
		if(namespace==null){
			namespace = defaultNamespace;
			
		}
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public String getParentClass() {
		return this.parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}

	public boolean isChild() {
		if(this.parentClass==null)
			return true;
		return false;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}

	public boolean isImplementing() {
		return (interfaces != null && interfaces.length >0 );
	}

	public AbstractClass getAsociatedModelClass() {
		return this.getDtoClass();
	}

	public String getServiceCommandName() {
		return this.getDtoClass().getClassName() + "Command";
	}

	public String getServiceEventName() {
		return this.getDtoClass().getClassName() + "Event";
	}

	public List<AbstractMethod> getServiceMethods() {
		return this.getMethods();
	}

	public String getServiceViewName() {
		return this.getDtoClass().getClassName() + "View";
	}
	
	
	
	
	
}
