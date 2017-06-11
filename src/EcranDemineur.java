/**
* Description : EcranDemineur est la fen�tre qui abrite l'espace de Jeu
* Il contient PanelJeu qui lui m�me contient le panel de boutons
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
   
   	// Constructeur de la fen�tre
       EcranDemineur(int taillex, int tailley, int nbl, int nbc,int nbmines,int niveauDeJeu){
         setSize(taillex,tailley);
         setTitle("BombSweeper");
         
      // Int�gration du menu
         ControleurMenuJeu cmenu=new ControleurMenuJeu(this);	
         MenuJeu menu=new MenuJeu(cmenu);
         setJMenuBar(menu);
      	
      // Placement de la fenetre au milieu de l'�cran 
         Dimension screen=(Toolkit.getDefaultToolkit()).getScreenSize();
         setLocation(screen.width/4,50);
      
      // D�finisseur du niveau - pour une �ventuelle partie du m�me niveau
         this.niveau=niveauDeJeu;
               
      // Ajout du panel de jeu contenant le panel des boutons etc..
         getContentPane().add(new PanelJeu(taillex,tailley,nbl,nbc,nbmines,this));	
         
      // Fen�tre rendu visible et non redimensionnable	
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
