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

public class PhpProjectInformationModel {
	
	//Package Configuration
	private String daoPackage;
	private String servicePackage;
	private String modelPackage;
	
	
	//Development enviroment configuration.
	private int portNumber;
	private String hostName;
	private String localServerDirectory;
	
	private boolean hasEnviromentInfo = false;
	
	public String getDaoPackage() {
		return daoPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getLocalServerDirectory() {
		return localServerDirectory;
	}
	public void setLocalServerDirectory(String localServerDirectory) {
		this.localServerDirectory = localServerDirectory;
	}
	public boolean isHasEnviromentInfo() {
		return hasEnviromentInfo;
	}
	public void setHasEnviromentInfo(boolean hasEnviromentInfo) {
		this.hasEnviromentInfo = hasEnviromentInfo;
	}
	
	
	
	

}
