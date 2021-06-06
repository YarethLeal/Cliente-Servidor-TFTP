package Domain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame implements Runnable {

	private int socketPortNumber = 69;
	private JLabel jlIp;
	private JLabel jlPort;
	private Thread hilo;

	public Servidor(int socketPortNumber) throws IOException {
		super("Server");
		this.setLayout(null);
		this.setSize(300, 300);
		this.socketPortNumber = socketPortNumber;

		this.jlIp = new JLabel();
		this.jlIp.setBounds(50, 50, 500, 100);
		this.add(this.jlIp);
		
		this.jlPort = new JLabel("Puerto: "+socketPortNumber);
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
				// Socket socket = serverSocket.accept();
				System.out.println("Acepte el cliente");
				serverSocket.receive(esperaAccion);
				System.out.print("Datagrama recibido del host: " + esperaAccion.getAddress());
				System.out.println(" desde el puerto remoto: " + esperaAccion.getPort());
				System.out.println("Asado");
				ServidorAtiende hiloServer = new ServidorAtiende(serverSocket, esperaAccion);
			    hiloServer.start();
				System.out.println("Mensaje de peticion:" + esperaAccion.getData().toString());
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
