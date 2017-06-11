/**
* Description : La classe ControleurMenuJeu permet d'attribuer des actions aux items de Menu 
*Présent dans le Menu de l'écran de jeu.
* 
*
*/
//***** La Classe ControleurMenuJeu *****/ 

   import java.awt.*; 
   import java.awt.event.*; 
	import javax.swing.*; 
	import javax.swing.event.*;

    class ControleurMenuJeu implements ActionListener{
    
   		// Les champs
     	 /**
		 * @uml.property  name="monDemineur"
		 * @uml.associationEnd  multiplicity="(1 1)"
		 */
     	EcranDemineur monDemineur;
   	
   		// Le constructeur
       ControleurMenuJeu(EcranDemineur d){
         this.monDemineur=d;
      }
   
   	   // ActionPerformed agit différemment selon la commande
       public void actionPerformed(ActionEvent e) { 
        
         // Pour quitter l'application
         if (e.getActionCommand().equals("Quitter")) {
            System.exit(0); 
         }
         
         // Pour une nouvelle partie
         else if(e.getActionCommand().equals("Nouvelle")){
            EcranDemineur fenetreDemineur1=new EcranDemineur(400,421,11,11,20,1);
            monDemineur.dispose();
         }
         
         // Ouvrir l'aide
         else if(e.getActionCommand().equals("Help")){
            Aide helpMe=new Aide();  	
         }
      	
      }
   }
