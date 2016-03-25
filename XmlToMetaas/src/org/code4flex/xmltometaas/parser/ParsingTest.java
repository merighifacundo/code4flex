package org.code4flex.xmltometaas.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.code4flex.xmltometaas.converter.XmlToMetaasGenerator;
import org.code4flex.xmltometaas.parser.handler.XmlToMetaasParser;
import org.xml.sax.SAXException;

import uk.co.badgersinfoil.metaas.ActionScriptFactory;
import uk.co.badgersinfoil.metaas.ActionScriptProject;
import uk.co.badgersinfoil.metaas.dom.ASClassType;
import uk.co.badgersinfoil.metaas.dom.ASCompilationUnit;
import uk.co.badgersinfoil.metaas.dom.ASMethod;
import uk.co.badgersinfoil.metaas.dom.Visibility;


public class ParsingTest {

	
	
	/*private static String prueba = "<?xml version=\"1.0\" encoding =\"utf-8\"?>"+
					"<bodyMethod>"+
					"<ifStatement value=\"e.result\">" + 
					"</ifStatement>"+
					"</bodyMethod>";*/
					
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		  

		//String xmlFile = "file:///xerces-2_9_1/data/personal.xml"; 
		ActionScriptFactory asFactory = new ActionScriptFactory();
		ActionScriptProject metaasProject = asFactory.newEmptyASProject("c:/pruebaParser/project/src");
		ASCompilationUnit unit = metaasProject.newClass("ar.com.prueba.Test");
		ASClassType type = (ASClassType) unit.getType();
		try {
			FileInputStream file = new FileInputStream("c:/pruebaParser/metaasToParse.xml");
			//StringReader reader = new StringReader(prueba);
			
			//InputSource source = new InputSource(reader);
			SAXParserFactory factory = SAXParserFactory.newInstance();
		    factory.setNamespaceAware(true);
		    SAXParser parser = factory.newSAXParser();
		    ASMethod method = type.newMethod("chupala", Visibility.PUBLIC, "void");
		    XmlToMetaasParser handler = new XmlToMetaasParser();
		    parser.parse(file, handler);
		    
		    XmlToMetaasGenerator generator = new XmlToMetaasGenerator(method);
		    Map root = new HashMap();
			root.put("message", "4 == 5");
		    
		    generator.setRoot(root);
		    generator.processTag(handler.getRootTag());
		    
		    metaasProject.writeAll();
		} catch (FactoryConfigurationError e) {
		    e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
