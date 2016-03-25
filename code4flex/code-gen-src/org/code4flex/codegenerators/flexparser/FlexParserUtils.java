package org.code4flex.codegenerators.flexparser;

import java.util.Iterator;
import java.util.List;

import org.code4flex.generators.model.actionscript.classes.ActionScriptClass;
import org.code4flex.generators.model.classes.AbstractClass;


public class FlexParserUtils {

	
	public static ActionScriptClass findActionScriptModelClassForEvent(AbstractClass dtoClass,
			List<AbstractClass> asModelClassList) {
		
		Iterator<AbstractClass> iterator  =asModelClassList.iterator();
		while (iterator.hasNext()) {
			ActionScriptClass actionScriptClass = (ActionScriptClass) iterator
					.next();
			if(actionScriptClass.getClassName().equalsIgnoreCase(dtoClass.getClassName())){
				return actionScriptClass;
				
				
			}
		}
		return null;
		
	}
	
	
}
