package Domain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Data.ManejarFile;
import Data.UsuarioData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;

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
				System.out.println("Cliente aceptado");
				String cadena = new String(esperaAccion.getData());
				String[] accion = cadena.split(":");
				System.out.println(cadena+accion[0]);
				if (accion[0].equals("Crea")) {
					System.out.println(accion[0] + accion[1] + accion[3]);
					usuario.insertUsuario(accion[1], accion[2]);
					file.CrearCarpeta(accion[1]);
					mensaje = "Usuario Creado";
					byte[] message = mensaje.getBytes();
					respuesta = new DatagramPacket(message, message.length, esperaAccion.getAddress(),
							esperaAccion.getPort());
					serverSocket.send(respuesta);

				}

				else if (accion[0] == "Verifica") {
					usuario.verificarUsuario(accion[1], accion[2]);

				} else if (accion[0] == "Guarda") {

				}
				else if (accion[0] == "Recibir") {

				}
				else if (accion[0] == "Lista") {
					mensaje=file.verArchivos(accion[1]);
					byte[] message = mensaje.getBytes();
					respuesta = new DatagramPacket(message, message.length, esperaAccion.getAddress(),
							esperaAccion.getPort());
					serverSocket.send(respuesta);

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

}
