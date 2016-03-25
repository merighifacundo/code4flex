package org.code4flex.codegenerators.metaas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.code4flex.generators.model.Operation;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormCommandClass;
import org.code4flex.generators.model.actionscript.cairngorm.methods.FlexCairngormCommandMethod;
import org.code4flex.generators.model.actionscript.methods.ActionScriptMethod;
import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.methods.AbstractMethodParameter;
import org.code4flex.xmltometaas.XmlToMetaasProcessor;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASField;
import uk.co.badgersinfoil.metaas.dom.ASIfStatement;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Visibility;

public class ActionScriptFlexCairngormCommandCodeGenerator extends
		MetaasCodeGenerator {

	private List<FlexCairngormCommandClass> classes = null;
	
	
	/*
	 * 
	 * public function execute(event:CairngormEvent):void
	 * var myEvent:${command.relation.event.className} = event as ${command.relation.event.className};
			var remotingObject:RemoteObject = service.getRemoteObject("${command.service.id}");
			
			
			var operation:AbstractOperation = remotingObject.getOperation(myEvent.methodToCall);
			
			if(!operation.hasEventListener(ResultEvent.RESULT)){
			
			#foreach( $operation in $command.relation.event.service.operations )
				if(myEvent.methodToCall == ${command.relation.event.className}.$operation.nombre){
					operation.addEventListener(ResultEvent.RESULT,${command.obtenerListenerMethodName($operation.method.methodType)});
				}
			#end
			}
			if(myEvent.parameter == null)
				operation.send();
			else
				operation.send(myEvent.parameter);*/
	
	
	@Override
	public void generate() {
		for (FlexCairngormCommandClass commandParsed : classes) {
			ASCompilationUnit asUnit= this.currentProject.newClass(commandParsed.getClassName());
			asUnit.setPackageName(commandParsed.getNamespace());
			ASClassType commandClass = (ASClassType) asUnit.getType();
			for (int i = 0; i < commandParsed.getInterfaces().length; i++) {
				commandClass.addImplementedInterface(commandParsed.getInterfaces()[i]);
			}
			ASField serviceLocatorProperty = commandClass.newField("service", Visibility.PRIVATE, "ServiceLocator");
			serviceLocatorProperty.setInitializer("ServiceLocator.getInstance()");
			ASMethod executeMethod = commandClass.newMethod("execute", Visibility.PUBLIC, "void");
			executeMethod.addParam("event","CairngormEvent");
			String eventClassName =  commandParsed.getRelation().getEvent().getClassName();
			executeMethod.addStmt("var myEvent:" + eventClassName +" = event as " + eventClassName);
			executeMethod.addStmt("var operation:AbstractOperation = remotingObject.getOperation(myEvent.methodToCall);");
			ASIfStatement ifstatement = executeMethod.newIf("!operation.hasEventListener(ResultEvent.RESULT)");
			for (Operation operacion : commandParsed.getRelation().getEvent().getService().getOperations()) {
				//${command.obtenerListenerMethodName($operation.method.methodType)}
				ifstatement.newIf("myEvent.methodToCall == " + eventClassName + "." + operacion.getNombre()).addStmt("operation.addEventListener(ResultEvent.RESULT," + commandParsed.obtenerListenerMethodName(operacion.getMethod().getMethodType()) +");");
			}
			ifstatement = executeMethod.newIf("myEvent.parameter == null");
			ifstatement.addStmt("operation.send()");
			ifstatement.elseBlock().addStmt("operation.send(myEvent.parameter)");
			
			for(AbstractMethod _mparsed : commandParsed.getMethods()){
				 FlexCairngormCommandMethod methodParsed = (FlexCairngormCommandMethod) _mparsed;
				 ASMethod method = commandClass.newMethod(methodParsed.getMethodName(), Visibility.DEFAULT,methodParsed.getMethodType());
				 
				 
				 
				 
				
				 
				 for (AbstractMethodParameter mparameter : methodParsed.getParameterList()) {
					 method.addParam(mparameter.getParameterName(),mparameter.getParameterType());
				}
//				 for (String statement : methodParsed.getStatements()) {
//					method.addStmt(statement);
//
//				}
				 Map root = new HashMap();
				 root.put("method", methodParsed);
				 XmlToMetaasProcessor.process(method, root, methodParsed.getXmlToMetaas());
			}
		}

	}

	
	public void setClasses(List<FlexCairngormCommandClass> classes) {
		this.classes = classes;
	}
	
}
