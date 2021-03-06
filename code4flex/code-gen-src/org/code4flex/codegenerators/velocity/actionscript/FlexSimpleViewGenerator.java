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

package org.code4flex.codegenerators.velocity.actionscript;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.EscapeTool;
import org.code4flex.codegenerators.velocity.VelocityCodeGenerator;
import org.code4flex.generators.model.FlexSimpleViewModel;
import org.code4flex.generators.model.actionscript.classes.ActionScriptModelApplicationClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class FlexSimpleViewGenerator extends VelocityCodeGenerator {
	private String finalPath;
	private List<FlexSimpleViewModel> eventList;
	private ActionScriptModelApplicationClass actionScriptModelApplicationClass;

	@Override
	public void generate() {
		try {

			this.initVelocityTemplate();

			Iterator<FlexSimpleViewModel> it = eventList.iterator();
			while (it.hasNext()) {
				FlexSimpleViewModel table =  it.next();
				String namespacePath = getNamespacePath(table.getNamespace());

				this.setFinalPath(this.mainGenerator.getProyectDestPath()
						+ File.separatorChar + "src" + File.separatorChar
						+ namespacePath);
				createPathIfDontExist();
				FileWriter fwriter = new FileWriter(getFinalPath()
						+ File.separator + table.getClassName() + ".mxml");
				VelocityContext context = new VelocityContext();
				context.put("viewModel", table);
				context.put("modelApplication", actionScriptModelApplicationClass);
				context.put("package", table.getNamespace());
				context.put("esc", new EscapeTool());
				Template template = this.getVelocityTemplate();
				template.merge(context, fwriter);
				fwriter.close();
			}

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
		return this.finalPath;
	}

	public void setClasses(List<FlexSimpleViewModel> eventList) {
		this.eventList = eventList;
		
	}

	public void setActionScriptModelApplication(
			ActionScriptModelApplicationClass ap) {
		this.actionScriptModelApplicationClass = ap;
		
	}
}
