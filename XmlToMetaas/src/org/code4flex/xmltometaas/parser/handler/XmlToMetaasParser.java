package org.code4flex.xmltometaas.parser.handler;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlToMetaasParser extends DefaultHandler {


	

	private TagObject rootTag = null;



	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		
	}

	
	
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		
		
		
	
		
		
	}
	
	

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		if (!Character.isISOControl(ch[0])) {
			String caracteres = new String(ch, start, length);
			System.out.println("car:" + caracteres);
			this.rootTag.setCharacters(caracteres);
		}
		
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, name, attributes);
		if (this.rootTag != null) {
			this.rootTag = new TagObject(this.rootTag);
		} else {

			this.rootTag = new TagObject();
		}
		this.rootTag.setName(name);
		this.rootTag.setAttributes(attributes);
		this.rootTag.setLocalName(localName);
		this.rootTag.setUri(uri);

	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, name);
		if(this.rootTag.getParent()!=null){
			this.rootTag = this.rootTag.getParent();
		}
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		super.endPrefixMapping(prefix);
		System.out.println("prefix" + prefix);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.error(e);
		System.out.println("Exception" + e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.fatalError(e);
		System.out.println("Exception" + e);
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.ignorableWhitespace(ch, start, length);
		String caracteres = new String(ch, start, length);
		System.out.println(caracteres);

	}

	@Override
	public void notationDecl(String name, String publicId, String systemId)
			throws SAXException {
		// TODO Auto-generated method stub
		super.notationDecl(name, publicId, systemId);
		System.out.println("notationDelc" + name + "," + publicId + ","
				+ systemId);
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		super.processingInstruction(target, data);
		System.out.println("processingInstruction" + target + "," + data);
	}

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws IOException, SAXException {
		// TODO Auto-generated method stub
		return super.resolveEntity(publicId, systemId);
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		super.setDocumentLocator(locator);
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		super.skippedEntity(name);
		System.out.println("skippedEntity" + name);
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		super.startPrefixMapping(prefix, uri);
		System.out.println("startPrefixMapping" + uri + "," + prefix);
	}

	@Override
	public void unparsedEntityDecl(String name, String publicId,
			String systemId, String notationName) throws SAXException {
		// TODO Auto-generated method stub
		super.unparsedEntityDecl(name, publicId, systemId, notationName);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.warning(e);
		System.out.println(e.getMessage());
		
	}
	
	public TagObject getRootTag(){
		return this.rootTag;
		
	}
}
