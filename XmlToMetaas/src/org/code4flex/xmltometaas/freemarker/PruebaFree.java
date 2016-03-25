package org.code4flex.xmltometaas.freemarker;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class PruebaFree {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			conf.setDirectoryForTemplateLoading(new File("c:/pruebaParser/templates/"));
			// Build the data-model
			Map root = new HashMap();
			root.put("message", "Hello World!");

			// Get the templat object
			//Template t = conf.getTemplate("test.ftl");
			
			Template t = new Template("prueba",new StringReader("Facux: ${message}"),conf);
			// Prepare the HTTP response:
			// - Use the charset of template for the output
			// - Use text/html MIME-type

			Writer out = new StringWriter();

			// Merge the data-model and the template

			t.process(root, out);
			System.out.println(out.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
