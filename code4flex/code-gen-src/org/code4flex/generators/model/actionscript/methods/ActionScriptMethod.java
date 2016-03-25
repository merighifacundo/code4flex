package org.code4flex.generators.model.actionscript.methods;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.generators.model.methods.AbstractMethod;
import org.code4flex.generators.model.methods.AbstractMethodParameter;


public class ActionScriptMethod implements AbstractMethod {

	
	private String methodName;
	private String methodType;
	private List<AbstractMethodParameter> parameterList = new ArrayList<AbstractMethodParameter>();
	public String getMethodName() {
		return methodName;
	}

	public String getMethodType() {
		return methodType;
	}

	public List<AbstractMethodParameter> getParameterList() {
		return parameterList;
	}



	
	
}
