package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfazTFTP extends JFrame implements ActionListener {
	// InitComponents
	private JMenu jmArchivo;
	private JMenuItem jmiEnviar, jmiRecibir, jmiCreaCuenta, jmiCerrar;
	private JMenuBar jmbArchivo;
	//
	private JLabel jlbNombre, jlbContrasena, jlbServidor, jlbPort;
	private JTextField jtfNombre, jtfContrasena, jtfServidor, jtfPort;
	private JButton jbtnEnviar, jbtnCrear, jbtnImagen;
	private JPanel jpPanel;
	private JFileChooser jfcBuscaImagen;
	private BufferedImage biImagen;
	private FileNameExtensionFilter filter;

	public InterfazTFTP() {
		setTitle("Cliente TFTP");
		setLayout(null);
		setSize(800, 800);
		initComponents();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents() {
		this.jmiEnviar = new JMenuItem("Enviar Archivo");
		this.jmiEnviar.addActionListener(this);
		this.jmiRecibir = new JMenuItem("Recibir Archivo");
		this.jmiRecibir.addActionListener(this);
		this.jmiCreaCuenta = new JMenuItem("Crea Cuenta");
		this.jmiCreaCuenta.addActionListener(this);
		this.jmiCerrar = new JMenuItem("Salir");
		this.jmiCerrar.addActionListener(this);
		this.jmArchivo = new JMenu("Archivo");
		this.jmbArchivo = new JMenuBar();
		this.jmArchivo.add(this.jmiEnviar);
		this.jmArchivo.add(this.jmiRecibir);
		this.jmArchivo.add(this.jmiCreaCuenta);
		this.jmArchivo.add(this.jmiCerrar);
		this.jmbArchivo.add(this.jmArchivo);
		setJMenuBar(this.jmbArchivo);
		this.jpPanel = new JPanel();
		this.jpPanel.setBounds(0, 0, getWidth(), getHeight());
		this.jpPanel.setLayout(null);
		add(this.jpPanel);

	}

	private void enviarLayout() {
		this.jpPanel.removeAll();
		Font font1 = new Font("SansSerif", Font.BOLD, 16);
		// label
		this.jlbNombre = new JLabel("Nombre:");
		this.jlbNombre.setFont(font1);
		this.jlbContrasena = new JLabel("Contraseña:");
		this.jlbContrasena.setFont(font1);
		this.jlbServidor = new JLabel("Servidor:");
		this.jlbServidor.setFont(font1);
		this.jlbPort = new JLabel("Puerto:");
		this.jlbPort.setFont(font1);
		// textfield
		this.jtfNombre = new JTextField();
		this.jtfNombre.setFont(font1);
		this.jtfContrasena = new JTextField();
		this.jtfContrasena.setFont(font1);
		this.jtfServidor = new JTextField();
		this.jtfServidor.setFont(font1);
		this.jtfPort = new JTextField("69");
		this.jtfPort.setFont(font1);
		// Botones
		this.jbtnImagen = new JButton("Selecciona Imagen");
		this.jbtnImagen.setFont(font1);
		this.jbtnImagen.addActionListener(this);
		this.jbtnEnviar = new JButton("Enviar al Servidor");
		this.jbtnEnviar.setFont(font1);
		// bounds
		this.jlbNombre.setBounds(10, 30, 100, 20);
		this.jtfNombre.setBounds(80, 30, 100, 25);// 125
		this.jlbContrasena.setBounds(190, 30, 120, 20);
		this.jtfContrasena.setBounds(290, 30, 120, 25);
		this.jlbServidor.setBounds(430, 30, 120, 20);
		this.jtfServidor.setBounds(505, 30, 120, 25);
		this.jlbPort.setBounds(640, 30, 120, 20);
		this.jtfPort.setBounds(700, 30, 45, 25);
		this.jbtnImagen.setBounds(10, 100, 180, 25);
		this.jbtnEnviar.setBounds(200, 100, 180, 25);
		this.jpPanel.add(this.jlbNombre);
		this.jpPanel.add(this.jtfNombre);
		this.jpPanel.add(this.jlbContrasena);
		this.jpPanel.add(this.jtfContrasena);
		this.jpPanel.add(this.jlbServidor);
		this.jpPanel.add(this.jtfServidor);
		this.jpPanel.add(this.jlbPort);
		this.jpPanel.add(this.jtfPort);
		this.jpPanel.add(this.jbtnImagen);
		this.jpPanel.add(this.jbtnEnviar);
		// filechosser
		this.jfcBuscaImagen = new JFileChooser();
		this.filter = new FileNameExtensionFilter("PNG,JPG,GIF", "png", "jpg", "bmp");
		this.jfcBuscaImagen.setFileFilter(this.filter);
		repaint();
	}

	private void pedirLayout() {
		this.jpPanel.removeAll();
		repaint();
	}

	private void crearLayout() {
		this.jpPanel.removeAll();
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		this.jlbNombre = new JLabel("Nombre:");
		this.jlbNombre.setFont(font1);
		this.jlbContrasena = new JLabel("Contraseña:");
		this.jlbContrasena.setFont(font1);
		this.jtfNombre = new JTextField();
		this.jtfNombre.setFont(font1);
		this.jtfContrasena = new JTextField();
		this.jtfContrasena.setFont(font1);
		this.jbtnCrear = new JButton("Crear Cuenta");
		this.jbtnCrear.setFont(font1);
		// layout
		this.jlbNombre.setBounds(260, 150, 100, 20);
		this.jtfNombre.setBounds(385, 150, 120, 25);
		this.jlbContrasena.setBounds(260, 190, 150, 20);
		this.jtfContrasena.setBounds(385, 190, 120, 25);
		this.jbtnCrear.setBounds(300, 240, 170, 20);
		this.jpPanel.add(this.jlbNombre);
		this.jpPanel.add(this.jtfNombre);
		this.jpPanel.add(this.jlbContrasena);
		this.jpPanel.add(this.jtfContrasena);
		this.jpPanel.add(this.jbtnCrear);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jmiEnviar)) {
			enviarLayout();
		} else if (e.getSource().equals(this.jmiRecibir)) {
			pedirLayout();
		} else if (e.getSource().equals(this.jmiCreaCuenta)) {
			crearLayout();
		} else if (e.getSource().equals(this.jmiCerrar)) {
			dispose();
		} else if (e.getSource().equals(this.jbtnImagen)) {
			int seleccion = this.jfcBuscaImagen.showSaveDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = this.jfcBuscaImagen.getSelectedFile();
				try {
					this.biImagen = ImageIO.read(fichero);
					ImageIcon icon = new ImageIcon(this.biImagen);
					JLabel label = new JLabel(icon);
					label.setBounds(30, 150, 500, 500);
					this.jpPanel.add(label);
					repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
