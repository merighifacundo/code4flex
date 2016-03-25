package org.code4flex.codegenerators;

import java.util.List;

import org.code4flex.codegenerators.flexparser.FlexCairngormParser;
import org.code4flex.codegenerators.helpers.NamesConversionHelper;
import org.code4flex.codegenerators.metaas.ActionScriptFlexCairngormControllerCodeGenerator;
import org.code4flex.codegenerators.metaas.ActionScriptFlexCairngormEventCodeGenerator;
import org.code4flex.codegenerators.metaas.ActionScriptFlexCairngormCommandCodeGenerator;
import org.code4flex.codegenerators.metaas.ActionScriptFlexModelApplicationCodeGenerator;
import org.code4flex.codegenerators.velocity.actionscript.FlexCairngormServiceLocatorGenerator;
import org.code4flex.codegenerators.velocity.actionscript.FlexEntryPointCodeGenerator;
import org.code4flex.codegenerators.velocity.actionscript.FlexSimpleViewGenerator;
import org.code4flex.generators.FlexCairngormCommandModelGenerator;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormCommandClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;


public class FlexCairngormCodeGenerator extends FlexCodeGenerator {

	protected FlexCairngormCommandModelGenerator cairngormCommandModelGenerator;

	protected ActionScriptFlexCairngormCommandCodeGenerator genASCommands;
	protected ActionScriptFlexCairngormEventCodeGenerator genASEvents;
	protected ActionScriptFlexCairngormControllerCodeGenerator genASController;

	protected ActionScriptFlexModelApplicationCodeGenerator genASFlexModelApp;

	protected FlexCairngormServiceLocatorGenerator generatorFlexCairngormServiceLocator;

	// project ViewGenerator
	protected FlexSimpleViewGenerator generatorFlexSimpleView;

	//project Entry Point
	protected FlexEntryPointCodeGenerator generatorEntryPoint;
	
	
	@Override
	public void generate() {

		try {
			super.generate();

			
			List<AbstractServiceClass> serviceClasses = serviceExposerGenerator.getServiceClasses();
			if(serviceClasses == null || serviceClasses.isEmpty()){
				
				throw new Exception("Las clases del modelo en php debe estar correctamente populadas antes de genrar el proyecto flex");
				
			}
			
			FlexCairngormParser flexProjectParser = new FlexCairngormParser();
			flexProjectParser.parse(serviceClasses, model, actionScriptModelGenerator.getActionScriptClases());
			generatorFlexCairngormServiceLocator.setServices(flexProjectParser
					.getList());
			generatorFlexCairngormServiceLocator.setNamespace(this.model
					.getFlexProjectInformation().getServicePackage());
			generatorFlexCairngormServiceLocator
					.setProyectName(NamesConversionHelper
							.toPascalCaseConversion(this.proyectName));
			generatorFlexCairngormServiceLocator
					.setServiceLocatorName(this.model
							.getFlexProjectInformation()
							.getServiceLocatorName());
			generatorFlexCairngormServiceLocator.generate();

			for (FlexCairngormCommandClass command : flexProjectParser
					.getCommandList()) {
				command = cairngormCommandModelGenerator.generateModel(command);
			}

			//genASCommands.setProyectName(this.proyectName);
			genASCommands.setCurrentProject(this.metaasProject);
			genASCommands.setClasses(flexProjectParser.getCommandList());
			
			genASEvents.setCurrentProject(this.metaasProject);
			//genASEvents.setProyectName(this.proyectName);
			genASEvents.setClasses(flexProjectParser.getEventList());

			genASController.setCurrentProject(this.metaasProject);
			genASController.setController(flexProjectParser.getController());

			genASEvents.generate();
			genASCommands.generate();
			genASController.generate();

			//genASFlexModelApp.setProyectName(this.proyectName);
			genASFlexModelApp.setCurrentProject(this.metaasProject);
			genASFlexModelApp.setController(flexProjectParser
					.getAppModelClass());

			genASFlexModelApp.generate();

			generatorFlexSimpleView.setProyectName(this.proyectName);
			generatorFlexSimpleView.setClasses(flexProjectParser
					.getSimpleViewList());
			generatorFlexSimpleView
					.setActionScriptModelApplication(flexProjectParser
							.getAppModelClass());

			generatorFlexSimpleView.generate();
			
			generatorASConfig.setEntryPoint(flexProjectParser.getEntryPointGenerator());
			generatorASConfig.setProyectName(this.proyectName);
			
			
			generatorEntryPoint.setEntryPoint(flexProjectParser.getEntryPointGenerator());
			generatorEntryPoint.generate();
			
			
			generatorEntryPoint.setProyectName(this.proyectName);
			generatorEntryPoint.setEntryPoint(flexProjectParser.getEntryPointGenerator());
			
			
			

			
			generatorFlexServiceConfig.generate();
			generatorASConfig.generate();
			
			metaasProject.performAutoImport();
			metaasProject.writeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ActionScriptFlexCairngormCommandCodeGenerator getGenASCommands() {
		return genASCommands;
	}

	public void setGenASCommands(
			ActionScriptFlexCairngormCommandCodeGenerator genASCommands) {
		this.genASCommands = genASCommands;
	}

	public ActionScriptFlexCairngormEventCodeGenerator getGenASEvents() {
		return genASEvents;
	}

	public void setGenASEvents(
			ActionScriptFlexCairngormEventCodeGenerator genASEvents) {
		this.genASEvents = genASEvents;
	}

	public ActionScriptFlexCairngormControllerCodeGenerator getGenASController() {
		return genASController;
	}

	public void setGenASController(
			ActionScriptFlexCairngormControllerCodeGenerator genASController) {
		this.genASController = genASController;
	}

	public ActionScriptFlexModelApplicationCodeGenerator getGenASFlexModelApp() {
		return genASFlexModelApp;
	}

	public void setGenASFlexModelApp(
			ActionScriptFlexModelApplicationCodeGenerator genASFlexModelApp) {
		this.genASFlexModelApp = genASFlexModelApp;
	}

	public FlexCairngormCommandModelGenerator getCairngormCommandModelGenerator() {
		return cairngormCommandModelGenerator;
	}

	public void setCairngormCommandModelGenerator(
			FlexCairngormCommandModelGenerator cairngormCommandModelGenerator) {
		this.cairngormCommandModelGenerator = cairngormCommandModelGenerator;
	}

	public FlexCairngormServiceLocatorGenerator getGeneratorFlexCairngormServiceLocator() {
		return generatorFlexCairngormServiceLocator;
	}

	public void setGeneratorFlexCairngormServiceLocator(
			FlexCairngormServiceLocatorGenerator generatorFlexCairngormServiceLocator) {
		this.generatorFlexCairngormServiceLocator = generatorFlexCairngormServiceLocator;
	}

	public FlexSimpleViewGenerator getGeneratorFlexSimpleView() {
		return generatorFlexSimpleView;
	}

	public void setGeneratorFlexSimpleView(
			FlexSimpleViewGenerator generatorFlexSimpleView) {
		this.generatorFlexSimpleView = generatorFlexSimpleView;
	}

	public FlexEntryPointCodeGenerator getGeneratorEntryPoint() {
		return generatorEntryPoint;
	}



	public void setGeneratorEntryPoint(
			FlexEntryPointCodeGenerator generatorEntryPoint) {
		this.generatorEntryPoint = generatorEntryPoint;
	}
	
}
