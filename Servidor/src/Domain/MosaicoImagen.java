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
	private ArrayList<PiezaImagen> imagenPartes = new ArrayList<PiezaImagen>();
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
	
	public MosaicoImagen() {
		
	}
	public MosaicoImagen(String ruta,int filas,int columnas) {
		try {
			this.imagenPrincipal = ImageIO.read(new File(ruta));
			this.width = imagenPrincipal.getWidth(null);
			this.height = imagenPrincipal.getHeight(null);
			dividirImagen(filas, columnas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dividirImagen(int filas, int columnas) {
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
	
	public void armaImagen(ArrayList<PiezaImagen> piezas) throws IOException {
		
		int cantPartes = piezas.size();
		int type, width,height;
		BufferedImage[] imagenes = new BufferedImage[cantPartes];
		
		for (int i = 0; i < cantPartes; i++) {
			for (int j = 0; j < cantPartes; j++) {
				if(piezas.get(j).getId()==i) {
					imagenes[i] =(BufferedImage)piezas.get(j).getPieza();
				}
			}
            
        }
		
		type= imagenes[0].getType();
		width= imagenes[0].getWidth();
		height= imagenes[0].getHeight();
		
		BufferedImage imagenCompleta = new BufferedImage(width*15, height*15, type);
		
		  int num = 0;
	        for (int i = 0; i < 15; i++) {
	            for (int j = 0; j < 15; j++) {
	            	imagenCompleta.createGraphics().drawImage(imagenes[num], width * j, height * i, null);
	                num++;
	            }
	        }
	        
	        ImageIO.write(imagenCompleta, "jpeg", new File("imagenCompleta.jpg"));
		
	}

	public BufferedImage getImagenPrincipal() {
		return imagenPrincipal;
	}

	public void setImagenPrincipal(BufferedImage imagenPrincipal) {
		this.imagenPrincipal = imagenPrincipal;
	}

	public ArrayList<PiezaImagen> getImagenPartes() {
		return imagenPartes;
	}

	public void setImagenPartes(ArrayList<PiezaImagen> imagenPartes) {
		this.imagenPartes = imagenPartes;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
