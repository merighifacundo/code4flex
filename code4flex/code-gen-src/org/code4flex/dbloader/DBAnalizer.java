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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.code4flex.dbloader.model.DBField;
import org.code4flex.dbloader.model.DBTable;


/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class DBAnalizer {

	/*
	 * # TABLE_CAT String => table catalog (may be null) # TABLE_SCHEM String =>
	 * table schema (may be null) # TABLE_NAME String => table name #
	 * COLUMN_NAME String => column name # DATA_TYPE short => SQL type from
	 * java.sql.Types # TYPE_NAME String => Data source dependent type name, for
	 * a UDT the type name is fully qualified # COLUMN_SIZE int => column size.
	 * For char or date types this is the maximum number of characters, for
	 * numeric or decimal types this is precision. # BUFFER_LENGTH is not used.
	 * # DECIMAL_DIGITS int => the number of fractional digits # NUM_PREC_RADIX
	 * int => Radix (typically either 10 or 2) # NULLABLE int => is NULL
	 * allowed?
	 */

	private List<DBTable> model = null;

	public void analizeConnection(Connection conn) throws SQLException {
		List<String> primaryKeys = new ArrayList<String>();
		List<DBTable> results = new ArrayList<DBTable>();
		DatabaseMetaData metadata = conn.getMetaData();
		ResultSet rs = metadata.getTables(null, null, null, null);
		while (rs.next()) {
			List<DBField> fields = new ArrayList<DBField>();
			List<DBField> fieldsKey = new ArrayList<DBField>();
			String tableName = rs.getString("TABLE_NAME");
			String tableSchema = rs.getString("TABLE_SCHEM");
			String catalog = rs.getString("TABLE_CAT");
			System.out.println(tableName);
			if ( !tableName.startsWith("BIN$")) {
				DBTable table = new DBTable();
				table.setTableName(tableName);
				table.setTableCat(catalog);
				table.setTableSchem(tableSchema);
				ResultSet primaryKey = metadata.getPrimaryKeys(null,
						tableSchema, tableName);
				while (primaryKey.next()) {
					String columnNameKey = primaryKey.getString("COLUMN_NAME");
					primaryKeys.add(columnNameKey);
					System.out.println(columnNameKey);

				}
				try {
					primaryKey.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				ResultSet rsPija = metadata.getColumns(null, null, rs
						.getString("TABLE_NAME"), null);
				while (rsPija.next()) {
					DBField field = new DBField();

					field.setColumnName(rsPija.getString("COLUMN_NAME"));
					field.setDataType(rsPija.getShort("DATA_TYPE"));
					field.setTypeName(rsPija.getString("TYPE_NAME"));
					if (primaryKeys.contains(field.getColumnName())) {
						field.setPrimaryKey(true);
						fieldsKey.add(field);
					}
					fields.add(field);
				}
				try{
					rsPija.close();
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				table.setFields(fields);
				table.setFieldsKey(fieldsKey);
				;
				results.add(table);
			}
		}
		try{
			rs.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.model = results;

	}

	public List<DBTable> getModel() {
		return model;
	}

	public void setModel(List<DBTable> model) {
		this.model = model;
	}

}
