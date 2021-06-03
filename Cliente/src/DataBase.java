

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DataBase {
	
	private final String drivername ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String sql_url = "jdbc:sqlserver://localhost:1433;databaseName=Proyecto_Redes;user=sa;password=1234";

	public Connection getSQLConnetion() throws ClassNotFoundException, SQLException {
		   Connection conn =DriverManager.getConnection(sql_url);
		   
		   return conn;
		   
	   }
}
