/**
* Description : MenuJeu permet de créer une JMenuBar qui sera placée exclusivement dans la fenêtre de jeu
* 
*
*/

//***** La Classe MenuJeu *****/ 
	
   import java.awt.*; 
   import java.awt.event.*; 
   import javax.swing.*; 
   import javax.swing.event.*;

    class MenuJeu extends JMenuBar{
   
      /**
	 * @uml.property  name="menubar"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    ControleurMenuJeu menubar;
   	
      // Constructeur
       MenuJeu(ControleurMenuJeu c){
      
      // Lien avec le contrôleur
         this.menubar=c;
      
      // Menu > Fichier > Option > Aide
         JMenu jeu=new JMenu ("Jeu");
         jeu.setMnemonic(KeyEvent.VK_J);
         JMenu aide=new JMenu ("Aide");
         aide.setMnemonic(KeyEvent.VK_A);
      
      // Ajout au menu    
         add(jeu);
         add(aide);
      	
      // Item de menu Fichier > Nouvelle partie
         JMenuItem newGame=new JMenuItem ("Nouvelle partie");
         jeu.add(newGame);
         newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
         newGame.setActionCommand("Nouvelle"); 	
         newGame.addActionListener(menubar);  
      
      // Item de menu Fichier > Quitter 
         JMenuItem quit=new JMenuItem ("Quitter");
         quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
         quit.setActionCommand("Quitter"); 	
         quit.addActionListener(menubar); 
         jeu.add(quit);
      	         
      // Item de menu Aide >Ouvrir l'aide
         JMenuItem newAide=new JMenuItem ("Ouvrir l'aide");
         aide.add(newAide);
         newAide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
         newAide.setActionCommand("Help"); 	
         newAide.addActionListener(menubar); 
          
      }
   }