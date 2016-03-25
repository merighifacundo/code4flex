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

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class FlexProjectInformationModel {

	
	//Package Configuration
	private String eventPackage;
	private String commandPackage;
	private String controllerPackage;
	private String servicePackage;
	
	//booleans configuration
	private boolean testViews;
	private boolean stateController;
	private boolean cairngormImplementation;
	
	//Classes Names
	private String mainClassName;
	private String serviceLocatorName;
	private String applicationModelClassName;
	
	
	
	public String getEventPackage() {
		return eventPackage;
	}
	public void setEventPackage(String eventPackage) {
		this.eventPackage = eventPackage;
	}
	public String getCommandPackage() {
		return commandPackage;
	}
	public void setCommandPackage(String commandPackage) {
		this.commandPackage = commandPackage;
	}
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public boolean isTestViews() {
		return testViews;
	}
	public void setTestViews(boolean testViews) {
		this.testViews = testViews;
	}
	public boolean isStateController() {
		return stateController;
	}
	public void setStateController(boolean stateController) {
		this.stateController = stateController;
	}
	public boolean isCairngormImplementation() {
		return cairngormImplementation;
	}
	public void setCairngormImplementation(boolean cairngormImplementation) {
		this.cairngormImplementation = cairngormImplementation;
	}
	public String getMainClassName() {
		return mainClassName;
	}
	public void setMainClassName(String mainClassName) {
		this.mainClassName = mainClassName;
	}
	public String getServiceLocatorName() {
		return serviceLocatorName;
	}
	public void setServiceLocatorName(String serviceLocatorName) {
		this.serviceLocatorName = serviceLocatorName;
	}
	public String getApplicationModelClassName() {
		return applicationModelClassName;
	}
	public void setApplicationModelClassName(String applicationModelClassName) {
		this.applicationModelClassName = applicationModelClassName;
	}
	
	
}
