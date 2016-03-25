 /*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors. �All third-party contributions are
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

package org.code4flex.codegenerators.velocity.actionscript;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.EscapeTool;
import org.code4flex.codegenerators.velocity.VelocityCodeGenerator;
import org.code4flex.generators.model.actionscript.cairngorm.classes.FlexCairngormControllerClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class ActionScriptFlexCairngormControllerCodeGenerator extends
		VelocityCodeGenerator {

	private FlexCairngormControllerClass controller;
	private String finalPath;

	@Override
	public void generate() {
		try {

			this.initVelocityTemplate();

			String namespacePath = getNamespacePath(controller);

			this.setFinalPath(this.mainGenerator.getProyectDestPath()
					+ File.separatorChar + "src" + File.separatorChar
					+ namespacePath);
			createPathIfDontExist();
			FileWriter fwriter = new FileWriter(getFinalPath() + File.separator
					+ controller.getClassName() + ".as");
			VelocityContext context = new VelocityContext();
			context.put("controller", controller);
			context.put("package", controller.getNamespace());
			context.put("esc", new EscapeTool());
			Template template = this.getVelocityTemplate();
			template.merge(context, fwriter);
			fwriter.close();

		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFinalPath(String finalPath) {
		this.finalPath = finalPath;
	}

	@Override
	public String getFinalPath() {
		// TODO Auto-generated method stub
		return finalPath;
	}

	public void setController(FlexCairngormControllerClass controller) {
		this.controller = controller;

	}

}
