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

package org.code4flex.cgflexintegration.views;

import org.code4flex.cgflexintegration.Activator;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.model.ProjectType;
import org.code4flex.cgflexintegration.widgets.utils.ImageLoader;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import com.swtdesigner.SWTResourceManager;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */
 

public class CGFlexIntegrationNewWizardProjectTypeSelection extends WizardPage {

	@SuppressWarnings("unused")
	private ISelection selection;
	
	
	private Button radioFlexCairngormPhp;
	private Button radioFlexCairngormNet;
	private Button radioFlexCairngormSeam;
	private Button radioFlexCairngormPhyton;
	private Button radioFlexFlexMateNet;
	private Button radioFlexFlexMatePhp;
	private Button radioFlexFlexMatePhyton;
	
	public CGFlexIntegrationNewWizardProjectTypeSelection(ISelection selection) {
		super("wizardPage");
		setTitle("KnowledgeIt - CG Flex Integration");
		setDescription("This wizard creates a new Code Generation Flex Integration");
		this.selection = selection;
		

	}

	

	


	public void createControl(Composite arg0) {
		Composite container = new Composite(arg0, SWT.NULL);

		container.setLayout(null);
		setControl(container);

		final Group proyectTypeGroup = new Group(container, SWT.CENTER);
		proyectTypeGroup.setText("Proyect Type");
		proyectTypeGroup.setBounds(30, 115, 676, 259);

		radioFlexCairngormPhp = new Button(proyectTypeGroup, SWT.RADIO);
		radioFlexCairngormPhp.setSelection(true);
		
		
		radioFlexCairngormPhp.setText("Flex + Cairgorn + PHP (AMFPHP) + MySql");
		radioFlexCairngormPhp.setBounds(60, 30, 251, 16);
		
		final Label lblDescription = new Label(container, SWT.NONE);
		lblDescription.setBounds(30, 380, 676, 84);

		
		showDescription(radioFlexCairngormPhp, lblDescription,"This wizard generates all the classes that you need.\nIt starts with a project configuration and ends with a DB selection.\nThen generates the code.");

		final Composite areaFlexNet = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexNet.setBounds(60, 65, 234, 38);

		radioFlexCairngormNet = new Button(areaFlexNet, SWT.RADIO);
		radioFlexCairngormNet.setBounds(0, 10,167, 16);
		radioFlexCairngormNet.setEnabled(false);
		radioFlexCairngormNet.setText("Flex + Cairgorm + .Net");
		showDescription(areaFlexNet, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");

		final Composite areaFlexJava = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexJava.setBounds(60, 115, 234, 38);

		radioFlexCairngormSeam = new Button(areaFlexJava, SWT.RADIO);
		radioFlexCairngormSeam.setBounds(0, 10,224, 16);
		
		radioFlexCairngormSeam.setEnabled(false);
		radioFlexCairngormSeam.setText("Flex + Cairngorm + Java + Jboss seam");
		showDescription(areaFlexJava, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");

		final Composite areaFlexCairngormPhyton = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexCairngormPhyton.setBounds(60, 172, 221, 38);

		radioFlexCairngormPhyton = new Button(areaFlexCairngormPhyton, SWT.RADIO);
		radioFlexCairngormPhyton.setEnabled(false);
		radioFlexCairngormPhyton.setText("Flex + Cairngorm + Phyton");
		radioFlexCairngormPhyton.setBounds(0, 10, 154, 16);

		final Composite areaFlexJavaMate = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexJavaMate.setBounds(335, 115, 251, 40);

		final Button radioFlexFlexMateJBoss = new Button(areaFlexJavaMate, SWT.RADIO);
		radioFlexFlexMateJBoss.setEnabled(false);
		radioFlexFlexMateJBoss.setText("Flex + Flex Mate + Java + Jboss seam");
		radioFlexFlexMateJBoss.setBounds(0, 10, 211, 16);

		final Composite areaFlexMateNEt = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexMateNEt.setBounds(335, 65,251, 38);

		final Composite composite_2_1_1 = new Composite(areaFlexMateNEt, SWT.NONE);
		composite_2_1_1.setBounds(0, -44, 221, 30);

		radioFlexFlexMateNet = new Button(areaFlexMateNEt, SWT.RADIO);
		radioFlexFlexMateNet.setEnabled(false);
		radioFlexFlexMateNet.setText("Flex + Flex Mate + .Net");
		radioFlexFlexMateNet.setBounds(0, 10, 197, 16);

		final Composite areaFlexPhytonMate = new Composite(proyectTypeGroup, SWT.NONE);
		areaFlexPhytonMate.setBounds(335, 175,251, 35);

		radioFlexFlexMatePhyton = new Button(areaFlexPhytonMate, SWT.RADIO);
		radioFlexFlexMatePhyton.setEnabled(false);
		radioFlexFlexMatePhyton.setText("Flex + Flex Mate +  Phyton");
		radioFlexFlexMatePhyton.setBounds(0, 10, 164, 16);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setBackgroundImage(ImageLoader.getPluginImage(Activator.getDefault(), "configuration/images/knowledgelogo.jpg"));
		composite.setBounds(525, 475, 181, 31);
		composite.addListener(SWT.MouseDown, new Listener(){
			
			
			public void handleEvent(Event arg0) {
				
				ShowKnowledgeIt showKnowledge = new ShowKnowledgeIt(Display.getCurrent().getActiveShell(),0,true,false,false,true,false,"Take a look at Knowledgeit","Enjoy your visit");
				BusyIndicator.showWhile(Display.getCurrent(), showKnowledge);
				
			}
		});

		final Label code4flexThisIsLabel = new Label(container, SWT.SHADOW_NONE);
		code4flexThisIsLabel.setFont(SWTResourceManager.getFont("", 11, SWT.NONE));
		code4flexThisIsLabel.setText("Code4Flex: this is a plugin for code generation.\nYou have to select a project put the mouse cursor over an option to show a description.\nEnjoy code4flex");
		code4flexThisIsLabel.setBounds(30, 30, 671, 56);
		
		showDescription(areaFlexJavaMate, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");
		showDescription(areaFlexPhytonMate, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");
		showDescription(areaFlexMateNEt, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");
		showDescription(areaFlexCairngormPhyton, lblDescription,"This wizard is not available yet.\nKnowledgeit team is working for it.");

		radioFlexFlexMatePhp = new Button(proyectTypeGroup, SWT.RADIO);
		radioFlexFlexMatePhp.setBounds(335, 30,241, 16);
		radioFlexFlexMatePhp.setText("Flex + Flex Mate + Php (AMFPHP) + Mysql");
		showDescription(radioFlexFlexMatePhp, lblDescription,"This wizard generates all the classes that you need.\nIt starts with a project configuration and ends with a DB selection.\nThen generate a Flex Project, and a Php Project.\nThe Flex Project implements the Flex Mate framework");

		
		
		
	}



	private void showDescription(final Control flexPhpButton,
			final Label lblDescription,final String description) {
		flexPhpButton.addListener(SWT.MouseEnter, new Listener(){
			
			public void handleEvent(Event arg0) {
				lblDescription.setText(description);
				
			}
			
		});
		
		
		flexPhpButton.addListener(SWT.MouseExit, new Listener(){
			
			public void handleEvent(Event arg0) {
				lblDescription.setText("");
				
			}
			
		});
	}
	public void chargeModelWithRadios(){
		
		PluginModel model = PluginModel.getInstance();
		if(radioFlexCairngormPhp.getSelection())
			model.getProjectType().setProjectType(ProjectType.PHP_CAIRNGORM_PROJECT);
		if(radioFlexFlexMatePhp.getSelection())
			model.getProjectType().setProjectType(ProjectType.PHP_FLEX_MATE_PROJECT);
	}
}
