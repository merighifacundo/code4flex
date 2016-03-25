package org.code4flex.codegenerators.velocity.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.EscapeTool;
import org.code4flex.codegenerators.velocity.StringTemplate;
import org.code4flex.generators.model.methods.AbstractMethod;

public class MethodCodeGenerator {

	
	
	public static String generateMethodCode(AbstractMethod method,String stringTemplate) {
		
		String methodCode;
		try {

			

			StringTemplate template = new StringTemplate(stringTemplate);
			template.setName(method.getMethodName() + "Template");

			template.process();

			StringWriter writer = new StringWriter();
			VelocityContext context = new VelocityContext();
			context.put("method", method);
			context.put("esc", new EscapeTool());
			template.merge(context, writer);
			
			methodCode = writer.toString();
			return methodCode;
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		//this.methodCode = "";
	}
	
	
	
}
