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

package org.code4flex.cgflexintegration.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.observer.Observer;
import org.code4flex.cgflexintegration.observer.Subject;
import org.code4flex.dbloader.ConnectionProfiler;
import org.code4flex.dbloader.DBAnalizer;
import org.code4flex.dbloader.model.DBTable;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class PluginModel implements Subject {

	
	
	



	private PluginModel() {
		this.projectType = new ProjectType();
		this.connectionInformation = new MySqlConnectionInformationModel();
		this.flexProjectInformation = new FlexProjectInformationModel();
		//If this is null and project type is 2 | 3 => Java project
		this.phpProjectInformation = new PhpProjectInformationModel();
		
	}

	private static PluginModel instance = null;
	
	private MySqlConnectionInformationModel connectionInformation;

	private FlexProjectInformationModel flexProjectInformation;

	private PhpProjectInformationModel phpProjectInformation;
	
	private ProjectType projectType;
	
	private DBAnalizer analizer = new DBAnalizer();

	private List<Observer> observers = new ArrayList<Observer>();

	private List<DBTable> selectedRows;

	private Properties messages;
	
	private static HashMap<String,String> defaultMessages = new HashMap<String,String>();
	//Default Messages initialization
	static{
		defaultMessages.put("com.c4f.wizard.title","KnowledgeIt - CG Flex Integration");
		defaultMessages.put("com.c4f.wizard.description","This wizard creates a new Code Generation Flex Integration");
		//Data Base Loader Wizard Internationalization
		defaultMessages.put("com.c4f.wizard.dbloader.testconn","Test Connection");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.title","MySql Connection Settings");

		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.txtHostDefault","localhost");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.txtPortDefault","3306");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.chkPasswordRequired","Password Required?");
		
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.host","Host");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.dbname","DB Name");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.username","UserName");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.port","Port");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.password","Password");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.passwordconfirmation","Password Confirm");
		
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.ok.title","connection status ok");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.ok.message","Connection Success");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.failure.title","connection status Failure");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.alertconnstatus.failure.message","Connection Failure\n\nReason: ?");		
		defaultMessages.put("com.c4f.wizard.dbloader.btnHelpText","Please fill the form to connect");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.hostHelpText","Ip or HostName of your MySql DB");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.dbnameHelpText","DB Name example: code4flexdb");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.usernameHelpText","UserName Wamp Example: root");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.funnyHelpText","Remember you need a MySql DB. We can not generate code from the dust...");
		defaultMessages.put("com.c4f.wizard.dbloader.mysqlsettings.portHelpText","Number Value of the port in which\n the connection is running");
		//Dialog windows Internationalization
		defaultMessages.put("com.c4f.showknowledgeit.url","http://knowledgeit.com.ar");
	}
	
	public void clearInstance(){
		
		this.analizer = new DBAnalizer();
		this.connectionInformation = new MySqlConnectionInformationModel();
		this.flexProjectInformation = new FlexProjectInformationModel();
		this.phpProjectInformation = new PhpProjectInformationModel();
	}
	
	public static PluginModel getInstance() {

		if (instance == null) {
			instance = new PluginModel();
		}
		return instance;
	}

	
	
	




	public List<DBTable> getModel() {

		if (this.analizer.getModel() == null || this.analizer.getModel().isEmpty()) {

			try {
				this.analizer.analizeConnection(ConnectionProfiler
						.getModelConnection());
			} catch (SQLException e) {
				ExceptionReport.reportException(e,true);
				e.printStackTrace();
			}

		}

		return this.analizer.getModel();

	}

	public boolean testConnection() throws Exception {
		try {
			if (ConnectionProfiler.getModelConnection() != null) {
				notifyObservers();
				return true;
			}
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
			
		}
	}


	public void addObserver(Observer o) {

		this.observers.add(o);
	}

	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		this.observers.remove(o);
	}

	private void notifyObservers() {
		// loop through and notify each observer
		Iterator<Observer> i = observers.iterator();
		while (i.hasNext()) {
			Observer o =  i.next();
			o.update(this);
		}
	}

	public MySqlConnectionInformationModel getConnectionInformation() {
		return connectionInformation;
	}

	public FlexProjectInformationModel getFlexProjectInformation() {
		return flexProjectInformation;
	}

	public PhpProjectInformationModel getPhpProjectInformation() {
		return phpProjectInformation;
	}
	
	

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setSelectedRows(List<DBTable> checkedRows) {
		this.selectedRows = checkedRows;
		
	}
	//use java reference if is java project
	public String getModelNamespace(){
		return this.phpProjectInformation.getModelPackage();
	}

	public List<DBTable> getSelectedRows() {
		return selectedRows;
	}

	public Properties getMessages() {
		if(messages == null)
			messages = new Properties();
		return messages;
	}

	public void setMessages(Properties messages) {
		this.messages = messages;
	}

	public String getProperty(String property){
		
		return this.getMessages().getProperty(property,defaultMessages.get(property));
		
	}

}
