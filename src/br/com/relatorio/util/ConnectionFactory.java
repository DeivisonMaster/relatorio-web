package br.com.relatorio.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private String url = "jdbc:mysql://localhost/bradesco?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "master";
	private String pass = "matos132739";

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
