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
package org.code4flex.generators.model.actionscript.cairngorm.classes;

import java.util.ArrayList;
import java.util.List;

import org.code4flex.generators.model.CommandInformation;
import org.code4flex.generators.model.EventInformation;
import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class FlexCairngormControllerClass extends ActionScriptClass {
	
	private static final String _parentClass = "com.adobe.cairngorm.control.FrontController";

	
	private List<FlexCairngormControllerEventRelation> relations = new ArrayList<FlexCairngormControllerEventRelation>();
	private CommandInformation commandInfo;
	private EventInformation eventInfo;
	public List<FlexCairngormControllerEventRelation> getRelations() {
		return relations;
	}

	public void setRelations(List<FlexCairngormControllerEventRelation> relations) {
		this.relations = relations;
	}

	public CommandInformation getCommandInfo() {
		return commandInfo;
	}

	public void setCommandInfo(CommandInformation commandInfo) {
		this.commandInfo = commandInfo;
	}

	public EventInformation getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInformation eventInfo) {
		this.eventInfo = eventInfo;
	}

	public void init() {
		this.commandInfo = new CommandInformation();
		this.eventInfo = new EventInformation();
		this.commandInfo.init(this.relations.get(0).getCommand());
		this.eventInfo.init(this.relations.get(0).getEvent());
	}

	@Override
	public String getParentClass() {
		return FlexCairngormControllerClass._parentClass;
	}

	
	
	
}
