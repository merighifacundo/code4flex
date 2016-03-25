package org.code4flex.xmltometaas.parser.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import uk.co.badgersinfoil.metaas.dom.ASIfStatement;
import uk.co.badgersinfoil.metaas.dom.Statement;

public class ProcessorAS {

	public static ASIfStatement processIf(String expression,Object cont){
		Method newIfMethod;
		try {
			newIfMethod = cont.getClass().getMethod("newIf",new Class[] { String.class });
			return (ASIfStatement) newIfMethod.invoke(cont, new Object[]{expression});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}

	public static Statement processStatement(String value, Object current) {
		Method addStamtMethod;
		try {
			addStamtMethod = current.getClass().getMethod("addStmt",new Class[] { String.class });
			return (Statement) addStamtMethod.invoke(current, new Object[]{value});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public static Statement processElseIf(Object current){
		
		Method elseBlockMethod;
		try {
			elseBlockMethod = current.getClass().getMethod("elseBlock",new Class[] {});
			return (Statement) elseBlockMethod.invoke(current, new Object[]{});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
}
