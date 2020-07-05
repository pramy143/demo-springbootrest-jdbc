/*
package com.example;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class ExtractAlsTestData {
	//jdbc:mysql://localhost:3306/payments_hub?useSSL=false
	public static void main(String[] args) throws Exception {
		// database connection
		//Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/payments_hub?useSSL=false", "payment", "payment");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// add the table ALS_PUBLISHERS for data extraction
		ITable tbl = connection.createQueryTable("payments",
												 "SELECT * FROM payments ");
		// exclude column LOGO from the data extraction
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(tbl,
																		new String[] { "LOGO" });
		// create a dataset with table ALS_PUBLISHERS
		DefaultDataSet partialDataSet = new DefaultDataSet();
		partialDataSet.addTable(filteredTable);
		// have DBUnit write the table data to an XML file
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream(
				"c:/payments-dataset.xml"));
	}
}*/
