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

import org.code4flex.cgflexintegration.Activator;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.widgets.utils.ImageLoader;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
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
 * @version $Revision: 1.2 $
 */

@SuppressWarnings("restriction")
public class PhpCGProjectCreationPage extends WizardPage {

	// initial value stores
	private String initialProjectFieldValue;

	// widgets
	Text projectNameField;

	// knowledge textboxvalidatorgrup
	TextBoxesValidatorGroup validator = new TextBoxesValidatorGroup();

	// TextBoxes
	private Text txtServicePackage;
	private Text txtDaoPackage;
	private Text txtModelPackage;
	
	private Text txtHost;
	private Text txtDirectory;
	private Text txtPort;
	
	private Button chkHavePhpEnviroment;

	private Group groupDoYouHave = null;
	private Group groupPhpConfig = null;
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
	 * @param pageName
	 *            the name of this page
	 */
	public PhpCGProjectCreationPage(String pageName) {
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
	public PhpCGProjectCreationPage(String pageName,
			IStructuredSelection selection, String[] workingSetTypes) {
		this(pageName);
	}

	/**
	 * (non-Javadoc) Method declared on IDialogPage.
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);

		initializeDialogUnits(parent);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
				IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createProjectNameGroup(composite);
		locationArea = new ProjectContentsLocationArea(getErrorReporter(),
				composite);
		if (initialProjectFieldValue != null) {
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

		final SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.DEFAULT, 18));

		groupPhpConfig = new Group(composite, SWT.NONE);
		groupPhpConfig.setText("Php Project Configuration");
		final GridData gd_groupPhpConfig = new GridData(705, 284);
		groupPhpConfig.setLayoutData(gd_groupPhpConfig);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		groupPhpConfig.setLayout(gridLayout);
		new Label(groupPhpConfig, SWT.NONE);
		new Label(groupPhpConfig, SWT.NONE);

		final Composite composite_3 = new Composite(groupPhpConfig, SWT.NONE);
		composite_3.setLayoutData(new GridData(268, 19));
		composite_3.setLayout(new GridLayout());

		chkHavePhpEnviroment = new Button(composite_3, SWT.CHECK);

		final GridData gd_chkHavePhpEnviroment = new GridData(201, SWT.DEFAULT);
		chkHavePhpEnviroment.setLayoutData(gd_chkHavePhpEnviroment);
		chkHavePhpEnviroment.setText("Do you have Php Enviroment?");

		final Group phpPackageConfigurationGroup = new Group(groupPhpConfig,
				SWT.NONE);
		phpPackageConfigurationGroup.setText("Php Package Configuration");
		final GridData gd_phpPackageConfigurationGroup = new GridData(291, 230);
		phpPackageConfigurationGroup
				.setLayoutData(gd_phpPackageConfigurationGroup);
		phpPackageConfigurationGroup.setLayout(null);

		final Label daoPackageLabel = new Label(phpPackageConfigurationGroup,
				SWT.NONE);
		daoPackageLabel.setText("Dao Package");
		daoPackageLabel.setBounds(10, 40, 71, 20);

		txtDaoPackage = new Text(phpPackageConfigurationGroup, SWT.BORDER);
		txtDaoPackage.setText("org.code4flex.dao");
		txtDaoPackage.setBounds(100, 35, 145, 25);

		final Label servicePackageLabel = new Label(
				phpPackageConfigurationGroup, SWT.NONE);
		servicePackageLabel.setText("Service Package");
		servicePackageLabel.setBounds(10, 95, 85, 25);

		txtServicePackage = new Text(phpPackageConfigurationGroup, SWT.BORDER);
		txtServicePackage.setText("org.code4flex.services");
		txtServicePackage.setBounds(100, 90, 145, 25);

		final Label modelPackageLabel = new Label(phpPackageConfigurationGroup,
				SWT.NONE);
		modelPackageLabel.setText("Model Package");
		modelPackageLabel.setBounds(10, 150, 85, 25);

		txtModelPackage = new Text(phpPackageConfigurationGroup, SWT.BORDER);
		txtModelPackage.setText("org.code4flex.model");
		txtModelPackage.setBounds(100, 145, 145, 25);

		final SashForm sashForm_1 = new SashForm(groupPhpConfig, SWT.NONE);
		sashForm_1.setLayoutData(new GridData(90, 230));
		sashForm_1.setLayout(null);
		paintDoYouHave(groupPhpConfig);

		chkHavePhpEnviroment.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				try {
					if (chkHavePhpEnviroment.getSelection()) {
						// groupDoYouHave.dispose();
						groupPhpConfig.redraw();
						paintConfig(groupPhpConfig);
						groupDoYouHave.redraw();
						groupPhpConfig.redraw();

					} else {
						// groupDoYouHave.dispose();
						groupPhpConfig.redraw();
						paintDoYouHave(groupPhpConfig);
						groupDoYouHave.redraw();
						groupPhpConfig.redraw();

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// listener form modifications:
		txtDaoPackage.addListener(SWT.Modify, nameModifyListener);
		txtModelPackage.addListener(SWT.Modify, nameModifyListener);
		txtServicePackage.addListener(SWT.Modify, nameModifyListener);

		// Adding validator;
		validator.addControl(txtDaoPackage, ValidatorUtils.createValidator("",
				""));
		validator.addControl(txtModelPackage, ValidatorUtils.createValidator(
				"", ""));
		validator.addControl(txtServicePackage, ValidatorUtils.createValidator(
				"", ""));

	}

	private void paintDoYouHave(Group groupPhpConfig) {
		if (groupDoYouHave == null) {
			groupDoYouHave = new Group(groupPhpConfig, SWT.NONE);
			groupDoYouHave.setLayoutData(new GridData(291, 230));
		} else {

			while (groupDoYouHave.getChildren().length > 0) {

				// groupDoYouHave.getChildren()[i].setParent(null);
				groupDoYouHave.getChildren()[0].dispose();
				
			}

		}

		groupDoYouHave.setText("If you haven't an Php Enviroment. We suggest");
		groupDoYouHave.setLayout(null);
		

		Composite composite_1 = new Composite(groupDoYouHave, SWT.NONE);
		composite_1.setLayout(null);
		//composite_1.setLayoutData(new GridData(280, 80));
		composite_1.setBackgroundImage(ImageLoader.getPluginImage(Activator
				.getDefault(), "configuration/images/mampforeclipse.jpg"));
		composite_1.setBounds(10, 44,280, 80);
		SashForm sashForm_2 = new SashForm(groupDoYouHave, SWT.NONE);
		//sashForm_2.setLayoutData(new GridData(SWT.DEFAULT, 27));
		sashForm_2.setBounds(10, 130, SWT.DEFAULT, 27);
		
		
		Composite composite_2 = new Composite(groupDoYouHave, SWT.NONE);
		composite_2.setLayout(null);
		composite_2.setBounds(10,150,280, 80);
		//composite_2.setLayoutData(new GridData(280, 80));
		composite_2.setBackgroundImage(ImageLoader.getPluginImage(Activator
				.getDefault(), "configuration/images/wampforeclipse.jpg"));
	}

	private void paintConfig(Group groupPhpConfig) {
		if (groupDoYouHave == null) {
			groupDoYouHave = new Group(groupPhpConfig, SWT.NONE);
			groupDoYouHave.setLayoutData(new GridData(291, 230));
		} else {

			while (groupDoYouHave.getChildren().length > 0) {

				// groupDoYouHave.getChildren()[i].setParent(null);
				groupDoYouHave.getChildren()[0].dispose();
				
			}

		}
		groupDoYouHave.setText("Configure your local development enviroment");
		// GridData gd_groupDoYouHave = new GridData(291, 230);
		// groupDoYouHave.setLayoutData(gd_groupDoYouHave);
		groupDoYouHave.setLayout(null);

		txtHost = new Text(groupDoYouHave, SWT.BORDER);
		txtHost.setText("localhost");
		txtHost.setBounds(40, 44, 95, 25);

		txtPort = new Text(groupDoYouHave, SWT.BORDER);
		txtPort.setText("80");
		txtPort.setBounds(40, 99, 95, 25);

		txtDirectory = new Text(groupDoYouHave, SWT.BORDER);
		txtDirectory.setBounds(40, 152, 95, 25);

		final Button button = new Button(groupDoYouHave, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				DirectoryDialog dialogFile = new DirectoryDialog(Display.getCurrent()
						.getActiveShell());
				dialogFile.open();
				txtDirectory.setText(dialogFile.getFilterPath());
			}
		});
		button.setText("Deploy Directory");
		button.setBounds(150, 150,130, 30);
		txtHost.addListener(SWT.Modify, nameModifyListener);
		txtPort.addListener(SWT.Modify, nameModifyListener);
		txtDirectory.addListener(SWT.Modify, nameModifyListener);
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
	 * 
	 * @return IErrorMessageReporter
	 */
	private IErrorMessageReporter getErrorReporter() {
		return new IErrorMessageReporter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea
			 * .IErrorMessageReporter#reportError(java.lang.String)
			 */
			public void reportError(String errorMessage, boolean infoOnly) {
				if (infoOnly) {
					setMessage(errorMessage, IStatus.INFO);
					setErrorMessage(null);
				} else
					setErrorMessage(errorMessage);
				boolean valid = errorMessage == null;
				if (valid) {
					valid = validatePage();
				}

				setPageComplete(valid);
			}
		};
	}

	/**
	 * Creates the project name specification controls.
	 * 
	 * @param parent
	 *            the parent composite
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
		projectLabel
				.setText(IDEWorkbenchMessages.WizardNewProjectCreationPage_nameLabel);
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
	 * Returns the current project location path as entered by the user, or its
	 * anticipated initial value. Note that if the default has been returned the
	 * path in a project description used to create a project should not be set.
	 * 
	 * @return the project location path or its anticipated initial value.
	 */
	public IPath getLocationPath() {
		return new Path(locationArea.getProjectLocation());
	}

	/**
	 * /** Returns the current project location URI as entered by the user, or
	 * <code>null</code> if a valid project location has not been entered.
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
	 * Returns the current project name as entered by the user, or its
	 * anticipated initial value.
	 * 
	 * @return the project name, its anticipated initial value, or
	 *         <code>null</code> if no project name is known
	 */
	public String getProjectName() {
		if (projectNameField == null) {
			return initialProjectFieldValue;
		}

		return getProjectNameFieldValue();
	}

	/**
	 * Returns the value of the project name field with leading and trailing
	 * spaces removed.
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
	 * Sets the initial project name that this page will use when created. The
	 * name is ignored if the createControl(Composite) method has already been
	 * called. Leading and trailing spaces in the name are ignored. Providing
	 * the name of an existing project will not necessarily cause the wizard to
	 * warn the user. Callers of this method should first check if the project
	 * name passed already exists in the workspace.
	 * 
	 * @param name
	 *            initial project name for this page
	 * 
	 * @see IWorkspace#validateName(String, int)
	 * 
	 */
	public void setInitialProjectName(String name) {
		if (name == null) {
			initialProjectFieldValue = null;
		} else {
			initialProjectFieldValue = name.trim();
			if (locationArea != null) {
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
	 * Returns whether this page's controls currently all contain valid values.
	 * 
	 * @return <code>true</code> if all controls are valid, and
	 *         <code>false</code> if at least one is invalid
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
		if (validLocationMessage != null) { // there is no destination location
			// given
			setErrorMessage(validLocationMessage);
			return false;
		}
		if(chkHavePhpEnviroment.getSelection()){
			validator.addControl(txtHost, ValidatorUtils.createValidator(null, null));
			validator.addControl(txtDirectory, ValidatorUtils.createValidator(null, null));
			validator.addControl(txtPort, ValidatorUtils.createValidator(null, null));
			
		}else{
			validator.removeControl(txtHost);
			validator.removeControl(txtDirectory);
			validator.removeControl(txtPort);
			
		}
		if (!validator.validateControls()) {
			String errorMessage = "";
			int errorsNumber = validator.giveMeReasons().entrySet().size();
			if (errorsNumber < 3) {
				errorMessage = "Errors(" + errorsNumber + "): ";
				for (Entry<Control, String> entry : validator.giveMeReasons()
						.entrySet()) {
					errorMessage += entry.getValue() + ", ";
				}
			} else {
				errorMessage = "Multiple errors(" + errorsNumber + ")";
			}
			setErrorMessage(errorMessage);
			// validator.showStyleErrors();
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
	 * 
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

	public void chargeModelWithBoxes() {
		PluginModel model = PluginModel
				.getInstance();
		model.getPhpProjectInformation().setDaoPackage(txtDaoPackage.getText());
		model.getPhpProjectInformation().setModelPackage(txtModelPackage.getText());
		model.getPhpProjectInformation().setServicePackage(txtServicePackage.getText());
		if(chkHavePhpEnviroment.getSelection()){
			model.getPhpProjectInformation().setHostName(txtHost.getText());
			model.getPhpProjectInformation().setLocalServerDirectory(txtDirectory.getText());
			model.getPhpProjectInformation().setPortNumber(Integer.parseInt(txtPort.getText()));
			model.getPhpProjectInformation().setHasEnviromentInfo(true);
			
		}else{
			
			model.getPhpProjectInformation().setHasEnviromentInfo(false);
		}
		//model.getPhpProjectInformation().setHostName(txt)

	}

}
