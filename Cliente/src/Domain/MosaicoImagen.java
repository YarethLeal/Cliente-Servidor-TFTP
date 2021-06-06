package Domain;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MosaicoImagen {
	private BufferedImage imagenPrincipal;
	private ArrayList<PiezaImagen> imagenPartes = new ArrayList();
	private int width, height;

	public MosaicoImagen(String ruta) {
		try {
			this.imagenPrincipal = ImageIO.read(new File(ruta));
			this.width = imagenPrincipal.getWidth(null);
			this.height = imagenPrincipal.getHeight(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dividirImagen(int filas, int columnas) {
		int id = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Image tempImage = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(
						j * (width / columnas), i * (height / filas), width / columnas, height / filas)));
				Image imagen = tempImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				PiezaImagen pieza = new PiezaImagen(imagen, id);
				imagenPartes.add(pieza);
				id++;
			}
		}
	}
}
