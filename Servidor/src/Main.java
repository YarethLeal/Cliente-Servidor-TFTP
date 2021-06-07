import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Data.ManejarFile;
import Data.UsuarioData;
import Domain.Servidor;
import Domain.ServidorPrueba;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

//	        Servidor servidor = new Servidor(69);
//	        servidor.setVisible(true);
//	        servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        servidor.setLocationRelativeTo(null);
//	        servidor.setResizable(false);
//	        

		ServidorPrueba servidor = new ServidorPrueba(69);
		servidor.setVisible(true);
		servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		servidor.setLocationRelativeTo(null);
		servidor.setResizable(false);

	}

}
