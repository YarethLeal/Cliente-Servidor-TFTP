package Domain;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
	public Cliente() {

	}

	public int validaUsuario(String nombre, String contrasena) {
		return 0;
	}

	public void envio(String nombre, String server, String port) {
		try {
			// Socket para conexion a servidor
			DatagramSocket socket = new DatagramSocket();
			// Mensaje en byte
			String mensaje = "Yolo baby";
			byte[] message = mensaje.getBytes();
			// Direccion del servidor
			InetAddress servidor = InetAddress.getByName(server);
			System.out.println(servidor.getHostAddress());
			// Puerto del servidor
			int puerto = Integer.parseInt(port);
			// Paquete de datos
			DatagramPacket archivo = new DatagramPacket(message, message.length, servidor, puerto);
			socket.send(archivo);
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			socket.receive(respuesta);
			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + new String(respuesta.getData()));
			socket.close();
			System.out.println("Hecho");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void recibir(String nombre, String server, String port) {
		try {

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
