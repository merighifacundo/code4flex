package org.code4flex.codegenerators.metaas.utils;

import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASIfStatement;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Statement;
import uk.co.badgersinfoil.metaas.dom.Visibility;

public class MetaasUtils {
	public static final ASMethod createConstructor(ASCompilationUnit unit){
		
		ASClassType ctype =  ((ASClassType)unit.getType());
		return ctype.newMethod(ctype.getName(), Visibility.PUBLIC, null);
		
		
	}

	public static void makeClassSingleton(ASClassType modelApplicationClass) {
		modelApplicationClass.newField("instance", Visibility.PRIVATE,modelApplicationClass.getName()).setStatic(true);
		ASMethod method= modelApplicationClass.newMethod("getInstance",Visibility.PUBLIC,modelApplicationClass.getName());
		method.setStatic(true);
		ASIfStatement ifstatement =method.newIf("instance == null");
		ifstatement.addStmt("instance = new " + modelApplicationClass.getName() +"()");
		method.newReturn("instance");
	}
}
