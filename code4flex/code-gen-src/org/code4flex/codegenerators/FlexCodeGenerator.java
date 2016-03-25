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

package org.code4flex.codegenerators;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.code4flex.codegenerators.metaas.ActionScriptClassCodeGenerator;
import org.code4flex.codegenerators.resourcexporter.FlexLibraryResourceExporter;
import org.code4flex.codegenerators.resourcexporter.UnzipResourceExporter;
import org.code4flex.codegenerators.velocity.actionscript.FlexProjectConfigurationTemplate;
import org.code4flex.codegenerators.velocity.actionscript.FlexServiceConfigGenerator;
import org.code4flex.generators.ActionScriptModelGenerator;
import org.code4flex.generators.ServiceExposerGenerator;
import org.code4flex.generators.model.actionscript.ProyectConfigurationTemplate;
import org.code4flex.generators.model.classes.AbstractClass;

import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.ActionScriptProject;

/**
 * The Main generator class for Flex Project!
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.3 $
 */


public abstract class FlexCodeGenerator extends CodeGenerator {

	
	
	//Es: A partir del modelo de la base genera la información para luego generar los contextos de Velocity
	//En: Extracting the DBModel and generating model information to feed the velocity context. 
	protected ServiceExposerGenerator serviceExposerGenerator;
	//Es: Esta clase se encarga de generar distintos tipos de metodos para recibir los eventos!
	//En: This class manage the Command Model Generator.
	
	protected ActionScriptModelGenerator actionScriptModelGenerator;
	
	
	
	//Es: Templates de velocity para la generación de archivos fuente.
	//En: Velocity Templates for Source Code Generation.
	 
	
	
	/* The action script generators will be replaced by metaas generators*/
	//Old velocity generators
	/* 
	private ActionScriptModelCodeGenerator genASModel;
	
	private ActionScriptFlexCairngormCommandCodeGenerator genASCommands;
	private ActionScriptFlexCairngormEventCodeGenerator genASEvents;
	private ActionScriptFlexCairngormControllerCodeGenerator genASController;

	private ActionScriptFlexModelApplicationCodeGenerator genASFlexModelApp;
	*/
	/* End */
	//Only the model generator
	//protected ActionScriptModelCodeGenerator genASModel;
	protected ActionScriptClassCodeGenerator genASModel;
	

	
	
	
	protected FlexLibraryResourceExporter generatorFlexLibrary;
	protected FlexServiceConfigGenerator generatorFlexServiceConfig;
	
	
	protected UnzipResourceExporter generatorFlexproject;
	
	
	
	//project config
	protected ProyectConfigurationTemplate generatorProjectConfig;
	protected FlexProjectConfigurationTemplate generatorASConfig;


	
	protected ActionScriptProject metaasProject;
	
	
	//set metaasProject to ActionScriptCodeGeneration
	protected void initASMetaasProject(){
		
		ActionScriptFactory asFactory = new ActionScriptFactory();
		metaasProject = asFactory.newEmptyASProject(this.proyectDestPath + File.separator + "src");
		
		if(this.generatorFlexLibrary!=null){
		
			String finalPath = this.getTemplatePath() + File.separator + this.generatorFlexLibrary.getSourceDirectory() +File.separator + this.generatorFlexLibrary.getResourceToExport();
			
			metaasProject.addClasspathEntry(finalPath);
		}
	}
	
	
	@Override
	public void generate() {
		try {
			
			
			
			
			//this parse the proyect to charge the velocity code generators
			
			
			this.actionScriptModelGenerator.setNameSpace(this.model.getPhpProjectInformation().getModelPackage());
			actionScriptModelGenerator.init(serviceExposerGenerator.getModelClasses());
			actionScriptModelGenerator.generateModel();
			List<AbstractClass> asModelClassList= actionScriptModelGenerator.getActionScriptClases();
			
			
			
			

			
			//End of parsing
			
			configureProject();
			initASMetaasProject();
			
			

			generatorFlexLibrary.exportResource();
			generatorFlexServiceConfig.setProyectName(this.proyectName);
			
			//This method set the url gateway from the model!
			configureUrlGateway();
			
			
			//Generation Templates to ActionScript this will be replaced for Metaas.
			
			genASModel.setCurrentProject(metaasProject);
			genASModel.setClasses(asModelClassList);
			

			genASModel.generate();

			//Generation Templates to ActionScript this will be replaced for Metaas.
			


	
			
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}



	


	private void configureUrlGateway() {
		// "http://localhost:8888/amfphp/gateway.php"
		if(this.model.getPhpProjectInformation().isHasEnviromentInfo()){
			String hostName = this.model.getPhpProjectInformation().getHostName();
			int portNumber = this.model.getPhpProjectInformation().getPortNumber();
			generatorFlexServiceConfig.setUrlGateway("http://" + hostName + ":" + portNumber + "/amfphp/gateway.php");
		
		}else{
			generatorFlexServiceConfig.setUrlGateway("http://localhost:8888/amfphp/gateway.php");
			
			
		}
	}



	private void configureProject() throws IOException {
		this.generatorProjectConfig.setProyectName(this.proyectName);
		this.generatorProjectConfig.generate();
		this.generatorFlexproject.exportResource();
	}

	
	
	



	public FlexServiceConfigGenerator getGeneratorFlexServiceConfig() {
		return generatorFlexServiceConfig;
	}

	public void setGeneratorFlexServiceConfig(
			FlexServiceConfigGenerator generatorFlexServiceConfig) {
		this.generatorFlexServiceConfig = generatorFlexServiceConfig;
	}

	public FlexLibraryResourceExporter getGeneratorFlexLibrary() {
		return generatorFlexLibrary;
	}

	public void setGeneratorFlexLibrary(
			FlexLibraryResourceExporter generatorFlexLibrary) {
		this.generatorFlexLibrary = generatorFlexLibrary;
	}

	
	public ActionScriptModelGenerator getActionScriptModelGenerator() {
		return actionScriptModelGenerator;
	}

	public void setActionScriptModelGenerator(
			ActionScriptModelGenerator actionScriptModelGenerator) {
		this.actionScriptModelGenerator = actionScriptModelGenerator;
	}

	

	
	
	public ServiceExposerGenerator getServiceExposerGenerator() {
		return serviceExposerGenerator;
	}






	public void setServiceExposerGenerator(
			ServiceExposerGenerator serviceExposerGenerator) {
		this.serviceExposerGenerator = serviceExposerGenerator;
	}






	



	public UnzipResourceExporter getGeneratorFlexproject() {
		return generatorFlexproject;
	}



	public void setGeneratorFlexproject(UnzipResourceExporter generatorFlexproject) {
		this.generatorFlexproject = generatorFlexproject;
	}







	



	


	public ProyectConfigurationTemplate getGeneratorProjectConfig() {
		return generatorProjectConfig;
	}



	public void setGeneratorProjectConfig(
			ProyectConfigurationTemplate generatorProjectConfig) {
		this.generatorProjectConfig = generatorProjectConfig;
	}



	public FlexProjectConfigurationTemplate getGeneratorASConfig() {
		return generatorASConfig;
	}



	public void setGeneratorASConfig(
			FlexProjectConfigurationTemplate generatorASConfig) {
		this.generatorASConfig = generatorASConfig;
	}










	



	public ActionScriptClassCodeGenerator getGenASModel() {
		return genASModel;
	}



	public void setGenASModel(ActionScriptClassCodeGenerator genASModel) {
		this.genASModel = genASModel;
	}




	
	
}
