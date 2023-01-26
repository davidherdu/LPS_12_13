package es.ucm.fdi.lps.g08.interfaz;

import java.awt.Image;

import javax.swing.JPanel;

import java.awt.Graphics;
//import java.awt.Image;


import javax.swing.ImageIcon;


public class PanelTablero extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	Image imagen;
	
	
	public PanelTablero() {

	}
	
	public PanelTablero(String nombreImagen) {
	        if (nombreImagen != null) {
	            imagen = new ImageIcon(
	                           getClass().getResource(nombreImagen)
	                           ).getImage();
	        }
	    }
	 
	public PanelTablero(Image imagenInicial) {
	        if (imagenInicial != null) {
	            imagen = imagenInicial;
	            
	        }
	    }
	 
	/*public void setImagen(String nombreImagen) {
	        if (nombreImagen != null) {
	            imagen = new ImageIcon(
	                   getClass().getResource(nombreImagen)
	                   ).getImage();
	        } else {
	            imagen = null;
	        }
	 
	        repaint();
	    }*/
	 
	 /*public void setImagen(Image nuevaImagen) {
	        imagen = nuevaImagen;
	 
	        repaint();
	    }*/
	 
	    @Override
	  public void paint(Graphics g) {
	        if (imagen != null) {
	        
	            g.drawImage(imagen, 0, 0,getWidth(),getHeight(), this);
	 
	            setOpaque(false);
	        } else {
	            setOpaque(true);
	        }
	 
	        super.paint(g);
	    }
	    
	  
	}
	
