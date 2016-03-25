package org.code4flex.codegenerators.metaas;

import org.code4flex.codegenerators.metaas.utils.MetaasUtils;
import org.code4flex.generators.model.actionscript.classes.ActionScriptModelApplicationClass;
import org.code4flex.generators.model.classes.AbstractClass;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.Visibility;

public class ActionScriptFlexModelApplicationCodeGenerator extends
		MetaasCodeGenerator {

	private ActionScriptModelApplicationClass modelApplication;
	
	
	@Override
	public void generate() {
		ASCompilationUnit unit = this.currentProject.newClass(modelApplication.getClassName());
		unit.setPackageName(modelApplication.getNamespace());
		ASClassType modelApplicationClass = (ASClassType) unit.getType();
		MetaasUtils.makeClassSingleton(modelApplicationClass);
		for (AbstractClass modelClass : modelApplication.getModel()) {
			modelApplicationClass.newField(modelApplication.obtenerDtoArrayVariable(modelClass), Visibility.PUBLIC, "mx.collections.ArrayCollection");
			
		}
	}

	
	public void setController(ActionScriptModelApplicationClass controller) {
		this.modelApplication = controller;

	}
}
