package es.ucm.fdi.lps.g08.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import es.ucm.fdi.lps.g08.*;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazInicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2680152776020910907L;
	private JPanel contentPane;
	private Image imgInicio = (Toolkit.getDefaultToolkit()).getImage("img/AbadiaInicio.png");
	private PanelTablero panelSuperior;
	private String bienvenida;
	JButton btnSalir,btnJugar,btnElegircolor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazInicio frame = new InterfazInicio();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazInicio() {
		super("El Misterio de la Abadia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//No permite Maximizar la ventana
		setResizable(false);
		setBounds(100, 100, 684, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//PanelSuperior
		panelSuperior = new PanelTablero(imgInicio);
		
		panelSuperior.setBounds(6, 6, 414, 376);
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(null);
		
		
		//PaneInferior
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(6, 394, 414, 58);
		contentPane.add(panelInferior);
		panelInferior.setLayout(null);
		
		btnElegircolor = new JButton("ELEGIR COLOR");
		btnElegircolor.setBounds(23, 23, 127, 29);
		panelInferior.add(btnElegircolor);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setBounds(291, 23, 117, 29);
		panelInferior.add(btnSalir);

		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(162, 23, 117, 29);
		panelInferior.add(btnJugar);
		
		
		//Panel Derecho
		Vista vista=new Vista();
		
		bienvenida = vista.InterfazInicio();
		
		JTextPane txtpnD = new JTextPane();
		txtpnD.setEditable(false);
		txtpnD.setOpaque(false);
		txtpnD.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		txtpnD.setForeground(Color.DARK_GRAY);
		txtpnD.setText(bienvenida);
		txtpnD.setBounds(432, 6, 246, 446);
		contentPane.add(txtpnD);
		

		ActionListener al = new ActionListener() { 
	         public void actionPerformed(ActionEvent e){ 
	            Object obj = e.getSource(); 
	            if (obj == btnSalir) 
	            	btnSalirActionPerformed(e); 
	            else if (obj == btnElegircolor) 
	            	btnElegircolorActionPerformed(e); 
	           else if (obj == btnJugar) 
	        	   btnJugarActionPerformed(e);
	       
	         } 
	      };
	      
	    //a|adimos oyentes
	      btnSalir.addActionListener(al);
	      btnJugar.addActionListener(al);
	      btnElegircolor.addActionListener(al);
	
	
	
	
	}//Fin Constructor InterfazInicio
	
	
			//Action Performed para Opcion Salir
			private void btnSalirActionPerformed(ActionEvent e){ 
				int res = JOptionPane.showConfirmDialog( this,"¿Desea Salir del Juego?",
			              "Salir",JOptionPane.YES_NO_OPTION );
			       
			          if( res == JOptionPane.YES_OPTION )
			          	  dispose();
			} //fin opcion salir
			
			
			//Action Performed para Opcion Elegir Color
			private void btnElegircolorActionPerformed(ActionEvent e){ 
				int res = JOptionPane.showConfirmDialog( this,"¿Desea Salir del Juego?",
			              "Salir",JOptionPane.YES_NO_OPTION );
			       
			          if( res == JOptionPane.YES_OPTION )
			          	  dispose();
			} //fin opcion ElegirColor
			
			

			//Action Performed para Opcion Jugar
			private void btnJugarActionPerformed(ActionEvent e){ 
				int res = JOptionPane.showConfirmDialog( this,"¿Desea Empezar el Juego?",
			              "Jugar",JOptionPane.YES_NO_OPTION );
			       
			          if( res == JOptionPane.YES_OPTION ){
			        	  
			        	  InterfazPrincipal intprin=new InterfazPrincipal();
			        	  intprin.setVisible(true);
			        	  contentPane.setVisible(false);
			          }
			          
			    
			} //fin opcion Jugar

}
