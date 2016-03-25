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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.velocity.Template;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;

/**
 * <p>
 * StringTemplate is an extension of Velocity Template
 * It's useful for many reasons but in our case this let as
 * put a code template in a property of the spring context's bean.
 * ref: CodeGenerator.xml (configuration)
 * </p>
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class StringTemplate extends Template {

	private String codeTemplate = null;
	
	public StringTemplate(String template){
		
		
		super();
		this.codeTemplate = template;
		this.setRuntimeServices(RuntimeSingleton.getRuntimeServices());
	}
	
	
	public boolean process() throws ResourceNotFoundException,
			ParseErrorException, IOException {
			data = null;
		
			try {
				
				StringReader sr = new StringReader(this.codeTemplate);
				BufferedReader br = new BufferedReader(sr);
				data = rsvc.parse(br, name);
				initDocument();
				return true;
			}  catch (ParseException pex) {
				/*
				 * remember the error and convert
				 */
				pex.printStackTrace();
				throw new IOException(pex.getMessage());
			} catch (TemplateInitException pex) {
				pex.printStackTrace();
				ParseErrorException errorParsing = new ParseErrorException(pex);
				throw errorParsing;
			}
			/**
			 * pass through runtime exceptions
			 */
			catch (RuntimeException e) {
				e.printStackTrace();
				throw new RuntimeException(
						"Exception thrown processing Template " + getName(), e);
			} finally {
				/*
				 * Make sure to close the inputstream when we are done.
				 */
				//is.close();
			}
		
			/*
			 * is == null, therefore we have some kind of file issue
			 */

		
		
	}

}
