package org.code4flex.xmltometaas.parser.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;

public class TagObject {
	private TagObject parent = null;
	
	private String name;
	private Map<String,String> attributes = new HashMap<String, String>();
	
	private String localName;
	private String characters;
	private List<TagObject> childrens = new ArrayList<TagObject>();//<TabObject>();

	private String uri;
	
	
	public TagObject(TagObject parent) {
		this.parent = parent;
		parent.addChild(this);

	}

	public TagObject() {
		this.parent = null;

	}

	private void addChild(TagObject tagObject) {
		childrens.add( tagObject);

	}

	public void setName(String name) {
		this.name = name;

	}

	public void setAttributes(Attributes att) {
		for(int i=0;i<att.getLength();i++){
			attributes.put(att.getQName(i),att.getValue(i));
			
		}

	}

	public void setLocalName(String localName) {
		this.localName = localName;

	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public TagObject getParent() {
		return parent;
	}

	
	public String getName() {
		return name;
	}

	public String getAttribute(String key) {
		return attributes.get(key);
	}

	public String getLocalName() {
		return localName;
	}

	public String getCharacters() {
		return characters;
	}

	public void setUri(String uri) {
		this.uri = uri;
		
	}
	
	public List<TagObject> getChildrens() {
		return childrens;
	}
	
	
	public String getUri() {
		return uri;
	}

	public List<TagObject> getChildrensByName(String name){
		List<TagObject> search = new ArrayList<TagObject>();
		for(TagObject  tag : this.childrens){
			if(name.equalsIgnoreCase(tag.getName()))
				search.add(tag);
		}
		return search;
	}


}
