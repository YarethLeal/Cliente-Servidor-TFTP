

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioData extends DataBase
{

public void verificarUsuario(String usuario, String contrasena) throws ClassNotFoundException, SQLException {
	Connection sqlconn= getSQLConnetion();
	Statement stmt= sqlconn.createStatement();
	try {
		String SQL  = "EXEC sp_CONSULTAR '"+usuario+"','"+contrasena+"'";
		ResultSet rs = stmt.executeQuery(SQL);
		System.out.println(rs);
	}catch(Exception e) {
		
		System.out.println("No se pudo realizar la consulta en la BD SQL. "+e.getMessage());
	}
	
}

public boolean insertUsuario(String usuario, String contrasena) throws ClassNotFoundException, SQLException {
	String registrarSQL= "EXEC sp_REGISTRAR ?,?";
	Connection sqlconn= getSQLConnetion();
	
	CallableStatement callablestament =sqlconn.prepareCall(registrarSQL);
	callablestament.setString(1, usuario);
	callablestament.setString(2,contrasena);
	
	try {
		callablestament.execute();
		System.out.println("Se ingreso el usuario satisfatoriamente en la BD SQL");
		
		return true;
	} catch (Exception e) {
		System.out.println("No se pudo insertar el usuario en la BD SQL "+e.getMessage());
		return false;
	}
	
}


}
