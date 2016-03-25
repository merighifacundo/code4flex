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

package org.code4flex.codegenerators.metaas;

import java.util.Iterator;
import java.util.List;

import org.code4flex.codegenerators.metaas.utils.MetaasUtils;
import org.code4flex.generators.model.Operation;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormEventClass;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Visibility;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class ActionScriptFlexCairngormEventCodeGenerator extends
		MetaasCodeGenerator {
	

	private List<FlexCairngormEventClass> eventList;

	@Override
	public void generate() {
		try {

			
			Iterator<FlexCairngormEventClass> it = eventList.iterator();
			while (it.hasNext()) {
				FlexCairngormEventClass eventClass = it.next();
				
				ASCompilationUnit unit = this.currentProject.newClass(eventClass.getClassName());
				unit.setPackageName(eventClass.getNamespace());
				
				ASClassType ctype = (ASClassType) unit.getType();
				ctype.setSuperclass(eventClass.getParentClass());
				//This creates the event id constant.
				createEventId(eventClass, ctype);
				
				//This creates the operations fields constant.
				createOperationsFields(eventClass, ctype);
				
				
				//Creates method to call
				ctype.newField("methodToCall",Visibility.PUBLIC,"String");
				ASField parameterField = ctype.newField("parameter",Visibility.PUBLIC,eventClass.getModelClass().getNamespace() + "." + eventClass.getModelClass().getClassName());
				parameterField.newMetaTag("Binding");
				ASMethod method = MetaasUtils.createConstructor(unit);
				method.addParam("methodToCall", "String");
				
				
				method.addStmt("super(EVENT_ID)");
				method.addStmt("this.methodToCall = methodToCall");
				method.addStmt("this.parameter = null");
			}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}




	private void createOperationsFields(FlexCairngormEventClass eventClass,
			ASClassType ctype) {
		for (Operation op : eventClass.getService().getOperations()) {
			ASField operationField = ctype.newField(op.getNombre(),Visibility.PUBLIC,"String");
			operationField.setStatic(true);
			operationField.setInitializer("\""+op.getNombre()+"\"");
			operationField.setConst(true);
		}
	}




	private void createEventId(FlexCairngormEventClass eventClass,
			ASClassType ctype) {
		ASField eventIdField = ctype.newField("EVENT_ID",Visibility.PUBLIC,"String");

		eventIdField.setInitializer("\"" + eventClass.getEventId() + "\"");
		eventIdField.setStatic(true);
		eventIdField.setConst(true);
	}

	


	public void setClasses(List<FlexCairngormEventClass> eventList) {
		this.eventList = eventList;
		
	}

}
