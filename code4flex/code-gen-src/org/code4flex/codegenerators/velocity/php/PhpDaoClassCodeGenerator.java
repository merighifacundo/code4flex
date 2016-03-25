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

package org.code4flex.codegenerators.velocity.php;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.EscapeTool;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.php.classes.PhpDaoClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class PhpDaoClassCodeGenerator extends PhpVelocityCodeGenerator {

	private String finalPath;
	
	public void generate() {
		try {
			
			initVelocityTemplate();
			Iterator<AbstractClass> it = classes.iterator();
			while (it.hasNext()) {
				
				PhpDaoClass table = (PhpDaoClass) it.next();
				String path = this.mainGenerator.getProyectDestPath() + File.separatorChar  +  "amfphp" + File.separator + "services" +   File.separatorChar + this.getNamespacePath(table);
				this.setFinalPath(path);
				createPathIfDontExist();
				FileWriter fwriter = new FileWriter(getFinalPath() + File.separator
						+ table.getClassName() + ".php");
				VelocityContext context = new VelocityContext();
				context.put("class", table);
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

	
//	@Override
//	public String getPackageDirectory() {
//		// TODO Auto-generated method stub
//		return  File.separator +"php" + File.separator  + this.getProyectName().toLowerCase() +  File.separator + "dao" + File.separator ;
//	}

	public String getFinalPath() {
		return finalPath;
	}
	public void setFinalPath(String finalPath) {
		this.finalPath = finalPath;
	}
	
}
