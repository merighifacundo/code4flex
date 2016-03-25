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

import org.code4flex.generators.model.Operation;
import org.code4flex.generators.model.classes.AbstractServiceClass;
import org.code4flex.generators.model.methods.AbstractMethod;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class CairngormServiceLocatorModelGenerator {
	private String id;
	private String source;
	private List<Operation> operations = new ArrayList<Operation>();
	private List<AbstractMethod> methods = new ArrayList<AbstractMethod>();

	public CairngormServiceLocatorModelGenerator(AbstractServiceClass daoClass) {
		init(daoClass);

	}

	public CairngormServiceLocatorModelGenerator() {

	}

	public void init(AbstractServiceClass serviceClass) {
		source = serviceClass.getNamespace() + "." + serviceClass.getClassName();
		id = serviceClass.getClassName() + "Remote";
		if (serviceClass.getServiceMethods() != null) {
			for (AbstractMethod method : serviceClass.getServiceMethods()) {
				methods.add(method);
				Operation op = new Operation();
				op.setNombre(method.getMethodName());
				op.setMethod(method);
				operations.add(op);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public List<AbstractMethod> getMethods() {
		return methods;
	}

}
