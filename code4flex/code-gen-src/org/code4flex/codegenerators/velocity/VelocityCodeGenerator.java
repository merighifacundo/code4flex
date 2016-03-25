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

package org.code4flex.codegenerators.velocity;

import java.io.File;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.code4flex.codegenerators.CodeGenerator;
import org.code4flex.generators.model.classes.AbstractClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */


public abstract class VelocityCodeGenerator {

	// nombre del template archivo con extension .vm
	private String template;
	// directorio en el plugin o en el filesystem donde se encuentra el arhivo.
	private String templatePath;
	// directorio de destiono. del proyecto
	private String proyectDest;
	// nombre del proyecto
	private String proyectName;
	// Generador que invoca a este generador.
	protected CodeGenerator mainGenerator;


	/*Metodos a implementar por las subclases*/
	
	public abstract void generate();

	
	
	public abstract String getFinalPath();
	
	//use before setting the PackagePath
	protected String getNamespacePath(AbstractClass table) {
		String namespacePath = (table.getNamespace()==null)?"":table.getNamespace();
		namespacePath = namespacePath.replace(".", File.separator);
		return namespacePath;
	}
	
	protected String getNamespacePath(String namespacePathToChange) {
		String namespacePath = (namespacePathToChange==null)?"":namespacePathToChange;
		namespacePath = namespacePath.replace(".", File.separator);
		return namespacePath;
	}

	public boolean createPathIfDontExist() {

		String mainPath = getFinalPath();

		File dir = new File(mainPath);
		if (dir.exists())
			return true;
		dir.mkdirs();
		return false;
	}

	public void initVelocityTemplate() {

		try {
			Properties p = new Properties();
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this
					.getMainGenerator().getTemplatePath());
			Velocity.init(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Template getVelocityTemplate() {
		try {
			
			return Velocity.getTemplate(getTemplateFileName());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*getters y setters*/

	public String getTemplateFileName() {
		return this.template;
	}
	
	
	public CodeGenerator getMainGenerator() {
		return mainGenerator;
	}

	public void setMainGenerator(CodeGenerator mainGenerator) {
		this.mainGenerator = mainGenerator;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getProyectDest() {
		return proyectDest;
	}

	public void setProyectDest(String proyectDest) {
		this.proyectDest = proyectDest;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getProyectName() {
		return proyectName;
	}

	public void setProyectName(String proyectName) {
		this.proyectName = proyectName;
	}

	
	
}
