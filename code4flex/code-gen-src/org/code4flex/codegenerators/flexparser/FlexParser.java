package org.code4flex.codegenerators.flexparser;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.generators.ApplicationStateGenerator;
import org.code4flex.generators.EntryPointCairngormGenerator;
import org.code4flex.generators.EntryPointGenerator;
import org.code4flex.generators.model.FlexSimpleViewModel;
import org.code4flex.generators.model.FlexStateClassDeclaration;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;


public abstract class FlexParser {

	protected EntryPointGenerator entryPointGenerator = null;

	protected List<FlexSimpleViewModel> simpleViewList = new ArrayList<FlexSimpleViewModel>();

	protected ApplicationStateGenerator statesGenerator = null;

	public List<FlexSimpleViewModel> getSimpleViewList() {
		return simpleViewList;
	}

	public void setSimpleViewList(List<FlexSimpleViewModel> simpleViewList) {
		this.simpleViewList = simpleViewList;
	}

	public EntryPointGenerator getEntryPointGenerator() {
		return entryPointGenerator;
	}

	public void setEntryPointGenerator(EntryPointGenerator entryPointGenerator) {
		this.entryPointGenerator = entryPointGenerator;
	}

	public ApplicationStateGenerator getStatesGenerator() {
		return statesGenerator;
	}

	public void setStatesGenerator(ApplicationStateGenerator statesGenerator) {
		this.statesGenerator = statesGenerator;
	}

	public abstract void parse(List<AbstractServiceClass> serviceClasses,
			PluginModel model,
			List<AbstractClass> asModelClassList) ;

	protected EntryPointGenerator configureEntryPoint(
			List<FlexStateClassDeclaration> states,
			List<FlexSimpleViewModel> viewList,
			PluginModel model) {
		EntryPointCairngormGenerator entryPointGenerator = new EntryPointCairngormGenerator();
		entryPointGenerator.getConfiguration().setControllerNamespace(
				model.getFlexProjectInformation().getControllerPackage());
		entryPointGenerator.getConfiguration().setServiceNamespace(
				model.getFlexProjectInformation().getServicePackage());
		entryPointGenerator.getConfiguration().setRdsServiceName(
				model.getFlexProjectInformation().getServiceLocatorName());
		// TODO: controller agregar al Flex Wizard
		entryPointGenerator.getConfiguration().setRouterControllerName(
				"EventsCommandsController");
		entryPointGenerator.setMainPointFileName(model
				.getFlexProjectInformation().getMainClassName()
				+ ".mxml");
		entryPointGenerator.setStateClasses(states);
		entryPointGenerator.setSimpleViews(viewList);

		return entryPointGenerator;
	}

}
