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

package org.code4flex.codegenerators.resourcexporter;

import java.io.File;
import java.io.IOException;

import org.code4flex.codegenerators.CodeGenerator;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public abstract class ResourceExporterCodeGenerator {

	protected CodeGenerator codeGenerator = null;
	protected String resourceToExport;
	protected String sourceDirectory;
	protected String destinationDirectory;

	
	
	
	public String getDestinationDirectory() {
		return destinationDirectory;
	}

	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}

	public CodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	public void setCodeGenerator(CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
	}
	
	
	
	public String getResourceToExport() {
		return resourceToExport;
	}

	public void setResourceToExport(String resourceToExport) {
		this.resourceToExport = resourceToExport;
	}
	
	

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public abstract String getFinalPath();
	
	public abstract void exportResource() throws IOException;
	
	public boolean createPathIfDontExist(){
		
		String mainPath = getFinalPath();
		
		File dir = new File(mainPath);
		if(dir.exists())
			return true;
		dir.mkdirs();
		return false;
	}
	
}
