 /*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors. �All third-party contributions are
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

import java.util.List;

import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.properties.AbstractProperty;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class PHPClass implements AbstractClass {
	private final static String defaultNamespace = "dto";
	private String comment;
	private String className;
	private List<AbstractProperty> properties;
	private List<AbstractMethod> methods;
	private String namespace;
	private String parentClass;
	private String[] interfaces;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClassName() {
		
		return className;
	}

	public void setClassName(String className){
		
		this.className = className;
		
	}
	
	
	public List<AbstractProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<AbstractProperty> ascriptProperties){
		this.properties = ascriptProperties;
		
	}

	public List<AbstractMethod> getMethods() {
		// TODO Auto-generated method stub
		return methods;
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
	
	
	
	
	
}
