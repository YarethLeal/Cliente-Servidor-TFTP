package Domain;

import java.awt.Image;

public class PiezaImagen {
	private Image pieza;
	private int id;
	public PiezaImagen() {
		this.pieza = null;
		this.id = 0;
	}
	public PiezaImagen(Image img,int num) {
		this.pieza = img;
		this.id = num;
	}
}
