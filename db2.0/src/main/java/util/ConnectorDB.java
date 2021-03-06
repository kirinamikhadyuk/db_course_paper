package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectorDB {

	private static ConnectorDB connectorDB;

	private Connection jdbcConnection;

	public static ConnectorDB getConnectorDBInstance() {

		if (connectorDB == null) {
			connectorDB = new ConnectorDB();
		}
		return connectorDB;
	}

	public Connection getConnection() {

		if (jdbcConnection == null) {
			initConnection();
		}
		return jdbcConnection;
	}

	private void initConnection() {

		Properties properties = getDataBaseProperties();

		String driverName = properties.getProperty("db.jdbc_driver_name");
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");

		try {
			Class.forName(driverName);
			jdbcConnection = DriverManager.getConnection(url, user, password);

			jdbcConnection.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void closeConnection() {

		try {

			jdbcConnection.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private Properties getDataBaseProperties() {

		Properties properties = new Properties();

		try (FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Downloads\\db\\src\\main\\resources\\jdbc.properties")) {

			properties.load(fis);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return properties;
	}
}
