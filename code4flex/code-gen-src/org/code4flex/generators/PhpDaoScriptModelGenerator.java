/*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Knowledgeit.
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
package org.code4flex.generators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.code4flex.dbloader.model.DBTable;
import org.code4flex.generators.model.classes.AbstractClass;
import org.code4flex.generators.model.classes.AbstractServiceClass;
import org.code4flex.generators.model.methoddescriptors.GenerationCodeForTemplateMethodDescriptor;
import org.code4flex.generators.model.methods.AbstractMethodParameter;
import org.code4flex.generators.model.php.classes.PHPClass;
import org.code4flex.generators.model.php.classes.PhpDaoClass;
import org.code4flex.generators.model.php.methods.PHPMethodParameter;
import org.code4flex.generators.model.php.methods.PhpDaoMethod;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.3 $
 */

public class PhpDaoScriptModelGenerator extends ModelGenerator
		implements ServiceExposerGenerator {
	private PhpScriptModelGenerator modelGenerator = null;
	private List<AbstractServiceClass> phpDaoList = null;
	protected List<DBTable> tables;
	private List<GenerationCodeForTemplateMethodDescriptor> methodsDescriptors;

	@Override
	public void generateModel() throws Exception {
		phpDaoList = new ArrayList<AbstractServiceClass>();
		
		// this.getPHPClases()

		for (DBTable table : tables) {
			PhpDaoClass daoClass = new PhpDaoClass();
			daoClass.setClassName(this.getDaoClassNameForTableEntity(table
					.getTableName()));
			daoClass.setDtoTable(table);
			daoClass.setDtoClass(this.findClassByName(super
					.getClassNameForTableEntity(table.getTableName())));
			daoClass.setNamespace(this.nameSpace);
			for (GenerationCodeForTemplateMethodDescriptor methodDescriptor : methodsDescriptors) {
				methodDescriptor.setClassName(daoClass.getDtoClass()
						.getClassName());

				PhpDaoMethod method = new PhpDaoMethod(methodDescriptor);
				method.setMethodType(methodDescriptor.getMethodType());
				method.setDaoClass(daoClass);
				
				List<AbstractMethodParameter> paramList = new ArrayList<AbstractMethodParameter>();
				if (methodDescriptor.getArguments() != null) {
					for (String argument : methodDescriptor.getArguments()) {
						PHPMethodParameter m = new PHPMethodParameter();
						m.setParameterName(argument);
						paramList.add(m);
					}
				}
				method.setParameterList(paramList);
				// method.generateMethodCode();

				daoClass.getMethods().add(method);

			}
			phpDaoList.add(daoClass);
		}

	}

	public List<AbstractClass> getModelClasses() {
		return modelGenerator.getPHPClases();
	}

	private PHPClass findClassByName(String className) throws Exception {
		Iterator<AbstractClass> it = modelGenerator.getPHPClases().iterator();
		while (it.hasNext()) {
			PHPClass phpclass = (PHPClass) it.next();
			if (className.equalsIgnoreCase(phpclass.getClassName()))
				return phpclass;
		}
		throw new Exception("not find exception");
	}

	protected String getDaoClassNameForTableEntity(String classname) {
		String nombreClase = super.getClassNameForTableEntity(classname);
		return nombreClase + "Dao";

	}

	public List<AbstractServiceClass> getPhpDaoList() {
		return phpDaoList;
	}

	public void setPhpDaoList(List<AbstractServiceClass> phpDaoList) {
		this.phpDaoList = phpDaoList;
	}

	public List<GenerationCodeForTemplateMethodDescriptor> getMethodsDescriptors() {
		return methodsDescriptors;
	}

	public void setMethodsDescriptors(
			List<GenerationCodeForTemplateMethodDescriptor> methodsDescriptors) {
		this.methodsDescriptors = methodsDescriptors;
	}

	public List<AbstractServiceClass> getServiceClasses() {
		return phpDaoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(Object obj) {
		 tables = (List<DBTable>) obj;
		
	}

	public PhpScriptModelGenerator getModelGenerator() {
		return modelGenerator;
	}

	public void setModelGenerator(PhpScriptModelGenerator modelGenerator) {
		this.modelGenerator = modelGenerator;
	}

}
