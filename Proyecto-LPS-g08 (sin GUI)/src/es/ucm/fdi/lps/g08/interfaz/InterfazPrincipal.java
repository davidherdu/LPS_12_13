package es.ucm.fdi.lps.g08.interfaz;

import es.ucm.fdi.lps.g08.*;
import java.awt.EventQueue;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelTablero panelTablero;
	private PanelInferior panelInferior;
	private PanelDerecho panelDerecho;
	private JPanel panelSuperior;
	
	private Image imgMapa = (Toolkit.getDefaultToolkit()).getImage("img/mapa.png");
	private Image imgScriptorium = (Toolkit.getDefaultToolkit()).getImage("img/MazoScriptorium.png");
	private Image imgBiblioteca = (Toolkit.getDefaultToolkit()).getImage("img/MazoBiblioteca.png");
	private Image imgEvento = (Toolkit.getDefaultToolkit()).getImage("img/MazoEvento.png");

	
	private JMenuBar menuBar;
	private JMenu mArchivo;
	private JMenu mEditar;
	private JMenu mOpciones;
	private JMenu mAyuda;
	private JMenuItem mnRealizarMov,mnMostrarConfe,mnVerTodosMonjes,mnVerRevelacion,mnVerMonjesTachados,mnVerHojaRasgos,mnVerTusCartas;
	private JMenuItem mnAbout,mnSalir,mnAbrir,mnGuardarComo;
	
	JButton btnScriptorium,btnNewButton,btnEventos;
	
	

	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal frame = new InterfazPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public InterfazPrincipal() {
		
		//titulo de la ventana
		super("El Misterio de la Abadia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//No permite Maximizar la ventana
		setResizable(false);
	
		
		setBounds(100, 100, 696, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		panelTablero = new PanelTablero(imgMapa);
		
		//PanelTablero.panelSuperior;
		panelTablero.setBounds(10, 31, 500, 500);
		contentPane.add(panelTablero);
		panelTablero.setLayout(null);
		
		
		//Panel Derecho.Mazos
		panelDerecho = new PanelDerecho();
		panelDerecho.setBounds(527, 31, 143, 500);
		contentPane.add(panelDerecho);
		panelDerecho.setLayout(null);
		
		
		ImageIcon iconS= new ImageIcon(imgScriptorium.getScaledInstance(143,97,0));
		btnScriptorium = new JButton(iconS);
		btnScriptorium.setBounds(0, 100, 143, 97);
		panelDerecho.add(btnScriptorium);
		
		ImageIcon iconB= new ImageIcon(imgBiblioteca.getScaledInstance(143,97,0));
		btnNewButton = new JButton(iconB);
		btnNewButton.setBounds(0, 208, 143, 97);
		panelDerecho.add(btnNewButton);
		
		ImageIcon iconE= new ImageIcon(imgEvento.getScaledInstance(143,97,0));
		btnEventos = new JButton(iconE);
		btnEventos.setBounds(0, 316, 143, 97);
		panelDerecho.add(btnEventos);
		
		JLabel lblMazos = new JLabel("MAZOS");
		lblMazos.setForeground(Color.RED);
		lblMazos.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblMazos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMazos.setBounds(0, 0, 143, 68);
		panelDerecho.add(lblMazos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 542, 660, 134);
		contentPane.add(scrollPane);
		
		//JButton btnNewButton = new JButton("New button");
		//panelDerecho.add(btnNewButton);
		
		//Panel Inferior.Cartas del jugador
		panelInferior = new PanelInferior();
		scrollPane.setViewportView(panelInferior);
		panelInferior.setLayout(null);
		
		
		
		//PanelSuperior.MENU
		panelSuperior = new JPanel();
		panelSuperior.setBounds(10, 0, 680, 25);
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 674, 25);
		menuBar.setBorderPainted(false);
		panelSuperior.add(menuBar);
		
		//Menu Archivo
		mArchivo = new JMenu("Archivo");
		menuBar.add(mArchivo);

		
		mnAbrir = new JMenuItem("Abrir");
		mArchivo.add(mnAbrir);
		
		mnGuardarComo = new JMenuItem("Guardar Como");
		mArchivo.add(mnGuardarComo);
		
		mnSalir = new JMenuItem("Salir");
		mArchivo.add(mnSalir);
		
		//Menu Editar
		mEditar = new JMenu("Editar");
		menuBar.add(mEditar);
		
		
		JMenuItem mnCortar = new JMenuItem("Cortar");
		mEditar.add(mnCortar);
		
		JMenuItem mnCopiar = new JMenuItem("Copiar");
		mEditar.add(mnCopiar);
		
		JMenuItem mnPegar = new JMenuItem("Pegar");
		mEditar.add(mnPegar);
		
		
		//Menu Opciones
		mOpciones = new JMenu("Opciones");
		menuBar.add(mOpciones);
		
		
		
		mnVerTusCartas = new JMenuItem("1. Ver tus cartas de Sospechoso");
		mOpciones.add(mnVerTusCartas);
		
		mnVerHojaRasgos = new JMenuItem("2. Ver tu hoja de rasgos");
		mOpciones.add(mnVerHojaRasgos);
		
		mnVerMonjesTachados = new JMenuItem("3. Ver los monjes que tienes tachados");
		mOpciones.add(mnVerMonjesTachados);
		
		mnVerRevelacion = new JMenuItem("4. Ver revelaciones y acusaciones");
		mOpciones.add(mnVerRevelacion);
		
		mnVerTodosMonjes = new JMenuItem("5. Ver todos los monjes");
		mOpciones.add(mnVerTodosMonjes);
		
		mnRealizarMov = new JMenuItem("6. Realizar un movimiento");
		mOpciones.add(mnRealizarMov);
		
		
		mnMostrarConfe = new JMenuItem("7. Mostrar ultimos en confesarse");
		mOpciones.add(mnMostrarConfe);
		
		
		mAyuda = new JMenu("Ayuda");
		menuBar.add(mAyuda);
		
		mnAbout = new JMenuItem("About");
		mAyuda.add(mnAbout);
	
		
		

		
		
		 ActionListener al = new ActionListener() { 
	         public void actionPerformed(ActionEvent e){ 
	            Object obj = e.getSource(); 
	            if (obj == mnRealizarMov) 
	               btnRealizaMovActionPerformed(e); 
	            else if (obj == mnAbrir) 
	               btnmnAbrirActionPerformed(e); 
	           else if (obj == mnAbout) 
	               btnmAyudaActionPerformed(e);
	           else if (obj== mnSalir)
	        	   btnmnSalirActionPerformed(e);
	           else if (obj== mnGuardarComo)
	        	   btnmnGuardarComoActionPerformed(e);
	         } 
	      };
	      
	     
	      
	      mnRealizarMov.addActionListener(al); 
	      mnAbrir.addActionListener(al);
	      mnSalir.addActionListener(al);
	      mnGuardarComo.addActionListener(al);
	      mnAbout.addActionListener(al);
	      //btnEntrada.addActionListener(al); 
	      //btnConfirma.addActionListener(al); 
	      
	      
	
	      //Listener del raton
	      PanelMouseListener mouse = new PanelMouseListener();
		  addMouseListener(mouse);
		  addMouseMotionListener(mouse);

	      
	      
	      
	   } //fin constructor interfaz grafica
	

		
	
		//Action Performed para Opcion RealizarMovimiento
		private void btnRealizaMovActionPerformed(ActionEvent evt){  
			JOptionPane.showMessageDialog(this, "PorFavor, Haga click en la casilla a la que quieras moverte", "Realizar Movimiento", JOptionPane.INFORMATION_MESSAGE); 
		} 
		
		//Action Performed para Opcion Abrir
		private void btnmnAbrirActionPerformed(ActionEvent e){ 
			new JFileChooser().showDialog( contentPane,"Abrir" );
		} 
		
		//Action Performed para Opcion Abrir
		private void btnmnGuardarComoActionPerformed(ActionEvent e){ 
			new JFileChooser().showSaveDialog(contentPane);
		} 
		
		//Action Performed para Opcion Ayuda
		private void btnmAyudaActionPerformed(ActionEvent e){ 
			Vista vista = new Vista();
			String Ayuda = vista.mensajeAyuda();
			JOptionPane.showMessageDialog(this, Ayuda , "Ayuda", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		//Action Performed para Opcion Salir
		private void btnmnSalirActionPerformed(ActionEvent e){ 
			int res = JOptionPane.showConfirmDialog( this,"¿Desea Salir del Juego?",
		              "Salir",JOptionPane.YES_NO_OPTION );
		       
		          if( res == JOptionPane.YES_OPTION )
		          	  dispose();
		} //fin opcion salir

		/*private void btnEntradaActionPerformed(ActionEvent evt) { 
			// Centro del marco por omisión 
			String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre por favor"); 
			if (nombre != null && !nombre.isEmpty()) 
				lblNombre.setText("Hola, " + nombre); 
		} 

		private void btnConfirmaActionPerformed(ActionEvent evt){ 
			int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION); 
			if (respuesta == 0) 
				System.exit(0); 
		} */
		
		
	
	
	
	class PanelMouseListener extends MouseAdapter implements MouseMotionListener {
		
		//Se activa si se pulsa un botón y no se mantiene pulsado: 
		public void mouseClicked(MouseEvent e){
			
			
			System.out.println("la posicion X pulsada es:   "+ e.getX()+"");
			System.out.println("la posicion Y pulsada es:   "+ e.getY()+"");
			
		}
		//Se activa cuando el mouse entra en el Panel: 
		public void mouseEntered(MouseEvent e){
			
		}
	    //Se activa cuando el mouse sale del Panel: 
		public void mouseExited(MouseEvent e){
			
		}
		//Se activa si se pulsa un botón y sí se mantiene pulsado: 
		public void mousePressed(MouseEvent e){
			
		}
		//Se activa cuando se suelta el botón presionado previamente (análoga de mousePressed):
		public void mouseReleased(MouseEvent e){
			
		}
		//Se activa cuando arrastramos el mouse con un botón pulsado: 
		public void mouseDragged(MouseEvent e){
			
		}
		//Se activa cuando se produce un movimiento del mouse: 
		public void mouseMoved(MouseEvent e){
			
		}

		
		
		
	}
}

	