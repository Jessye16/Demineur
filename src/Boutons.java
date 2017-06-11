/**
* Description : La Classe Boutons qui définit l'aspect des boutons
* et qui joue le rôle du controleur
*
*
*/
//***** La Classe Boutons *****/
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.event.*;
   
    class Boutons extends JButton implements MouseListener{
    
    // Variables d'instance
    Modele m;
    PanelBoutons pb;
    int x;
	int y;
    EcranDemineur ed;
   	
   	// Constructeur des boutons contenus dans la grille prend en paramètre l'icon, le x et le y...
       Boutons(Icon i, int x, int y, Modele modele, PanelBoutons panel){
   	// Affectation des valeurs      
			super(i);
         this.x=x;
         this.y=y;
         m=modele;
         pb=panel;	
		// Définition des propriétés	
         setFocusPainted(false);
         setBorderPainted(false);		
         setContentAreaFilled(false);
         setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         addMouseListener(this); // Ajout d'un listener
      }
		
		// Constructeur à un paramètre
		 Boutons(Icon ic){
		 	super(ic);
			setFocusPainted(false);
         setBorderPainted(false);		
         setContentAreaFilled(false);
         setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
   	 
       public	void 	mouseEntered(MouseEvent e) {}
   
       public 	void 	mouseExited(MouseEvent e) {}
   
       public void mousePressed(MouseEvent e) {}
   
       public void mouseReleased(MouseEvent e){}

      /** Méthode qui contrôle le clic, activé que si une case n'est pas déja découverte
		Pour chaque bouton cliqué on appelle la méthode adéquate **/
       public void mouseClicked(MouseEvent e){
		 
         if (m.getCaseEvt(x,y)!=m.DECOUVERTE){

            if (!m.verifVictoire() && m.getCaseEvt(x,y)==m.NONDECOUVERTE && e.getButton() == MouseEvent.BUTTON1)
            {pb.changeBoutonsGauche(x,y);}
            		
            if(!m.verifVictoire() && e.getButton() == MouseEvent.BUTTON3){ pb.changeBoutonsDroite(x,y);}
         }
         
         if(m.verifVictoire()&& e.getButton() == MouseEvent.BUTTON1 || m.verifVictoire() && e.getButton() == MouseEvent.BUTTON3)
            pb.dialogueFin(true);
      }
       
      
   
      		
   }