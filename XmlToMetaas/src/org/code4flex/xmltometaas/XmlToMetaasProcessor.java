package org.code4flex.xmltometaas;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.code4flex.xmltometaas.converter.XmlToMetaasGenerator;
import org.code4flex.xmltometaas.parser.handler.XmlToMetaasParser;
import org.xml.sax.SAXException;

import uk.co.badgersinfoil.metaas.dom.ASMethod;

public class XmlToMetaasProcessor {

	
	public static void process(ASMethod method, Map root, File xml){
		
		try {
//			StringReader reader = new StringReader(xml);
//			
//			InputSource source = new InputSource(reader);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
 
			XmlToMetaasParser handler = new XmlToMetaasParser();
			parser.parse(xml, handler);
			
			XmlToMetaasGenerator generator = new XmlToMetaasGenerator(method);
   
			
			
			generator.setRoot(root);
			generator.processTag(handler.getRootTag());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
}
