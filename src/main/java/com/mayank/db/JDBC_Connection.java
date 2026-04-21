package com.mayank.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection {

	private static Connection conn;

	public static Connection getConnection() {

		String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
		String username = "sys as sysdba";
		String password = "oracle7814";

		try {
			if (conn == null)
				conn = DriverManager.getConnection(url, username, password);

			System.out.println("Database connection success");

			return conn;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return conn;
		}

	}

}


