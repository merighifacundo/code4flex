package org.code4flex.xmltometaas.parser.handler;


public class CurrentObject {

	
	private Object current = null;
	private CurrentObject parent = null;
	
	public CurrentObject(Object statement){
		this.current = statement;
		
	}
	
	public Object getCurrent(){
		return current;		
	}
	public Object getParent(){
		return  parent.getCurrent();
	}
	public CurrentObject getParentObject(){
		
		return parent;
	}
	public void setParent(CurrentObject parent){
		this.parent = parent;
		
	}
}
