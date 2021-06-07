package Domain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Data.ManejarFile;
import Data.UsuarioData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServidorPrueba extends JFrame implements Runnable {

	private int socketPortNumber = 69;
	private JLabel jlIp;
	private JLabel jlPort;
	private Thread hilo;
	UsuarioData usuario;
	ManejarFile file;
	DatagramPacket respuesta;
	String mensaje;

	public ServidorPrueba(int socketPortNumber) throws IOException {
		super("Server");
		this.setLayout(null);
		this.setSize(300, 300);
		this.socketPortNumber = socketPortNumber;

		this.jlIp = new JLabel();
		this.jlIp.setBounds(50, 50, 500, 100);
		this.add(this.jlIp);

		this.jlPort = new JLabel("Puerto: " + socketPortNumber);
		this.jlPort.setBounds(50, 50, 500, 150);
		this.add(this.jlPort);

		this.hilo = new Thread(this);
		this.hilo.start();
	}

	@Override
	public void run() {

		// DatagramSocket serverSocket;
		try {
			DatagramSocket serverSocket = new DatagramSocket(this.socketPortNumber);
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Servidor ejecutado");
			this.jlIp.setText(String.valueOf(address));

			byte[] bufer = new byte[1000];

			while (true) {
				DatagramPacket esperaAccion = new DatagramPacket(bufer, bufer.length);
				serverSocket.receive(esperaAccion);
				String cadena = new String(esperaAccion.getData());
				String[] accion = cadena.split(":");
				System.out.println(cadena);
				if (accion[1].equals("Server")) {
					serverSocket.receive(esperaAccion);
					cadena = new String(esperaAccion.getData());
					accion = cadena.split(":");
					if (accion[1].equals("Verifica")) {
						serverSocket.receive(esperaAccion);
						cadena = new String(esperaAccion.getData());
						accion = cadena.split(":");
						usuario.verificarUsuario(accion[2], accion[4]);
						String respuestaVerifica = "1";
						byte[] message = respuestaVerifica.getBytes();
						respuesta = new DatagramPacket(message, message.length, esperaAccion.getAddress(),
								esperaAccion.getPort());
						serverSocket.send(respuesta);
					}
				} else if (accion[1].equals("Cliente")) {
					serverSocket.receive(esperaAccion);
					cadena = new String(esperaAccion.getData());
					accion = cadena.split(":");
					File carpeta = new File("../Servidor/Clientes/" + accion[1]);
					if (!carpeta.exists()) {
						file.CrearCarpeta(accion[1]);
					} else {
						serverSocket.receive(esperaAccion);
						cadena = new String(esperaAccion.getData());
						accion = cadena.split(":");
						if (accion[1].equals("Guarda")) {
							ArrayList<PiezaImagen> piezas = new ArrayList<PiezaImagen>();
							for (int i = 0; i < 15; i++) {
								for (int j = 0; j < 15; j++) {
									serverSocket.receive(esperaAccion);
									PiezaImagen pieza = (PiezaImagen) toObject(esperaAccion.getData());
									piezas.add(pieza);
								}
							}
							MosaicoImagen mosaico = new MosaicoImagen();
							mosaico.armaImagen(piezas);
							mensaje = "Armado";
							byte[] message = mensaje.getBytes();
							respuesta = new DatagramPacket(message, message.length, esperaAccion.getAddress(),
									esperaAccion.getPort());
							serverSocket.send(respuesta);
						} else if (accion[1].equals("Recibir")) {

						} else if (accion[1].equals("Lista")) {
							mensaje = file.verArchivos(carpeta.getName());
							byte[] message = mensaje.getBytes();
							respuesta = new DatagramPacket(message, message.length, esperaAccion.getAddress(),
									esperaAccion.getPort());
							serverSocket.send(respuesta);
						}
					}
				}
			}

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
