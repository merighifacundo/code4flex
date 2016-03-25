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

import org.springframework.util.FileCopyUtils;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class FlexLibraryResourceExporter extends ResourceExporterCodeGenerator{

	@Override
	public void exportResource() {
		try {
			createPathIfDontExist();
			String fileNameIn = this.codeGenerator.getTemplatePath() + File.separator + this.getSourceDirectory() + this.resourceToExport;
			String fileNameOut = this.getFinalPath() + File.separator + resourceToExport;
			
			File in = new File(fileNameIn);
			File out = new File(fileNameOut);
			if(!out.exists()){
				
				out.createNewFile();
			}
			FileCopyUtils.copy(in, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getFinalPath() {
		return this.codeGenerator.getProyectDestPath() + File.separatorChar  +  this.destinationDirectory;
	}

}
