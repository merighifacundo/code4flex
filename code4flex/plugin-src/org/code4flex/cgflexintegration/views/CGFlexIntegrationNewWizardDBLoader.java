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

import java.util.Map.Entry;

import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.widgets.validator.group.TextBoxesValidatorGroup;
import org.code4flex.cgflexintegration.widgets.validator.utils.ValidatorUtils;
import org.code4flex.dbloader.ConnectionProfiler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class CGFlexIntegrationNewWizardDBLoader extends WizardPage {

	@SuppressWarnings("unused")
	private ISelection selection;

	// Validator
	TextBoxesValidatorGroup validator = new TextBoxesValidatorGroup();

	// TextBoxes
	private Text txtHost;
	private Text txtDBName;
	private Text txtUserName;
	private Text txtPort;
	private Text txtPasswordConf;
	private Text txtPassword;
	
	//Button
	private Button btnConnectionButton = null;
	
	//Plugin Model
	private PluginModel model = PluginModel.getInstance();
	
	
	public CGFlexIntegrationNewWizardDBLoader(ISelection selection) {
		super("wizardPage2");
		setTitle(model.getProperty("com.c4f.wizard.title"));
		setDescription(model.getProperty("com.c4f.wizard.description"));
		this.selection = selection;

	}

	private Listener nameModifyListener = new Listener() {
		public void handleEvent(Event e) {
			validatePage();
			

		}
	};

	/**
	 * Returns whether this page's controls currently all contain valid values.
	 * 
	 * @return <code>true</code> if all controls are valid, and
	 *         <code>false</code> if at least one is invalid
	 */
	protected boolean validatePage() {

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
			
			return false;
		}
		
		setErrorMessage(null);
		setMessage(null);
		return true;
	}

	public void createControl(Composite arg0) {
		Composite container = new Composite(arg0, SWT.NULL);
		setPageComplete(false);
		container.setLayout(null);
		setControl(container);

		btnConnectionButton = new Button(container, SWT.NONE);
		btnConnectionButton.setToolTipText(model.getProperty("com.c4f.wizard.dbloader.btnHelpText"));
		

		btnConnectionButton.setText(model.getProperty("com.c4f.wizard.dbloader.testconn"));
		btnConnectionButton.setBounds(609, 470, 119, 29);

		final Group group = new Group(container, SWT.NONE);
		group.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.title"));
		group.setBounds(65, 65, 665, 397);

		final Label hostLabel = new Label(group, SWT.NONE);
		hostLabel.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.host"));
		hostLabel.setBounds(35, 35, 52, 20);

		txtHost = new Text(group, SWT.BORDER);
		txtHost.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.txtHostDefault"));
		txtHost.setBounds(145, 30, 209, 25);

		final Label dbNameLabel = new Label(group, SWT.NONE);
		dbNameLabel.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.dbname"));
		dbNameLabel.setBounds(35, 90, 67, 20);

		txtDBName = new Text(group, SWT.BORDER);
		txtDBName.setBounds(145, 85, 209, 25);

		final Label usernameLabel = new Label(group, SWT.NONE);
		usernameLabel.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.username"));
		usernameLabel.setBounds(35, 140, 67, 22);

		txtUserName = new Text(group, SWT.BORDER);
		txtUserName.setBounds(145, 140, 209, 25);

		final Label lblHelpHost = new Label(group, SWT.NONE);
		lblHelpHost.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.hostHelpText"));
		lblHelpHost.setBounds(375, 35, 216, 20);

		final Label lblHelpDBName = new Label(group, SWT.NONE);
		lblHelpDBName.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.dbnameHelpText"));
		lblHelpDBName.setBounds(375, 90, 194, 20);

		final Label lblHelpUserName = new Label(group, SWT.NONE);
		lblHelpUserName.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.usernameHelpText"));
		lblHelpUserName.setBounds(375, 145, 194, 20);

		final Label portLabel = new Label(group, SWT.NONE);
		portLabel.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.port"));
		portLabel.setBounds(35, 195, 52, 20);

		txtPort = new Text(group, SWT.BORDER);
		txtPort.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.txtPortDefault"));
		txtPort.setBounds(145, 195, 209, 25);

		final Label numberValueOfLabel = new Label(group, SWT.NONE);
		numberValueOfLabel
				.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.portHelpText"));
		numberValueOfLabel.setBounds(375, 190, 194, 43);

		final Group passwordGroup = new Group(group, SWT.NONE);
		passwordGroup.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.password"));
		passwordGroup.setBounds(10, 251, 645, 136);

		txtPasswordConf = new Text(passwordGroup, SWT.PASSWORD | SWT.BORDER);
		txtPasswordConf.setEnabled(false);
		txtPasswordConf.setBounds(135, 75, 209, 25);

		txtPassword = new Text(passwordGroup, SWT.PASSWORD | SWT.BORDER);
		txtPassword.setEnabled(false);
		txtPassword.setBounds(135, 25, 209, 25);

		final Label passwordLabel = new Label(passwordGroup, SWT.NONE);
		passwordLabel.setBounds(20, 30, 93, 22);
		passwordLabel.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.password"));

		final Label lblConfPassword = new Label(passwordGroup, SWT.NONE);
		lblConfPassword.setBounds(20, 80, 109, 20);
		lblConfPassword.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.passwordconfirmation"));

		final Button chkRequired = new Button(passwordGroup, SWT.CHECK);
		chkRequired.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (chkRequired.getSelection()) {
					validator.addControl(txtPassword,ValidatorUtils.createDBPasswordValidator(txtPasswordConf));
					txtPassword.setEnabled(true);
					txtPasswordConf.setEnabled(true);
				} else {
					txtPassword.setEnabled(false);
					txtPasswordConf.setEnabled(false);
					validator.removeControl(txtPassword);
				}
			}
		});
		chkRequired.setBounds(372, 26, 151, 16);
		chkRequired.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.chkPasswordRequired"));

		btnConnectionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				try {
					if (!validatePage())
						return;

					int port = Integer.parseInt(txtPort.getText());
					PluginModel uniqueInstance = PluginModel
							.getInstance();
					
					uniqueInstance.getConnectionInformation().setDbName(
							txtDBName.getText());
					uniqueInstance.getConnectionInformation().setHost(
							txtHost.getText());
					if (!chkRequired.getSelection()) {
						uniqueInstance.getConnectionInformation().setPassword(null);
					} else {
						uniqueInstance.getConnectionInformation().setPassword(
								txtPassword.getText());
					}
					uniqueInstance.getConnectionInformation().setUserName(
							txtUserName.getText());
					uniqueInstance.getConnectionInformation().setPort(port);
					MessageBox msgBox = new MessageBox(Display.getCurrent()
							.getActiveShell());
					if (!uniqueInstance.testConnection()) {

						msgBox.setMessage(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.failure.message").replace("?", ConnectionProfiler.getLastException().getMessage().substring(0, 25)));
						msgBox.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.failure.title"));

					} else {
						msgBox.setMessage(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.ok.message"));
						msgBox.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.ok.title"));

						setPageComplete(true);

					}
					msgBox.open();
				} catch (Exception error) {
					ExceptionReport.reportException(error,false);
					error.printStackTrace();
				} catch (SWTError error) {
					ExceptionReport.reportException(new Exception(error.getMessage()),false);
					error.printStackTrace();

				}

			}

		});
		
		final Label rememberYouNeedLabel = new Label(container, SWT.NONE);
		rememberYouNeedLabel.setBounds(65, 40, 501, 13);
		rememberYouNeedLabel.setForeground(SWTResourceManager.getColor(255, 0,
				0));
		rememberYouNeedLabel
				.setText(model.getProperty("com.c4f.wizard.dbloader.mysqlsettings.funnyHelpText"));
		
		
		//Add Listeners
		txtDBName.addListener(SWT.Modify,nameModifyListener);
		txtHost.addListener(SWT.Modify,nameModifyListener);
		txtPassword.addListener(SWT.Modify,nameModifyListener);
		txtPasswordConf.addListener(SWT.Modify,nameModifyListener);
		txtPort.addListener(SWT.Modify,nameModifyListener);
		txtUserName.addListener(SWT.Modify,nameModifyListener);
		chkRequired.addListener(SWT.Modify,nameModifyListener);
		
		//Validator
		validator.addControl(txtDBName, ValidatorUtils.createValidator("",""));
		validator.addControl(txtHost, ValidatorUtils.createValidator("",""));
		validator.addControl(txtUserName, ValidatorUtils.createValidator("",""));
		validator.addControl(txtPort, ValidatorUtils.createValidator("",""));
		validator.addControl(txtHost, ValidatorUtils.createValidator("",""));
	}

}
