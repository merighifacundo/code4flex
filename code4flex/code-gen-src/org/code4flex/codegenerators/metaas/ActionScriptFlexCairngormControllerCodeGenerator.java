package org.code4flex.codegenerators.metaas;

import org.code4flex.codegenerators.metaas.utils.MetaasUtils;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormControllerClass;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormControllerEventRelation;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASMethod;

public class ActionScriptFlexCairngormControllerCodeGenerator extends
		MetaasCodeGenerator {

	private FlexCairngormControllerClass controller;
	
	
	@Override
	public void generate() {
		ASCompilationUnit unit = this.currentProject.newClass(controller.getClassName());
		unit.setPackageName(controller.getNamespace());
		ASClassType asController = (ASClassType) unit.getType();
		asController.setSuperclass(controller.getParentClass());
		ASMethod constructor = MetaasUtils.createConstructor(unit);
		constructor.addStmt("super()");
		for (FlexCairngormControllerEventRelation relation : controller.getRelations()) {
			//addCommand(${relation.event.className}.EVENT_ID,${relation.command.className});
			constructor.addStmt("addCommand(" + relation.getEvent().getClassName() +".EVENT_ID," +  relation.getCommand().getClassName() +")");
		}
	}


	public void setController(FlexCairngormControllerClass controller) {
		this.controller = controller;
	}

}
