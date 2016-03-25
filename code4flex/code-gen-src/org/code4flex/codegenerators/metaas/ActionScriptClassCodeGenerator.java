package org.code4flex.codegenerators.metaas;

import java.util.List;

import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;
import org.code4flex.generators.model.actionscript.methods.ActionScriptMethod;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.methods.AbstractMethodParameter;
import org.code4flex.generators.model.properties.AbstractProperty;

import uk.co.badgersinfoil.metaas.ActionScriptProject;
import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Visibility;

public class ActionScriptClassCodeGenerator extends MetaasCodeGenerator {

	
	List<AbstractClass> classes = null;
	
	public ActionScriptClassCodeGenerator(ActionScriptProject project) {
		super(project);
		
	}

	public ActionScriptClassCodeGenerator() {
		
		super();
		
	}
	
	@Override
	public void generate() {
		for (AbstractClass classToGenerate : classes) {
			ActionScriptClass myASClass = (ActionScriptClass) classToGenerate;
			ASCompilationUnit unit =  this.currentProject.newClass(myASClass.getClassName());
			unit.setPackageName(myASClass.getNamespace());
			ASClassType asclass = (ASClassType) unit.getType();
			asclass.newMetaTag("Bindable");
			asclass.setDescription(classToGenerate.getComment());
			for (AbstractProperty property :  myASClass.getProperties()) {
				asclass.newField(property.getPropertyName(), Visibility.PUBLIC, property.getPropertyType());
			}
			for ( AbstractMethod m : myASClass.getMethods()) {
				ActionScriptMethod method = (ActionScriptMethod) m; 
				ASMethod asmethod = asclass.newMethod(method.getMethodName(), Visibility.PUBLIC, method.getMethodType());
				for (AbstractMethodParameter mparameter : method.getParameterList()) {
					asmethod.addParam(mparameter.getParameterName(),mparameter.getParameterType());
				}
				
			
			
			}
			
		}
		
		
		
	}

	public List<AbstractClass> getClasses() {
		return classes;
	}

	public void setClasses(List<AbstractClass> classes) {
		this.classes = classes;
	}

}
