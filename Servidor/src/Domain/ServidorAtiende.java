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
		System.out.print("Datagrama recibido del host: " + esperaAccion.getAddress());
		System.out.println(" desde el puerto remoto: " + esperaAccion.getPort());
		System.out.println(new String(esperaAccion.getData()));
		String mensaje = "Recibido";
		byte[] message = mensaje.getBytes();
		DatagramPacket respuesta =
		          new DatagramPacket(message, message.length,
		        		  esperaAccion.getAddress(), esperaAccion.getPort());

		        // Enviamos la respuesta, que es un eco
		 
			socket.send(respuesta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
