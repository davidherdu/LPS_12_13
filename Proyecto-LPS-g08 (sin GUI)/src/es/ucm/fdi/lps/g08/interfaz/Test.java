package es.ucm.fdi.lps.g08.interfaz;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Test extends JFrame 
{ 
   /**
	 * 
	 */
	private static final long serialVersionUID = 5731488099661494475L;
private JButton btnMensaje; 
   private JButton btnEntrada; 
   private JButton btnConfirma; 
   private JLabel lblNombre; 

   public Test() 
   { 
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      this.setTitle("Probando Diálogos predefinidos"); 
      this.setSize(new Dimension(400, 250)); 

      btnMensaje = new JButton("Saludar"); 
      btnEntrada = new JButton("Ingresar mi nombre"); 
      btnConfirma = new JButton("Salir"); 
      lblNombre = new JLabel(); 

      Container contenedor = this.getContentPane(); 

      lblNombre.setHorizontalAlignment(SwingConstants.CENTER); 
      lblNombre.setFont(new Font("Arial", Font.BOLD, 16)); 
      lblNombre.setForeground(Color.BLUE); 

      JPanel panelNorte = new JPanel(); 
      panelNorte.setLayout(new FlowLayout()); 
      panelNorte.add(btnMensaje); 
      panelNorte.add(btnEntrada); 
      panelNorte.add(btnConfirma); 

      contenedor.add(panelNorte, BorderLayout.NORTH); 
      contenedor.add(lblNombre, BorderLayout.SOUTH); 

      // Manejando eventos 
      ActionListener al = new ActionListener() 
      { 
         public void actionPerformed(ActionEvent evt) 
         { 
            Object obj = evt.getSource(); 
            if (obj == btnMensaje) 
               btnMensajeActionPerformed(evt); 
            else if (obj == btnEntrada) 
               btnEntradaActionPerformed(evt); 
            else if (obj == btnConfirma) 
               btnConfirmaActionPerformed(evt); 
         } 
      }; 
      btnMensaje.addActionListener(al); 
      btnEntrada.addActionListener(al); 
      btnConfirma.addActionListener(al); 
   } 

   private void btnMensajeActionPerformed(ActionEvent evt) 
   { 
      // Centro del marco padre 
      JOptionPane.showMessageDialog(this, "Hola, soy un cuadro modal", "Saludo", JOptionPane.INFORMATION_MESSAGE); 
   } 

   private void btnEntradaActionPerformed(ActionEvent evt) 
   { 
      // Centro del marco por omisión 
      String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre por favor"); 
      if (nombre != null && !nombre.isEmpty()) 
         lblNombre.setText("Hola, " + nombre); 
   } 

   private void btnConfirmaActionPerformed(ActionEvent evt) 
   { 
      int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION); 
      if (respuesta == 0) 
         System.exit(0); 
   } 

   public static void main(String[] args) 
   { 
      new Test().setVisible(true); 
   } 
}