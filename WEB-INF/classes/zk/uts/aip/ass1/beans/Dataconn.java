package zk.uts.aip.ass1.beans;

import java.io.Serializable;
import java.sql.*;

public class Dataconn implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String dbDriver = "oracle.jdbc.driver.OracleDriver";

	private static final String dbURL = "jdbc:oracle:thin:@smaug.it.uts.edu.au:1522:ell";

	private String dbUser = "kzhan";
	private String dbPass = "suydgn3b";
	private Connection conn;

	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(dbDriver);
		this.conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
		return conn;
	}

	public void close() throws SQLException {
		this.conn.close();
	}

	public ResultSet query(String sql) throws SQLException {
		Statement s = conn.createStatement();
		return (s.executeQuery(sql));
	}

	public int update(String sql) throws SQLException {
		Statement s = conn.createStatement();
		return (s.executeUpdate(sql));
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
