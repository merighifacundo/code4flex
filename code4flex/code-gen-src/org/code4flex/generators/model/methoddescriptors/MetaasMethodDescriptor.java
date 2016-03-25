package org.code4flex.generators.model.methoddescriptors;

import java.io.File;

import org.code4flex.codegenerators.CodeGenerator;


public class MetaasMethodDescriptor extends MethodDescriptor {
	private String methodName;
	private String xmlToMetaasFileName = null;
	private CodeGenerator cg;
	private String sourceDirectory = null;
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getXmlToMetaasFileName() {
		return xmlToMetaasFileName;
	}

	public void setXmlToMetaasFileName(String xmlToMetaas) {
		this.xmlToMetaasFileName = xmlToMetaas;
	}
	public void setInformation(CodeGenerator cg){
		this.cg = cg;
		
		
	}
	
	
	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public File getXmlToMetaas(){
		
		String filePath =  this.cg.getTemplatePath()+ File.separator + this.getSourceDirectory() + File.separator + this.xmlToMetaasFileName;
		return new File(filePath);
	}
	

}
