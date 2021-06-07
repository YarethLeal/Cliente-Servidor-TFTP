import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Data.ManejarFile;
import Domain.Servidor;



public class Main {

	public static void main(String[] args) throws IOException {
		

	        Servidor servidor = new Servidor(69);
	        servidor.setVisible(true);
	        servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        servidor.setLocationRelativeTo(null);
	        servidor.setResizable(false);

		}

	}

