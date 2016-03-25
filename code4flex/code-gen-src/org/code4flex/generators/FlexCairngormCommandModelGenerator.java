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

import java.util.List;

import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormCommandClass;
import org.code4flex.generators.model.actionscript.cairngorm.methods.FlexCairngormCommandMethod;
import org.code4flex.generators.model.methoddescriptors.GenerationCodeForTemplateMethodDescriptor;
import org.code4flex.generators.model.methoddescriptors.MethodDescriptor;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.3 $
 */

public class FlexCairngormCommandModelGenerator  {
	private List<MethodDescriptor> methodsDescriptors;
	
	/**
	 * @author fmerighi
	 * @param FlexCairngormCommandClass
	 * @return FlexCairngormCommandClass
	 * This method get a class command and add all the FlexCairngormCommandMethod 
	 * this implementation at the design moment was with velocity now we are migrating to Metaas
	 */
	public FlexCairngormCommandClass generateModel(FlexCairngormCommandClass classesCommand) throws Exception {
		for (MethodDescriptor methodDescriptor : methodsDescriptors) {
			
			FlexCairngormCommandMethod method = new FlexCairngormCommandMethod(methodDescriptor);
			
			method.setCommand(classesCommand);
			//method.generateMethodCode();
			classesCommand.getMethods().add(method);
		}
		return classesCommand;
	}

	

	public List<MethodDescriptor> getMethodsDescriptors() {
		return methodsDescriptors;
	}

	public void setMethodsDescriptors(
			List<MethodDescriptor> methodsDescriptors) {
		this.methodsDescriptors = methodsDescriptors;
	}

}
