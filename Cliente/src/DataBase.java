

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DataBase {
	
	private final String drivername ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private String sql_url = "jdbc:sqlserver://localhost:1433;databaseName=Proyecto_Redes;user=sa;password=1234";
	private String sql_url = "jdbc:sqlserver://localhost:163.178.107.10,1433;databaseName=B87107_B84211_Redes;user=laboratorios;password=KmZpo.2796";

	public Connection getSQLConnetion() throws ClassNotFoundException, SQLException {
		   Connection conn =DriverManager.getConnection(sql_url);
		   
		   return conn;
		   
	   }
}
