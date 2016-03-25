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
package org.code4flex.generators.model;

import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;
import org.code4flex.generators.model.classes.AbstractClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class FlexStateClassDeclaration extends ActionScriptClass implements AbstractClass {

	private FlexSimpleViewModel viewReference;
	private String view;
	private String childName;
	
	public void init(FlexSimpleViewModel simpleView) {
		this.setClassName(simpleView.getClassName() + "State");
		this.setChildName(simpleView.getClassName() + "Child");
		this.setView(simpleView.getClassName());
		this.setViewReference(simpleView);
	}

	private void setViewReference(FlexSimpleViewModel simpleView) {
		this.viewReference = simpleView;
		
	}

	private void setView(String className) {
		this.view = className;
		
	}

	private void setChildName(String string) {
		this.childName = string;
		
	}

	public FlexSimpleViewModel getViewReference() {
		return viewReference;
	}

	public String getView() {
		return view;
	}

	public String getChildName() {
		return childName;
	}
	public String getIdView(){
		
		return this.view + "View";
		
	}
	
	


}
