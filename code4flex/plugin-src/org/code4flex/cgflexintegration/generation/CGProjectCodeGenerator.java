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

package org.code4flex.cgflexintegration.generation;

import java.io.File;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.model.PluginModel;
import org.code4flex.cgflexintegration.springcontext.SpringContextLoader;
import org.code4flex.codegenerators.CodeGenerator;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class CGProjectCodeGenerator {

	private File file;
	private String templatePath;
	private String proyectName;
	private PluginModel theModel;
	
	
	public PluginModel getTheModel() {
		return theModel;
	}



	public void setTheModel(PluginModel theModel) {
		this.theModel = theModel;
	}



	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}



	public void setProyDest(String proyDest) {
		this.proyDest = proyDest;
	}

	private String proyDest;

	public void setFile(File file) {
		this.file = file;
	}

	public void setProyectName(String proyectName) {
		this.proyectName = proyectName;
	}

	
	public void generateCode(String type) {

		SpringContextLoader loader = SpringContextLoader.getInstance();
		loader.init(file);
		initVelocity();
		CodeGenerator cg = (CodeGenerator) loader.getFactory().getBean(
				"codeGenerator" + type);

		cg.setTemplatePath(this.templatePath);
		cg.setModel(theModel);
		cg.setProyectDestPath(proyDest);
		cg.setProyectName(this.proyectName);
		cg.generate();

	}

	private final void initVelocity() {
		try {
			Properties p = new Properties();
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.templatePath);
			Velocity.init(p);
		} catch (Exception e) {
			ExceptionReport.reportException(e,true);
			e.printStackTrace();
		}

	}
	
	
}
