package Domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorAtiende extends Thread{


	private int socketPortNumber;
	private DatagramSocket socket;
	DatagramPacket esperaAccion;
	
	public ServidorAtiende(DatagramSocket socket,DatagramPacket esperaAccion) {
		super("Hilo para Cliente");
		this.socket = socket;
		this.esperaAccion=esperaAccion;
		
	}
	
	public void run() {
		try {
		System.out.println("Cliente acceptado");
		DatagramPacket respuesta =
		          new DatagramPacket(esperaAccion.getData(), esperaAccion.getLength(),
		        		  esperaAccion.getAddress(), esperaAccion.getPort());

		        // Enviamos la respuesta, que es un eco
		 
			socket.send(respuesta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
