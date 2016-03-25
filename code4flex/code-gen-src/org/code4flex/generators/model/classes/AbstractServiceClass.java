package org.code4flex.generators.model.classes;

import java.util.List;

import org.code4flex.generators.model.methods.AbstractMethod;

public interface AbstractServiceClass extends AbstractClass {

	public List<AbstractMethod> getServiceMethods();

	public String getServiceCommandName();

	public String getServiceEventName();

	public String getServiceViewName();

	public AbstractClass getAsociatedModelClass();

	
	
	
}
