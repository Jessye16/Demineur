 /**
* Description : La Classe PanelJeu qui est le panel de jeu contenant
* tous les components, le compteur, le timer et le panel des boutons
*
*/
//***** La Classe PanelJeu *****/ 
   import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.event.*;

    class PanelJeu extends JPanel{
    
     // Les champs
    EcranDemineur ed;
    Timer monTimer;
    
     // Variables d'instances, la largeur et la hauteur de la fenêtre
    int taillefenetrex;
    int taillefenetrey;
     
	  // on charge l'image de fond
	   public void paintComponent(Graphics g){
            g.drawImage(new ImageIcon("images/ecran_jeu/Fond_EcranJeu_T1.png").getImage(),0,0,null); 
      }
   
   	// Constructeur du panel jeu, prenant en paramètre la taille, le nombre de lignes et de colonnes, et le nombre de mines
       PanelJeu(int taillex, int tailley, int nbl, int nbc, int nbmines,EcranDemineur ecran){
         this.ed=ecran;
         taillefenetrex=taillex;
         taillefenetrey=tailley;
      
      // On définit le layout à null de manière à placer les composants là où on le souhaite
         setLayout(null);
                  								  
      
      // Création du compteur de mines et définition de ses propriétés
         JTextField compteur=new JTextField(String.valueOf(nbmines));		
         compteur.setForeground(new Color(132,150,156));
         compteur.setBackground(new Color(0,24,33));
         compteur.setEditable(false);
         compteur.setHorizontalAlignment(JTextField.CENTER);
         compteur.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         compteur.setFont(new Font("nom",1,25));
         compteur.setBounds(80,taillefenetrey-90,45,30);
      
      // Création du compteur de temps et définition de ses propriétés
         JTextField chrono=new JTextField("");		
         chrono.setForeground(new Color(132,150,156));
         chrono.setBackground(new Color(0,24,33));
         chrono.setEditable(false);
         chrono.setHorizontalAlignment(JTextField.CENTER);
         chrono.setBorder(BorderFactory.createLineBorder(Color.black, 1));
         chrono.setFont(new Font("nom",1,25));
         chrono.setBounds(300,taillefenetrey-90,90,30);	   
      		
      
      // Mise en place du timer
         monTimer= new Timer(1000,new Chrono(chrono,ed.niveau,ed,this));
         monTimer.start();	
      
      
      // Création du panel contenant les boutons    
         PanelBoutons pbouton=new PanelBoutons(taillex,tailley,nbl,nbc,new Modele(nbl,nbc,nbmines),chrono,compteur,ed,monTimer);
      	            
      	
      // Ajout des components au panel	
         add(pbouton);
         add(compteur);
         add(chrono);
      }
   }