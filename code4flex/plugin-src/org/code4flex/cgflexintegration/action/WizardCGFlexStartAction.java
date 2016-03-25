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

package org.code4flex.cgflexintegration.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;

import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.generation.CGProjectCodeGenerator;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.model.ProjectType;
import org.code4flex.cgflexintegration.views.CGFlexIntegrationNewWizardDBLoader;
import org.code4flex.cgflexintegration.views.CGFlexIntegrationNewWizardDBTablesSelection;
import org.code4flex.cgflexintegration.views.CGFlexIntegrationNewWizardProjectTypeSelection;
import org.code4flex.cgflexintegration.views.FlexCGProjectCreationPage;
import org.code4flex.cgflexintegration.views.PhpCGProjectCreationPage;
import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.osgi.framework.Bundle;


/**
 * This is the "wizard controller" it add the pages and finish the work In this
 * place the magic begins: we have two importan methods: generatePhpProject
 * generateFlexProject
 * 
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

@SuppressWarnings("restriction")
public class WizardCGFlexStartAction extends Wizard implements INewWizard {
	private CGFlexIntegrationNewWizardProjectTypeSelection newFlexIntegrationWizardProjectTypeSection;
	private FlexCGProjectCreationPage newFlexProjectCreationPage;
	private PhpCGProjectCreationPage newPhpProjectCreationPage;
	private CGFlexIntegrationNewWizardDBLoader dbLoaderPage;
	private CGFlexIntegrationNewWizardDBTablesSelection dbSelector;
	private ISelection selection;
	private String proyectName;

	public WizardCGFlexStartAction() {
		super();

		try {
			Bundle bundle = Activator.getDefault().getBundle(
					"Code4FlexPlugin");
			Path path = new Path("/configuration/props/messages_en.xml");
			URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
			try {
				url = FileLocator.toFileURL(url);
			} catch (IOException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}

			FileInputStream file = new FileInputStream(url.getPath());
			Properties prop = new Properties();
			prop.loadFromXML(file);
			PluginModel.getInstance().setMessages(prop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean performFinish() {

		PluginModel.getInstance().setSelectedRows(
				dbSelector.getDbTable().getCheckedRows());
		this.newFlexProjectCreationPage.chargeModelWithBoxes();
		this.newPhpProjectCreationPage.chargeModelWithBoxes();
		this.newFlexIntegrationWizardProjectTypeSection.chargeModelWithRadios();
		generatePhpProject();
		generateFlexProject();
		PluginModel.getInstance().removeObserver(dbSelector.getDbTable());
		PluginModel.getInstance().removeObserver(dbSelector.getCboCatalog());
		PluginModel.getInstance().removeObserver(dbSelector.getCboSchema());
		PluginModel.getInstance().clearInstance();
		return true;
	}

	private boolean generateFlexProject() {
		try {

			IProject proy = newFlexProjectCreationPage.getProjectHandle();
			this.proyectName = proy.getName();
			final IProjectDescription des = ResourcesPlugin.getWorkspace()
					.newProjectDescription(proy.getName());

			if (!proy.exists())
				proy.create(des, null);
			if (!proy.isOpen()) {
				proy.open(null);

			}
			String dest = proy.getLocation().toOSString();
			Bundle bundle = Activator.getDefault().getBundle(
					"Code4FlexPlugin");
			Path path = new Path("/configuration/CodeGenerator.xml");
			URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
			URL fileUrl = null;
			try {
				fileUrl = FileLocator.toFileURL(url);
			} catch (IOException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}
			File file = new File(fileUrl.getPath());

			// S bundle.get + File.separator + "";
			// .getEntry("/configuration/CodeGenerator.xml").get;

			// String fileName =
			// ResourcesPlugin.getPlugin().getBundle().getResource
			// ("CodeGenerator.xml").getFile();

			// Bundle bundle =
			// Activator.getDefault().getBundle("CGFlexIntegration");
			Path path2 = new Path("/configuration");
			URL urlconf = FileLocator
					.find(bundle, path2, Collections.EMPTY_MAP);
			URL fileUrlconf = null;
			try {
				fileUrlconf = FileLocator.toFileURL(urlconf);
			} catch (IOException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}

			int ptype = PluginModel.getInstance().getProjectType()
					.getProjectType();
			String type = null;
			switch (ptype) {
			case ProjectType.JSEAM_CAIRNGORM_PROJECT:
			case ProjectType.PHP_CAIRNGORM_PROJECT:
				type = "FlexCairngorm";
				break;
			case ProjectType.JSEAM_FLEX_MATE_PROJECT:
			case ProjectType.PHP_FLEX_MATE_PROJECT:
				type = "FlexFMate";
				break;
			}

			generateCode(type, file, dest, fileUrlconf.getPath());
			// generateCode("Php",file,dest,fileUrlconf.getPath());
			proy.refreshLocal(IResource.DEPTH_INFINITE, null);

		} catch (CoreException e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();
		} catch (Exception e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();

		}
		return true;
	}

	private boolean generatePhpProject() {
		try {

			IProject proy = newPhpProjectCreationPage.getProjectHandle();
			this.proyectName = proy.getName();
			final IProjectDescription des = ResourcesPlugin.getWorkspace()
					.newProjectDescription(proy.getName());

			if (!proy.exists())
				proy.create(des, null);
			if (!proy.isOpen()) {
				proy.open(null);

			}
			String dest = proy.getLocation().toOSString();
			Bundle bundle = Activator.getDefault().getBundle(
					"Code4FlexPlugin");
			Path path = new Path("/configuration/CodeGenerator.xml");
			URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
			URL fileUrl = null;
			try {
				fileUrl = FileLocator.toFileURL(url);
			} catch (IOException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}
			File file = new File(fileUrl.getPath());

			// S bundle.get + File.separator + "";
			// .getEntry("/configuration/CodeGenerator.xml").get;

			// String fileName =
			// ResourcesPlugin.getPlugin().getBundle().getResource
			// ("CodeGenerator.xml").getFile();

			// Bundle bundle =
			// Activator.getDefault().getBundle("CGFlexIntegration");
			Path path2 = new Path("/configuration");
			URL urlconf = FileLocator
					.find(bundle, path2, Collections.EMPTY_MAP);
			URL fileUrlconf = null;
			try {
				fileUrlconf = FileLocator.toFileURL(urlconf);
			} catch (IOException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}

			// generateCode("Flex",file,dest,fileUrlconf.getPath());
			generateCode("Php", file, dest, fileUrlconf.getPath());
			proy.refreshLocal(IResource.DEPTH_INFINITE, null);

		} catch (CoreException e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();
		} catch (Exception e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();

		}
		return true;
	}

	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		this.selection = arg1;
		this.setWindowTitle("Code4Flex - Knowledgeit.com.ar");

	}

	public void generateCode(String type, File ios, String proyDest, String path) {

		try {
			CGProjectCodeGenerator sgt = new CGProjectCodeGenerator();

			sgt.setFile(ios);
			sgt.setProyDest(proyDest);
			sgt.setTemplatePath(path);
			sgt.setTheModel(PluginModel.getInstance());
			sgt.setProyectName(this.proyectName);
			sgt.generateCode(type);

		} catch (Exception ex) {
			ExceptionReport.reportException(ex,true);
			System.out.println(ex.getMessage());
			ex.printStackTrace();

		}

	}

	public ImageDescriptor getImageDescriptor(String filepath) {

		Bundle bundle = Activator.getDefault().getBundle("Code4FlexPlugin");
		Path path = new Path(filepath);
		URL url = FileLocator.find(bundle, path, Collections.EMPTY_MAP);
		ImageDescriptor ides = ImageDescriptor.createFromURL(url);
		return ides;
	}

	public void addPages() {

		// Pagina Principal del Plugin
		newFlexIntegrationWizardProjectTypeSection = new CGFlexIntegrationNewWizardProjectTypeSelection(
				selection);
		newFlexIntegrationWizardProjectTypeSection
				.setTitle("Project Type Selection");
		newFlexIntegrationWizardProjectTypeSection
				.setImageDescriptor(getImageDescriptor("/configuration/images/wizardAlone.jpg"));
		newFlexProjectCreationPage = new FlexCGProjectCreationPage("newProject");
		newFlexProjectCreationPage
				.setImageDescriptor(getImageDescriptor("/configuration/images/wizardNewProjectFlex.jpg"));
		newFlexProjectCreationPage.setTitle("Code Generation Flex Integration");
		newFlexProjectCreationPage.setDescription("New Flex Proyect");
		newPhpProjectCreationPage = new PhpCGProjectCreationPage(
				"newPhpProject");
		newPhpProjectCreationPage
				.setImageDescriptor(getImageDescriptor("/configuration/images/wizardPhp.jpg"));
		newPhpProjectCreationPage.setTitle("Code Generation Flex Integration");
		newPhpProjectCreationPage.setDescription("New Php Proyect");

		dbLoaderPage = new CGFlexIntegrationNewWizardDBLoader(selection);
		dbLoaderPage
				.setImageDescriptor(getImageDescriptor("/configuration/images/wizardAlone.jpg"));
		dbSelector = new CGFlexIntegrationNewWizardDBTablesSelection(selection);
		dbSelector
				.setImageDescriptor(getImageDescriptor("/configuration/images/wizardAlone.jpg"));
		PluginModel.getInstance().addObserver(dbSelector.getDbTable());
		PluginModel.getInstance().addObserver(dbSelector.getCboCatalog());
		PluginModel.getInstance().addObserver(dbSelector.getCboSchema());
		addPage(newFlexIntegrationWizardProjectTypeSection);
		addPage(dbLoaderPage);
		addPage(newFlexProjectCreationPage);
		addPage(newPhpProjectCreationPage);
		addPage(dbSelector);

	}

	@Override
	public boolean performCancel() {
		PluginModel.getInstance().removeObserver(dbSelector.getDbTable());
		PluginModel.getInstance().removeObserver(dbSelector.getCboCatalog());
		PluginModel.getInstance().removeObserver(dbSelector.getCboSchema());
		PluginModel.getInstance().clearInstance();
		return super.performCancel();
	}

}
