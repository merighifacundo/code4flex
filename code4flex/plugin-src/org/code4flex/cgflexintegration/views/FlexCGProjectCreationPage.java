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

import java.net.URI;
import java.util.Map.Entry;

import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.widgets.validator.group.TextBoxesValidatorGroup;
import org.code4flex.cgflexintegration.widgets.validator.utils.ValidatorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WorkingSetGroup;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */


@SuppressWarnings("restriction")
public class FlexCGProjectCreationPage  extends WizardPage {

 // initial value stores
 private String initialProjectFieldValue;

 // widgets
 Text projectNameField;
 
 //knowledge textboxvalidatorgrup
 TextBoxesValidatorGroup validator = new TextBoxesValidatorGroup();
 

 //TextBoxes Declaration
 private Text txtEventPackage;
 private Text txtCommandPackage;
 private Text txtControllerPackage;
 private Text txtServiceLocatorPackage;
 
 private Text txtEntryPointName;
 private Text txtServiceLocatorName;
 private Text txtApplicationModelName;
 
 //Checkbox
 private Button chkCairngorm;
 private Button chkTestViews;
 private Button chkStateController;
 
 private Listener nameModifyListener = new Listener() {
     public void handleEvent(Event e) {
     	setLocationForSelection();
         boolean valid = validatePage();
         setPageComplete(valid);
             
     }
 };

	private ProjectContentsLocationArea locationArea;

	private WorkingSetGroup workingSetGroup;

 // constants
 private static final int SIZING_TEXT_FIELD_WIDTH = 250;

 /**
  * Creates a new project creation wizard page.
  *
  * @param pageName the name of this page
  */
 public FlexCGProjectCreationPage(String pageName) {
 	super(pageName);
	    setPageComplete(false);
 }

 /**
	 * Creates a new project creation wizard page.
	 * 
	 * @param pageName
	 * @param selection
	 * @param workingSetTypes
	 * 
	 * @deprecated default placement of the working set group has been removed.
	 *             If you wish to use the working set block please call
	 *             {@link #createWorkingSetGroup(Composite, IStructuredSelection, String[])}
	 *             in your overriden {@link #createControl(Composite)}
	 *             implementation. This method will be removed before 3.4 ships.
	 * @since 3.4
	 */
	public FlexCGProjectCreationPage(String pageName,
			IStructuredSelection selection, String[] workingSetTypes) {
		this(pageName);
	}

	/** (non-Javadoc)
  * Method declared on IDialogPage.
  */
 public void createControl(Composite parent) {
     Composite composite = new Composite(parent, SWT.NULL);
 

     initializeDialogUnits(parent);

     PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
             IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);
     
     composite.setLayout(new GridLayout());
     composite.setLayoutData(new GridData(GridData.FILL_BOTH));

     createProjectNameGroup(composite);
     locationArea = new ProjectContentsLocationArea(getErrorReporter(), composite);
     if(initialProjectFieldValue != null) {
			locationArea.updateProjectName(initialProjectFieldValue);
		}
     
		// Scale the button based on the rest of the dialog
		setButtonLayoutData(locationArea.getBrowseButton());
		
     setPageComplete(validatePage());
     // Show description on opening
     setErrorMessage(null);
     setMessage(null);
     setControl(composite);
     Dialog.applyDialogFont(composite);

     final SashForm separatorMain = new SashForm(composite, SWT.NONE);
     separatorMain.setLayoutData(new GridData(SWT.DEFAULT, 12));

     final Group grpFlexProjectConfiguration = new Group(composite, SWT.NONE);
     grpFlexProjectConfiguration.setText("Flex Project Configuration");
     final GridData gd_grpFlexProjectConfiguration = new GridData(742, 310);
     grpFlexProjectConfiguration.setLayoutData(gd_grpFlexProjectConfiguration);
     final GridLayout gridLayout = new GridLayout();
     gridLayout.numColumns = 3;
     grpFlexProjectConfiguration.setLayout(gridLayout);

     final Group grpProjectScope = new Group(grpFlexProjectConfiguration, SWT.NONE);
     grpProjectScope.setText("Project Scope");
     final GridData gd_grpProjectScope = new GridData(331, 172);
     grpProjectScope.setLayoutData(gd_grpProjectScope);
     grpProjectScope.setLayout(null);

     final Button button = new Button(grpProjectScope, SWT.CHECK);
     button.setText("Check Button");

     chkTestViews = new Button(grpProjectScope, SWT.CHECK);
     chkTestViews.setToolTipText("Code4Flex will generate views to test the interaction with amf.\nYou will have one view for each Dao in the application.\nTo test the functionallity");
     chkTestViews.setSelection(true);
     chkTestViews.setText("Test Views");
     chkTestViews.setBounds(25, 25, 103, 16);

     chkStateController = new Button(grpProjectScope, SWT.CHECK);
     chkStateController.setToolTipText("This is a component to manage the States changes in the application.\nThis component will placed in the Controller Package.");
     chkStateController.setSelection(true);
     chkStateController.setText("State Controller");
     chkStateController.setBounds(25, 80, 103, 16);

     chkCairngorm = new Button(grpProjectScope, SWT.CHECK);
     chkCairngorm.setSelection(true);
     chkCairngorm.setText("Cairngorm Events + Commands + Controller");
     chkCairngorm.setBounds(25, 140, 244, 16);

     final SashForm sashForm = new SashForm(grpFlexProjectConfiguration, SWT.NONE);
     sashForm.setLayoutData(new GridData(46, SWT.DEFAULT));

     final Group grpFlexPackageConfiguration = new Group(grpFlexProjectConfiguration, SWT.NONE);
     grpFlexPackageConfiguration.setText("Flex Package Configuration");
     final GridData gd_grpFlexPackageConfiguration = new GridData(331, 172);
     grpFlexPackageConfiguration.setLayoutData(gd_grpFlexPackageConfiguration);
     grpFlexPackageConfiguration.setLayout(null);

     final Label eventPackageLabel = new Label(grpFlexPackageConfiguration, SWT.NONE);
     eventPackageLabel.setText("Event Package");
     eventPackageLabel.setBounds(10, 30, 81, 13);

     txtEventPackage = new Text(grpFlexPackageConfiguration, SWT.BORDER);
     txtEventPackage.setText("org.code4flex.events");
     txtEventPackage.setBounds(150, 20, 159, 25);

     final Label commandPackageLabel = new Label(grpFlexPackageConfiguration, SWT.NONE);
     commandPackageLabel.setText("Command Package");
     commandPackageLabel.setBounds(10, 65, 106, 13);

     txtCommandPackage = new Text(grpFlexPackageConfiguration, SWT.BORDER);
     txtCommandPackage.setText("org.code4flex.commands");
     txtCommandPackage.setBounds(150, 60, 159, 25);

     final Label controllerPackageLabel = new Label(grpFlexPackageConfiguration, SWT.NONE);
     controllerPackageLabel.setText("Controller Package");
     controllerPackageLabel.setBounds(10, 105, 106, 13);

     txtControllerPackage = new Text(grpFlexPackageConfiguration, SWT.BORDER);
     txtControllerPackage.setText("org.code4flex.controllers");
     txtControllerPackage.setBounds(150, 100, 159, 25);

     final Label serviceLocatorPackageLabel = new Label(grpFlexPackageConfiguration, SWT.NONE);
     serviceLocatorPackageLabel.setText("Service Locator Package");
     serviceLocatorPackageLabel.setBounds(10, 140, 119, 25);

     txtServiceLocatorPackage = new Text(grpFlexPackageConfiguration, SWT.BORDER);
     txtServiceLocatorPackage.setText("org.code4flex.services");
     txtServiceLocatorPackage.setBounds(150, 137, 159, 25);

     final Group grpEntryPoint = new Group(grpFlexProjectConfiguration, SWT.NONE);
     grpEntryPoint.setText("Entry Point (Main Class) Config");
     final GridData gd_grpEntryPoint = new GridData(SWT.LEFT, SWT.TOP, false, false);
     gd_grpEntryPoint.heightHint = 85;
     gd_grpEntryPoint.widthHint = 331;
     grpEntryPoint.setLayoutData(gd_grpEntryPoint);
     grpEntryPoint.setLayout(null);

     final Label mainClassNameLabel = new Label(grpEntryPoint, SWT.NONE);
     mainClassNameLabel.setText("Main Class Name");
     mainClassNameLabel.setBounds(30, 30, 92, 21);

     txtEntryPointName = new Text(grpEntryPoint, SWT.BORDER);
     txtEntryPointName.setBounds(130, 25, 170, 25);
     new Label(grpFlexProjectConfiguration, SWT.NONE);

     final Group grpServiceLocatorAppModel = new Group(grpFlexProjectConfiguration, SWT.NONE);
     grpServiceLocatorAppModel.setText("Service and Application Model configuration");
     final GridData gd_grpServiceLocatorAppModel = new GridData(331, 85);
     grpServiceLocatorAppModel.setLayoutData(gd_grpServiceLocatorAppModel);
     grpServiceLocatorAppModel.setLayout(null);

     final Label serviceLocatorNameLabel = new Label(grpServiceLocatorAppModel, SWT.NONE);
     serviceLocatorNameLabel.setText("Service Locator Name");
     serviceLocatorNameLabel.setBounds(20, 30, 118, 19);

     txtServiceLocatorName = new Text(grpServiceLocatorAppModel, SWT.BORDER);
     txtServiceLocatorName.setBounds(155, 25, 154, 25);

     final Label applicationModelClassLabel = new Label(grpServiceLocatorAppModel, SWT.NONE);
     applicationModelClassLabel.setText("Application Model Name");
     applicationModelClassLabel.setBounds(20, 67, 118, 24);

     txtApplicationModelName = new Text(grpServiceLocatorAppModel, SWT.BORDER);
     txtApplicationModelName.setBounds(155, 64, 154, 25);
     
     
     //Listener for modification
     txtApplicationModelName.addListener(SWT.Modify, nameModifyListener);
     txtCommandPackage.addListener(SWT.Modify, nameModifyListener);
     txtControllerPackage.addListener(SWT.Modify, nameModifyListener);
     txtEntryPointName.addListener(SWT.Modify, nameModifyListener);
     txtEventPackage.addListener(SWT.Modify, nameModifyListener);
     txtServiceLocatorName.addListener(SWT.Modify, nameModifyListener);
     txtServiceLocatorPackage.addListener(SWT.Modify, nameModifyListener);
     
     //Adding validator;
     validator.addControl(txtApplicationModelName, ValidatorUtils.createValidator("",""));
     validator.addControl(txtCommandPackage, ValidatorUtils.createValidator("",""));
     validator.addControl(txtControllerPackage, ValidatorUtils.createValidator("",""));
     validator.addControl(txtEntryPointName, ValidatorUtils.createValidator("",""));
     validator.addControl(txtEventPackage, ValidatorUtils.createValidator("",""));
     validator.addControl(txtServiceLocatorName, ValidatorUtils.createValidator("",""));
     validator.addControl(txtServiceLocatorPackage, ValidatorUtils.createValidator("",""));
 }
 
 
/**
	 * Create a working set group for this page. This method can only be called
	 * once.
	 * 
	 * @param composite
	 *            the composite in which to create the group
	 * @param selection
	 *            the current workbench selection
	 * @param supportedWorkingSetTypes
	 *            an array of working set type IDs that will restrict what types
	 *            of working sets can be chosen in this group
	 * @return the created group. If this method has been called previously the
	 *         original group will be returned.
	 * @since 3.4
	 */
	public WorkingSetGroup createWorkingSetGroup(Composite composite,
			IStructuredSelection selection, String[] supportedWorkingSetTypes) {
		if (workingSetGroup != null)
			return workingSetGroup;
		workingSetGroup = new WorkingSetGroup(composite, selection,
				supportedWorkingSetTypes);
		return workingSetGroup;
	}
 
 /**
	 * Get an error reporter for the receiver.
	 * @return IErrorMessageReporter
	 */
	private IErrorMessageReporter getErrorReporter() {
		return new IErrorMessageReporter(){
			/* (non-Javadoc)
			 * @see org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter#reportError(java.lang.String)
			 */
			public void reportError(String errorMessage, boolean infoOnly) {
				if (infoOnly) {
					setMessage(errorMessage, IStatus.INFO);
					setErrorMessage(null);
				}
				else
					setErrorMessage(errorMessage);
				boolean valid = errorMessage == null;
				if(valid) {
					valid = validatePage();
				}
				
				setPageComplete(valid);
			}
		};
	}

 /**
  * Creates the project name specification controls.
  *
  * @param parent the parent composite
  */
 private final void createProjectNameGroup(Composite parent) {
     // project specification group
     Composite projectGroup = new Composite(parent, SWT.NONE);
     GridLayout layout = new GridLayout();
     layout.numColumns = 2;
     projectGroup.setLayout(layout);
     projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

     // new project label
     Label projectLabel = new Label(projectGroup, SWT.NONE);
     projectLabel.setText(IDEWorkbenchMessages.WizardNewProjectCreationPage_nameLabel);
     projectLabel.setFont(parent.getFont());

     // new project name entry field
     projectNameField = new Text(projectGroup, SWT.BORDER);
     GridData data = new GridData(GridData.FILL_HORIZONTAL);
     data.widthHint = SIZING_TEXT_FIELD_WIDTH;
     projectNameField.setLayoutData(data);
     projectNameField.setFont(parent.getFont());

     // Set the initial value first before listener
     // to avoid handling an event during the creation.
     if (initialProjectFieldValue != null) {
			projectNameField.setText(initialProjectFieldValue);
		}
     projectNameField.addListener(SWT.Modify, nameModifyListener);
 }


 /**
  * Returns the current project location path as entered by 
  * the user, or its anticipated initial value.
  * Note that if the default has been returned the path
  * in a project description used to create a project
  * should not be set.
  *
  * @return the project location path or its anticipated initial value.
  */
 public IPath getLocationPath() {
     return new Path(locationArea.getProjectLocation());
 }
 
 /**
 /**
  * Returns the current project location URI as entered by 
  * the user, or <code>null</code> if a valid project location
  * has not been entered.
  *
  * @return the project location URI, or <code>null</code>
  * @since 3.2
  */
 public URI getLocationURI() {
 	return locationArea.getProjectLocationURI();
 }

 /**
	 * Creates a project resource handle for the current project name field
	 * value. The project handle is created relative to the workspace root.
	 * <p>
	 * This method does not create the project resource; this is the
	 * responsibility of <code>IProject::create</code> invoked by the new
	 * project resource wizard.
	 * </p>
	 * 
	 * @return the new project resource handle
	 */
 public IProject getProjectHandle() {
     return ResourcesPlugin.getWorkspace().getRoot().getProject(
             getProjectName());
 }

 /**
  * Returns the current project name as entered by the user, or its anticipated
  * initial value.
  *
  * @return the project name, its anticipated initial value, or <code>null</code>
  *   if no project name is known
  */
 public String getProjectName() {
     if (projectNameField == null) {
			return initialProjectFieldValue;
		}

     return getProjectNameFieldValue();
 }

 /**
  * Returns the value of the project name field
  * with leading and trailing spaces removed.
  * 
  * @return the project name in the field
  */
 private String getProjectNameFieldValue() {
     if (projectNameField == null) {
			return ""; //$NON-NLS-1$
		}

     return projectNameField.getText().trim();
 }

 /**
  * Sets the initial project name that this page will use when
  * created. The name is ignored if the createControl(Composite)
  * method has already been called. Leading and trailing spaces
  * in the name are ignored.
  * Providing the name of an existing project will not necessarily 
  * cause the wizard to warn the user.  Callers of this method 
  * should first check if the project name passed already exists 
  * in the workspace.
  * 
  * @param name initial project name for this page
  * 
  * @see IWorkspace#validateName(String, int)
  * 
  */
 public void setInitialProjectName(String name) {
     if (name == null) {
			initialProjectFieldValue = null;
		} else {
         initialProjectFieldValue = name.trim();
         if(locationArea != null) {
				locationArea.updateProjectName(name.trim());
			}
     }
 }

 /**
  * Set the location to the default location if we are set to useDefaults.
  */
 void setLocationForSelection() {
 	locationArea.updateProjectName(getProjectNameFieldValue());
 }


 /**
  * Returns whether this page's controls currently all contain valid 
  * values.
  *
  * @return <code>true</code> if all controls are valid, and
  *   <code>false</code> if at least one is invalid
  */
 protected boolean validatePage() {
     IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();

     String projectFieldContents = getProjectNameFieldValue();
     if (projectFieldContents.equals("")) { //$NON-NLS-1$
         setErrorMessage(null);
         setMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectNameEmpty);
         return false;
     }

     IStatus nameStatus = workspace.validateName(projectFieldContents,
             IResource.PROJECT);
     if (!nameStatus.isOK()) {
         setErrorMessage(nameStatus.getMessage());
         return false;
     }

     IProject handle = getProjectHandle();
     if (handle.exists()) {
         setErrorMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectExistsMessage);
         return false;
     }
             
     IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				getProjectNameFieldValue());
		locationArea.setExistingProject(project);
		
		String validLocationMessage = locationArea.checkValidLocation();
		if (validLocationMessage != null) { // there is no destination location given
			setErrorMessage(validLocationMessage);
			return false;
		}
	
	 if(!validator.validateControls()){
		 String errorMessage = "";
		 int errorsNumber = validator.giveMeReasons().entrySet().size();
		 if(errorsNumber<3){
			 errorMessage = "Errors(" + errorsNumber +"): "; 
			 for (Entry<Control,String> entry : validator.giveMeReasons().entrySet()) {
				errorMessage += entry.getValue() + ", ";
			}
		 }else{
			 errorMessage = "Multiple errors(" + errorsNumber + ")";
		 }
		 setErrorMessage(errorMessage);
		 //validator.showStyleErrors();
		 return false;
	 }	
		
		
	
     setErrorMessage(null);
     setMessage(null);
     return true;
 }

 /*
  * see @DialogPage.setVisible(boolean)
  */
 public void setVisible(boolean visible) {
     super.setVisible(visible);
     if (visible) {
			projectNameField.setFocus();
		}
 }

 /**
  * Returns the useDefaults.
  * @return boolean
  */
 public boolean useDefaults() {
     return locationArea.isDefault();
 }

 /**
	 * Return the selected working sets, if any. If this page is not configured
	 * to interact with working sets this will be an empty array.
	 * 
	 * @return the selected working sets
	 * @since 3.4
	 */
	public IWorkingSet[] getSelectedWorkingSets() {
		return workingSetGroup == null ? new IWorkingSet[0] : workingSetGroup
				.getSelectedWorkingSets();
	}
	
	public void chargeModelWithBoxes(){
		PluginModel model = PluginModel.getInstance();
		model.getFlexProjectInformation().setCommandPackage(txtCommandPackage.getText());
		model.getFlexProjectInformation().setEventPackage(txtEventPackage.getText());
		model.getFlexProjectInformation().setControllerPackage(txtControllerPackage.getText());
		model.getFlexProjectInformation().setServicePackage(txtServiceLocatorPackage.getText());
		model.getFlexProjectInformation().setCairngormImplementation(chkCairngorm.getSelection());
		model.getFlexProjectInformation().setStateController(chkStateController.getSelection());
		model.getFlexProjectInformation().setTestViews(chkTestViews.getSelection());
		model.getFlexProjectInformation().setMainClassName(txtEntryPointName.getText());
		model.getFlexProjectInformation().setServiceLocatorName(txtServiceLocatorName.getText());
		model.getFlexProjectInformation().setApplicationModelClassName(txtApplicationModelName.getText());
		
		
	}
	
}
