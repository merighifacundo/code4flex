package org.code4flex.generators;

import java.util.List;

import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;


public interface ServiceExposerGenerator {
	public List<AbstractServiceClass> getServiceClasses();
	
	//This method is called for generating Flex Model Classes
	public List<AbstractClass> getModelClasses();
}
