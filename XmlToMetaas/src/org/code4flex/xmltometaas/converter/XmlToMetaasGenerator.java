package org.code4flex.xmltometaas.converter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.code4flex.xmltometaas.parser.handler.CurrentObject;
import org.code4flex.xmltometaas.parser.handler.ElementType;
import org.code4flex.xmltometaas.parser.handler.ProcessorAS;
import org.code4flex.xmltometaas.parser.handler.TagObject;

import uk.co.badgersinfoil.metaas.dom.ASIfStatement;
import uk.co.badgersinfoil.metaas.dom.Statement;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class XmlToMetaasGenerator {

	private CurrentObject currentObject = null;
	@SuppressWarnings("unchecked")
	private Map root;

	public XmlToMetaasGenerator(Object curObj) {
		this.currentObject = new CurrentObject(curObj);

	}

	@SuppressWarnings("unchecked")
	public void setRoot(Map root) {
		this.root = root;

	}

	public void processTag(TagObject myCurrentTag) {
		boolean processValueTemplate = false;

		if ("true".equalsIgnoreCase(myCurrentTag
				.getAttribute("processTemplate"))) {
			processValueTemplate = true;

		}

		if (ElementType.METHOD_BODY.equalsIgnoreCase(myCurrentTag.getName())) {
			for (TagObject tagToProcess : myCurrentTag.getChildrens()) {
				myCurrentTag = tagToProcess;
				processTag(myCurrentTag);
			}
			return;
		}
		if (ElementType.IF_STATEMENT.equalsIgnoreCase(myCurrentTag.getName())) {

			String value = null;
			if (myCurrentTag.getAttribute("value") != null) {
				value = myCurrentTag.getAttribute("value");
			} else {
				if (!myCurrentTag.getChildrensByName("value").isEmpty())
					value = myCurrentTag.getChildrensByName("value").get(0)
							.getCharacters();

			}
			if (processValueTemplate) {

				value = processValue(value);

			}
			ASIfStatement current = ProcessorAS.processIf(value, currentObject
					.getCurrent());
			CurrentObject currentObj = new CurrentObject(current);
			currentObj.setParent(this.currentObject);
			this.currentObject = currentObj;
			boolean hasElse = false;
			for (TagObject tagToProcess : myCurrentTag.getChildrens()) {
				myCurrentTag = tagToProcess;
				if (!hasElse
						&& ElementType.ELSE.equalsIgnoreCase(tagToProcess
								.getName())) {
					hasElse = true;
				}

				processTag(myCurrentTag);
			}

			if (this.currentObject.getParentObject() != null) {
				if (hasElse) {
					// Me salto un nivel mas-> else -> if ->
					this.currentObject = this.currentObject.getParentObject();
				}
				this.currentObject = this.currentObject.getParentObject();
			}
			return;
		}
		if (ElementType.ELSE.equalsIgnoreCase(myCurrentTag.getName())) {

			String value = null;
			if (myCurrentTag.getAttribute("value") != null) {
				value = myCurrentTag.getAttribute("value");
			} else {
				if (!myCurrentTag.getChildrensByName("value").isEmpty())
					value = myCurrentTag.getChildrensByName("value").get(0)
							.getCharacters();

			}
			if (processValueTemplate) {

				value = processValue(value);

			}
			Statement current = ProcessorAS.processElseIf(this.currentObject
					.getCurrent());
			CurrentObject currentObj = new CurrentObject(current);
			currentObj.setParent(this.currentObject);
			this.currentObject = currentObj;
			for (TagObject tagToProcess : myCurrentTag.getChildrens()) {
				myCurrentTag = tagToProcess;
				processTag(myCurrentTag);
			}

			return;
		}

		if (ElementType.STATEMENT.equalsIgnoreCase(myCurrentTag.getName())) {

			String value = null;
			if (myCurrentTag.getAttribute("value") != null) {
				value = myCurrentTag.getAttribute("value");
			} else {

				if (!myCurrentTag.getChildrensByName("value").isEmpty())
					value = myCurrentTag.getChildrensByName("value").get(0)
							.getCharacters();

			}
			if (processValueTemplate) {

				value = processValue(value);

			}
			Statement current = ProcessorAS.processStatement(value,
					this.currentObject.getCurrent());
			CurrentObject currentObj = new CurrentObject(current);
			currentObj.setParent(this.currentObject);
			this.currentObject = currentObj;
			for (TagObject tagToProcess : myCurrentTag.getChildrens()) {
				myCurrentTag = tagToProcess;
				processTag(myCurrentTag);
			}
			if (this.currentObject.getParentObject() != null)
				this.currentObject = this.currentObject.getParentObject();
			return;
		}

	}

	@SuppressWarnings("unchecked")
	private String processValue(String value) {
		try {
			Configuration conf = new Configuration();
			
			// Build the data-model
			Map root = new HashMap();
			root.put("message", "Hello World!");

			// Get the templat object
			//Template t = conf.getTemplate("test.ftl");
			
			Template t = new Template("prueba",new StringReader(value),conf);
			// Prepare the HTTP response:
			// - Use the charset of template for the output
			// - Use text/html MIME-type

			Writer out = new StringWriter();

			// Merge the data-model and the template

			t.process(this.root, out);
			return out.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
