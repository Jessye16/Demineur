/**
* Description : La classe Aide permet d'ouvrir la fenêtre d'aide qui comporte une image grace à une simple Jframe et un JLabel
*
*
*/

//***** La Classe Aide *****/ 

   import java.awt.*; 
   import java.awt.event.*; 
	import javax.swing.*; 
	import javax.swing.event.*;
	
    public class Aide extends JFrame{
   
  		// Le Constructeur
       public Aide(){
      
      // Attributs de la fenêtre
         super ("BombSweeper Aide");
         setSize (680,701);
      
      // Placement de la fenêtre
         Dimension screen=(Toolkit.getDefaultToolkit()).getScreenSize();
         setLocation(screen.width/4,50);
      
      // Création du panel de fond
         JPanel panelFond=new JPanel();
         panelFond.setLayout(new BorderLayout());
         JLabel image = new JLabel( new ImageIcon("images/aide/fond_aide.png"));
         panelFond.add(image,BorderLayout.CENTER);
      
      // Ajout au content Pane
         getContentPane().add(panelFond);
         
      // Affichage - Taille - fermeture	
         setResizable(false);	
         setVisible(true);
         
         addWindowListener(
                new WindowAdapter(){
                   public void windowClosing(WindowEvent e){
                     dispose();
                  }
               });
      }
   
   }