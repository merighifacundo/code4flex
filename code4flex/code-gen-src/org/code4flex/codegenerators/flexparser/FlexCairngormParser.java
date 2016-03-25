package org.code4flex.codegenerators.flexparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.codegenerators.CodeGenerationNameSpaceConstants;
import org.code4flex.generators.ApplicationStateGenerator;
import org.code4flex.generators.CairngormServiceLocatorModelGenerator;
import org.code4flex.generators.EntryPointGenerator;
import org.code4flex.generators.model.FlexSimpleViewModel;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormCommandClass;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormControllerClass;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormControllerEventRelation;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormEventClass;
import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;
import org.code4flex.generators.model.actionscript.classes.ActionScriptModelApplicationClass;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;


public class FlexCairngormParser extends FlexParser {

	
	List<CairngormServiceLocatorModelGenerator> list = new ArrayList<CairngormServiceLocatorModelGenerator>();
	List<FlexCairngormCommandClass> commandList = new ArrayList<FlexCairngormCommandClass>();
	List<FlexCairngormEventClass> eventList = new ArrayList<FlexCairngormEventClass>();

	FlexCairngormControllerClass controller = null;
	ActionScriptModelApplicationClass appModelClass = null;
	
	

	@Override
	public void parse(List<AbstractServiceClass> serviceClasses,
			PluginModel model,
			List<AbstractClass> asModelClassList) {
		
		appModelClass = new ActionScriptModelApplicationClass();
		appModelClass.setModel(asModelClassList);
		appModelClass.setClassName(model.getFlexProjectInformation()
				.getApplicationModelClassName());
		appModelClass.setNamespace(model.getPhpProjectInformation()
				.getModelPackage());

		controller = new FlexCairngormControllerClass();
		controller.setNamespace(model.getFlexProjectInformation()
				.getControllerPackage());
		// Todo agregar al wizard de Flex
		controller.setClassName("EventsCommandsController");
		
		
		
		FlexCairngormControllerEventRelation relation = null;
		FlexCairngormCommandClass command = null;
		FlexCairngormEventClass event = null;
		FlexSimpleViewModel simpleView = null;
		CairngormServiceLocatorModelGenerator locator = null;
		Iterator<AbstractServiceClass> itDaoClasses = serviceClasses.iterator();
		while (itDaoClasses.hasNext()) {
			AbstractServiceClass serviceClass = (AbstractServiceClass) itDaoClasses
					.next();
			locator = new CairngormServiceLocatorModelGenerator(serviceClass);

			command = new FlexCairngormCommandClass();
			command.setNamespace(model.getFlexProjectInformation()
					.getCommandPackage());
			command.setClassName(serviceClass.getServiceCommandName());

			command.setActionScriptModelApplication(appModelClass);

			event = new FlexCairngormEventClass();
			event.setNamespace(model.getFlexProjectInformation()
					.getEventPackage());
			event.setClassName(serviceClass.getServiceEventName());
			ActionScriptClass ascriptFinded = FlexParserUtils
					.findActionScriptModelClassForEvent(serviceClass
							.getAsociatedModelClass(), asModelClassList);
			event.setModelClass(ascriptFinded);
			event.init();

			command.setService(locator);
			event.setService(locator);

			list.add(locator);

			eventList.add(event);

			relation = new FlexCairngormControllerEventRelation();
			relation.setCommand(command);
			relation.setEvent(event);
			event.setRelation(relation);
			command.setRelation(relation);
			controller.getRelations().add(relation);

			simpleView = new FlexSimpleViewModel();
			simpleView.setEvent(event);
			simpleView
					.setNamespace(CodeGenerationNameSpaceConstants.simpleViewNamespace);
			simpleView.setClassName(serviceClass.getServiceViewName());
			simpleViewList.add(simpleView);
			commandList.add(command);

		}
		
		statesGenerator = new ApplicationStateGenerator(this
				.getSimpleViewList());
		entryPointGenerator = new EntryPointGenerator();
		entryPointGenerator = configureEntryPoint(statesGenerator.getStates(),
				this.getSimpleViewList(), model);
		controller.init();
		
	}
	public List<CairngormServiceLocatorModelGenerator> getList() {
		return list;
	}
	public void setList(List<CairngormServiceLocatorModelGenerator> list) {
		this.list = list;
	}
	public List<FlexCairngormCommandClass> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<FlexCairngormCommandClass> commandList) {
		this.commandList = commandList;
	}
	public List<FlexCairngormEventClass> getEventList() {
		return eventList;
	}
	public void setEventList(List<FlexCairngormEventClass> eventList) {
		this.eventList = eventList;
	}

	
	
	public FlexCairngormControllerClass getController() {
		return controller;
	}
	public void setController(FlexCairngormControllerClass controller) {
		this.controller = controller;
	}
	
	
	
	
	public ActionScriptModelApplicationClass getAppModelClass() {
		return appModelClass;
	}
	public void setAppModelClass(ActionScriptModelApplicationClass appModelClass) {
		this.appModelClass = appModelClass;
	}
	
	

	
	
}
