import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import Data.ManejarFile;
import Domain.Servidor;



public class Main {

	public static void main(String[] args) throws IOException {
		
		ManejarFile prueba= new ManejarFile();
//		prueba.CrearCarpeta("Ara");
		System.out.println(prueba.verArchivos("Ara"));
		
//	        Servidor servidor = new Servidor(6973);
//	        servidor.setVisible(true);
//	        servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        servidor.setLocationRelativeTo(null);
//	        servidor.setResizable(false);

		}

	}

