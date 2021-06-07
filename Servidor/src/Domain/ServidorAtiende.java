package Domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLException;

import Data.ManejarFile;
import Data.UsuarioData;

public class ServidorAtiende extends Thread {

	private int socketPortNumber;
	private DatagramSocket socket;
	DatagramPacket esperaAccion;
	DatagramPacket respuesta;
	UsuarioData usuario;
	ManejarFile file;
	
	public ServidorAtiende(DatagramSocket socket, DatagramPacket esperaAccion) {
		super("Hilo para Cliente");
		this.socket = socket;
		this.esperaAccion = esperaAccion;

	}

	public void run() {
		try {
		System.out.println("Cliente acceptado");
		System.out.println(new String(esperaAccion.getData()));
//		String mensaje = "Recibido";
//		byte[] message = mensaje.getBytes();
//		DatagramPacket respuesta =
//		          new DatagramPacket(message, message.length,
//		        		  esperaAccion.getAddress(), esperaAccion.getPort());
		socket.receive(esperaAccion);
		String cadena=new String(esperaAccion.getData());
		String[] accion = cadena.split(":");   
		System.out.println(cadena);
			if(accion[0]=="Crea") {
				
				usuario.insertUsuario(accion[1], accion[2]);
				file.CrearCarpeta(accion[1]);
				String mensaje = "Usuario Creado";
				byte[] message = mensaje.getBytes();
				 respuesta =new DatagramPacket(message, message.length,esperaAccion.getAddress(), esperaAccion.getPort());
				 socket.send(respuesta);
			}
//			socket.send(respuesta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
