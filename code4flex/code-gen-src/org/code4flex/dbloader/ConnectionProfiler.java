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

package org.code4flex.dbloader;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.code4flex.cgflexintegration.exceptionhandler.ExceptionReport;
import org.code4flex.cgflexintegration.model.PluginModel;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.2 $
 */

public class ConnectionProfiler {

	private static PluginModel model = PluginModel.getInstance();

	private static Exception lastException = null;

	public static Connection getModelConnection() {

		try {

			Driver c = (Driver) Class.forName(
					model.getConnectionInformation().getDriver(), true,
					ConnectionProfiler.class.getClassLoader()).newInstance();
			DriverManager.registerDriver(new DriverWrapper(c));
			Connection con = DriverManager.getConnection(model
					.getConnectionInformation().getUrl());
			con.setAutoCommit(false);
			con.setReadOnly(true);
			return con;
		} catch (InstantiationException e) {
			ExceptionReport.reportException(e, false);
			e.printStackTrace();
			lastException = e;
		} catch (IllegalAccessException e) {
			ExceptionReport.reportException(e, false);
			e.printStackTrace();
			lastException = e;
		} catch (ClassNotFoundException e) {
			ExceptionReport.reportException(e, false);
			e.printStackTrace();
			lastException = e;
		} catch (SQLException e) {
			ExceptionReport.reportException(e, false);
			e.printStackTrace();
			lastException = e;
		}
		return null;

	}

	public static Exception getLastException(){
		
		return lastException;
		
	}
	
	
}
