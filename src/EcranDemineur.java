/**
* Description : EcranDemineur est la fenêtre qui abrite l'espace de Jeu
* Il contient PanelJeu qui lui même contient le panel de boutons
*
*/

//***** La Classe EcranDemineur *****/ 
	
   import java.awt.*;
   import java.awt.event.*; 
   import javax.swing.*; 
   import javax.swing.event.*;	
	
    class EcranDemineur extends JFrame{
     	 /**
		 * @uml.property  name="m"
		 * @uml.associationEnd  readOnly="true"
		 */
     	Modele m;
       /**
	 * @uml.property  name="niveau"
	 */
    int niveau;
   
   	// Constructeur de la fenêtre
       EcranDemineur(int taillex, int tailley, int nbl, int nbc,int nbmines,int niveauDeJeu){
         setSize(taillex,tailley);
         setTitle("BombSweeper");
         
      // Intégration du menu
         ControleurMenuJeu cmenu=new ControleurMenuJeu(this);	
         MenuJeu menu=new MenuJeu(cmenu);
         setJMenuBar(menu);
      	
      // Placement de la fenetre au milieu de l'écran 
         Dimension screen=(Toolkit.getDefaultToolkit()).getScreenSize();
         setLocation(screen.width/4,50);
      
      // Définisseur du niveau - pour une éventuelle partie du même niveau
         this.niveau=niveauDeJeu;
               
      // Ajout du panel de jeu contenant le panel des boutons etc..
         getContentPane().add(new PanelJeu(taillex,tailley,nbl,nbc,nbmines,this));	
         
      // Fenêtre rendu visible et non redimensionnable	
         setVisible(true);
         setResizable(false);
         
      // Activation de la croix
         addWindowListener(
                new WindowAdapter(){
                   public void windowClosing(WindowEvent e){
                     System.exit(0);
                  }
               }); 
      }
   
   }
