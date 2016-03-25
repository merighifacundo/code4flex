package org.code4flex.codegenerators.metaas;

import uk.co.badgersinfoil.metaas.ActionScriptProject;

//implements a class for manage this
public abstract class MetaasCodeGenerator {

	
	protected ActionScriptProject currentProject;
	public MetaasCodeGenerator(ActionScriptProject project){
		
		currentProject = project;
		
	}
	
	public MetaasCodeGenerator(){
		
		
	}
	
	public abstract void generate();

	public ActionScriptProject getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(ActionScriptProject currentProject) {
		this.currentProject = currentProject;
	}
	
	
	
	
	
}
