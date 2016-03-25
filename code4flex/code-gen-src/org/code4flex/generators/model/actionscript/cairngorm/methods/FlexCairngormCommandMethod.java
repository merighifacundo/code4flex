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
package org.code4flex.generators.model.actionscript.cairngorm.methods;

import java.io.File;
import java.util.Iterator;

import org.code4flex.codegenerators.velocity.utils.MethodCodeGenerator;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormCommandClass;
import org.code4flex.generators.model.actionscript.methods.ActionScriptMethod;
import org.code4flex.generators.model.actionscript.methods.ActionScriptMethodParameter;
import org.code4flex.generators.model.methoddescriptors.GenerationCodeForTemplateMethodDescriptor;
import org.code4flex.generators.model.methoddescriptors.MetaasMethodDescriptor;
import org.code4flex.generators.model.methoddescriptors.MethodDescriptor;
import org.code4flex.generators.model.methods.AbstractMethod;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class FlexCairngormCommandMethod extends ActionScriptMethod implements AbstractMethod {

	private String methodName;
	private String methodType;
	private FlexCairngormCommandClass command;
	private String methodCode = null;
	private File xmlToMetaas = null;
	private MethodDescriptor methodDescriptor;
	
	public FlexCairngormCommandMethod(MethodDescriptor methodDescriptor){
		this.methodDescriptor = methodDescriptor;
		
		Iterator<String> argumentNameIterator = methodDescriptor.getArguments().iterator();
		Iterator<String> argumentTypeIterator = methodDescriptor.getArgumentsType().iterator();
		while(argumentNameIterator.hasNext() && argumentTypeIterator.hasNext()){
			String argName = argumentNameIterator.next();
			String argType = argumentTypeIterator.next();
			ActionScriptMethodParameter parameter = new ActionScriptMethodParameter();
			parameter.setParameterName(argName);
			parameter.setParameterType(argType);
			this.getParameterList().add(parameter);
			
			
		}
		this.setMethodName(methodDescriptor.getParcialName());
		this.setMethodType(methodDescriptor.getMethodType());
		
		if(methodDescriptor instanceof MetaasMethodDescriptor){
			MetaasMethodDescriptor metaasDescriptor = (MetaasMethodDescriptor) methodDescriptor;
			this.xmlToMetaas = metaasDescriptor.getXmlToMetaas();
		}
		
	}

	

	

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public FlexCairngormCommandClass getCommand() {
		return command;
	}

	public void setCommand(FlexCairngormCommandClass command) {
		this.command = command;
	}

	public File getXmlToMetaas() {
		return xmlToMetaas;
	}





	public String getMethodCode() {
		if(methodCode==null){
			methodCode = MethodCodeGenerator.generateMethodCode(this, ((GenerationCodeForTemplateMethodDescriptor)methodDescriptor).getMethodCodeTemplate());	
		}
		return methodCode;
	}
	public String getModelProperty(){
		return this.command.getActionScriptModelApplication().obtenerDtoArrayVariable(this.command.getRelation().getEvent().getModelClass());
		
		
	}
	
	
	
}
