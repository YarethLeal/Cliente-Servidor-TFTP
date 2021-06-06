package Data;

import java.io.File;

public class ManejarFile {

	public static void manejarFile() {
	}

	public void CrearCarpeta(String nombre) {
		File carpeta = new File("../Servidor/Clientes/" + nombre);
		System.out.println(carpeta.getName());
		if (!carpeta.exists()) {
			if (carpeta.mkdirs()) {
				System.out.println("Carpeta Creada");
			} else {
				System.out.println("La carpeta no se pudo crear");
			}
		}
	}

	public String verArchivos(String nombre) {
		File carpeta = new File("../Servidor/Clientes/" + nombre);
		File[] archivos = null;
		String listaArchivos = "";
		if (carpeta.exists()) {
			
			if (carpeta.isDirectory()) {
				archivos = carpeta.listFiles();
				for (int i = 0; i < archivos.length; i++) {
					listaArchivos += archivos[i].getName() + "\n";
					
				}
			}
		} else {
			System.out.println("No hay archivos o la carpeta no existe");
		}

		return listaArchivos;

	}
//	
//	public void GuardarCarpeta(String nombre) {
//		File carpeta = new File("../Servidor/Clientes/" + nombre);
//		System.out.println(carpeta.getName());
//		if (!carpeta.exists()) {
//			if (carpeta.mkdirs()) {
//				System.out.println("Carpeta Creada");
//			} else {
//				System.out.println("La carpeta no se pudo crear");
//			}
//		}
//	}
	

}
