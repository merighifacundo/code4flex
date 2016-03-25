/*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by KnowledgeIt.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 * 
 * Visit our site: http://knowledgeit.com.ar/code4flex/
 *
 */
package org.code4flex.cgflexintegration.exceptionhandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

/**
 * This is the "wizard controller" it add the pages and finish the work
 * In this place the magic begins: we have two importan methods:
 * generatePhpProject
 * generateFlexProject
 * 
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class ExceptionReport {

	

	public static void reportException(Exception e,boolean showmessage){
		
		 try {
			 
			 //					http://www.knowledgeit.com.ar/code4flex/reporter/exceptionreport.php?exception=prueba24333
			 String messageEncoded = URLEncoder.encode(e.getMessage(),"UTF-8");
			 String _url = "http://www.knowledgeit.com.ar/code4flex/reporter/exceptionreport.php?exception=" + messageEncoded;
			 System.out.println(_url);
			 URL u = new URL (  _url ) ; 
			 HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (  ) ; 
			 huc.setRequestMethod ("PUT") ; 
			 huc.connect() ;
			 if(huc.getResponseCode()== HttpURLConnection.HTTP_OK)
				 System.out.println("Envie error");
			 InputStreamReader reader = new InputStreamReader(huc.getInputStream());
			 //);
			 char target[] = new char[1000];
			 reader.read(target);
			 System.out.println(target);
			 huc.disconnect();
			 if(showmessage){
				 MessageBox msbox = new MessageBox( Display.getCurrent().getActiveShell());
				 msbox.setMessage(e.getClass().getName() + " " + e.getMessage().substring(0, 100));
				 msbox.setText("Exception");
				 msbox.open();
			 }
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	
	
}
