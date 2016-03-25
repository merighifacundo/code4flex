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
package org.code4flex.generators.model.php.methods;

import java.util.Iterator;
import java.util.List;

import org.code4flex.codegenerators.velocity.utils.MethodCodeGenerator;
import org.code4flex.generators.model.methoddescriptors.GenerationCodeForTemplateMethodDescriptor;
import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.methods.AbstractMethodParameter;
import org.code4flex.generators.model.php.classes.PhpDaoClass;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class PhpDaoMethod implements AbstractMethod {

	private String methodName;
	private List<AbstractMethodParameter> parameterList;

	private String queryVariable;
	private PhpDaoClass daoClass;

	private String methodType;
	private String methodCode = null;
	private GenerationCodeForTemplateMethodDescriptor methodDescriptor;

	public PhpDaoMethod(
			GenerationCodeForTemplateMethodDescriptor methodDescriptor) {
		if (methodDescriptor.getArguments() != null &&  methodDescriptor
				.getArgumentsType()!=null) {
			Iterator<String> argumentNameIterator = methodDescriptor
					.getArguments().iterator();
			Iterator<String> argumentTypeIterator = methodDescriptor
					.getArgumentsType().iterator();
			while (argumentNameIterator.hasNext()
					&& argumentTypeIterator.hasNext()) {
				String argName = argumentNameIterator.next();
				String argType = argumentTypeIterator.next();
				PHPMethodParameter parameter = new PHPMethodParameter();
				parameter.setParameterName(argName);
				parameter.setParameterType(argType);
				this.getParameterList().add(parameter);

			}
		}
		this.setMethodName(methodDescriptor.getMethodName());
		this.setQueryVariable(methodDescriptor.getQueryVariable());
		this.methodDescriptor = methodDescriptor;

	}

	public String getMethodName() {

		return methodName;
	}

	public List<AbstractMethodParameter> getParameterList() {

		return parameterList;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setParameterList(List<AbstractMethodParameter> parameterList) {
		this.parameterList = parameterList;
	}

	public String getQueryVariable() {
		return queryVariable;
	}

	public void setQueryVariable(String queryVariable) {
		this.queryVariable = queryVariable;
	}

	public void setDaoClass(PhpDaoClass daoClass) {
		this.daoClass = daoClass;

	}

	public PhpDaoClass getDaoClass() {
		return daoClass;
	}

	public String getMethodType() {
		return this.methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodCode() {
		if (methodCode == null) {
			methodCode = MethodCodeGenerator.generateMethodCode(this,
					methodDescriptor.getMethodCodeTemplate());
		}
		return methodCode;
	}

}
