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

package org.code4flex.cgflexintegration.springcontext;

import java.io.File;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;


/**
 * SpringContextLoader give us support for getting the Spring Context for 
 * first time and hold it up for the next time.
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class SpringContextLoader {

	private XmlBeanFactory factory = null;

	private SpringContextLoader() {
	}

	public void init(File file) {
		if (this.factory == null) {
			FileSystemResource rs = new FileSystemResource(file);
			this.factory = new XmlBeanFactory(rs);
			factory.setBeanClassLoader(this.getClass().getClassLoader());
		}
	}

	private static SpringContextLoader instance = null;

	public static SpringContextLoader getInstance() {
		if (instance == null) {

			instance = new SpringContextLoader();
		}
		return instance;

	}

	public XmlBeanFactory getFactory() {
		return factory;
	}

}
