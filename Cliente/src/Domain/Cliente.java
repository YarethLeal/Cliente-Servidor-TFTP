package Domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cliente {
	private DatagramSocket socketCliente;
	private byte[] informacion;
	private String mensaje;
	private DatagramPacket paqueteCliente;
	private MosaicoImagen mosaico;

	public Cliente() {

	}

	public int validaUsuario(String nombre, String contrasena) {
		return 0;
	}

	public void envio(String nombre, String server, String port, String urlImagen) {
		try {
			// Socket para conexion a servidor
			this.socketCliente = new DatagramSocket();

			// Direccion del servidor
			InetAddress servidor = InetAddress.getByName(server);

			// Puerto del servidor
			int puerto = Integer.parseInt(port);

			// Mensaje en byte
			this.mensaje = "Accion:Cliente";
			this.informacion = mensaje.getBytes();

			// Envio de Paquete de datos Accion
			this.paqueteCliente = new DatagramPacket(informacion, informacion.length, servidor, puerto);
			this.socketCliente.send(this.paqueteCliente);
			this.mensaje = "Cliente:" + nombre;
			this.informacion = mensaje.getBytes();
			this.paqueteCliente = new DatagramPacket(informacion, informacion.length, servidor, puerto);
			this.socketCliente.send(this.paqueteCliente);
			this.mensaje = "Accion:Guarda";
			this.informacion = mensaje.getBytes();
			this.paqueteCliente = new DatagramPacket(informacion, informacion.length, servidor, puerto);
			this.socketCliente.send(this.paqueteCliente);

			// Envio de Paquete de datos Imagen
			this.mosaico = new MosaicoImagen(urlImagen, 15, 15);
			ArrayList<PiezaImagen> piezasAux = this.mosaico.getImagenPartes();
			Random random = new Random();
			while (!piezasAux.isEmpty()) {
				int rdm = random.nextInt(piezasAux.size());
				PiezaImagen pieza = piezasAux.remove(rdm);
				this.informacion = toByteArray(pieza);
				this.paqueteCliente = new DatagramPacket(informacion, informacion.length, servidor, puerto);
				this.socketCliente.send(this.paqueteCliente);
			}
			/*
			 * for(int i=0;i<this.mosaico.getImagenPartes().size();i++) { this.informacion =
			 * toByteArray(this.mosaico.getImagenPartes().get(i)); this.paqueteCliente = new
			 * DatagramPacket(informacion, informacion.length, servidor, puerto);
			 * this.socketCliente.send(this.paqueteCliente); }
			 */

			// Recibir respuesta del servidor
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			this.socketCliente.receive(respuesta);
			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + new String(respuesta.getData()));

			// Cerrar Socket
			this.socketCliente.close();
			System.out.println("Hecho");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void recibir(String nombre, String server, String port) {
		try {
			// Socket para conexion a servidor
			this.socketCliente = new DatagramSocket();

			// Mensaje en byte
			mensaje = "Yolo baby";
			informacion = mensaje.getBytes();

			// Direccion del servidor
			InetAddress servidor = InetAddress.getByName(server);

			// Puerto del servidor
			int puerto = Integer.parseInt(port);

			// Envio de Paquete de datos
			DatagramPacket archivo = new DatagramPacket(informacion, informacion.length, servidor, puerto);
			this.socketCliente.send(archivo);

			// Recibir respuesta del servidor
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			this.socketCliente.receive(respuesta);
			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + new String(respuesta.getData()));

			// Cerrar Socket
			this.socketCliente.close();
			System.out.println("Hecho");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> listaObjetos(String nombre, String server, String port) {
		try {
			// Socket para conexion a servidor
			this.socketCliente = new DatagramSocket();

			// Direccion del servidor
			InetAddress servidor = InetAddress.getByName(server);

			// Puerto del servidor
			int puerto = Integer.parseInt(port);

			// Mensaje en byte
			this.mensaje = "Accion:Cliente";
			this.informacion = this.mensaje.getBytes();

			// Envio de Paquete de datos
			this.paqueteCliente = new DatagramPacket(this.informacion, this.informacion.length, servidor, puerto);
			this.socketCliente.send(this.paqueteCliente);
			this.mensaje = "Accion:Lista";
			this.informacion = this.mensaje.getBytes();
			this.paqueteCliente = new DatagramPacket(this.informacion, this.informacion.length, servidor, puerto);
			this.socketCliente.send(this.paqueteCliente);

			// Recibir respuesta del servidor
			byte[] bufer = new byte[1000];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			this.socketCliente.receive(respuesta);
			// Enviamos la respuesta del servidor a la salida estandar
			String elementos = new String(respuesta.getData());
			ArrayList<String> lista = new ArrayList<String>();
			lista = (ArrayList<String>) Arrays.asList(elementos.split(","));
			// Cerrar Socket
			this.socketCliente.close();
			return lista;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static byte[] toByteArray(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}

	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		return obj;
	}
}
