package Data;

import java.io.File;
public class manejarFile {
	
	public void CrearCarpeta(String nombre)
	{
		File carpeta = new File("/ServidorTFTP/Servidor/"+nombre);
		if(carpeta.exists()) {
			if(carpeta.mkdirs()) {
				System.out.println("Carpeta Creada");
			}
			else
			{
				System.out.println("La carpeta no se pudo crear");
			}
			
		}
		
	}

}
