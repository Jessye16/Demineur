/**
* Description : La Classe PanelBoutons qui est le panel contenant l'ensemble des boutons 
* et un ensemble de méthodes permettant la mise à jour et le controle de la vue par le controleur
*  
*/
//***** La Classe PanelBoutons *****/
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.event.*;

    class PanelBoutons extends JPanel{
    
      // Les variables d'instance
   	// La taille de la fenêtre, le modèle, le nombre de lignes et de colonnes, un tableau de boutons etc...
	    Boutons[][] bouton;
	    int taillefenetrex;
		int taillefenetrey;
		int nblignes;
		int nbcolonnes;
		int nbmines;
	    Modele m;
	    JTextField compteur;
		JTextField chrono;
	    EcranDemineur ed;	
	    Timer montimer;
   	
   	// Méthode permettant de dessiner une image de fond
       public void paintComponent(Graphics g){
         g.drawImage(new ImageIcon("images/ecran_jeu/fond_plateau.png").getImage(),0,0,taillefenetrex,taillefenetrey,null);
      }
   	
   	// Construction du panel de boutons
       PanelBoutons(int taillex, int tailley, int nbl, int nbc, Modele modele, JTextField chrono, JTextField compteur,EcranDemineur ecran, Timer tim){       
      
      // Affectation des valeurs au variables
         taillefenetrex=taillex;
         taillefenetrey=tailley;
         nblignes=nbl-1;
         nbcolonnes=nblignes;
         m=modele;
         this.ed=ecran;
         nbmines=m.nbmines;
         this.chrono=chrono;
         this.compteur=compteur;
         montimer=tim;
      
      // Définition du Layout, de la position du panel, qui est ajusté automatiquement en fonction du mode de jeu	
         setLayout(new GridLayout(nblignes-1,nbcolonnes-1,0,0));
         setBounds(80, 60, taillefenetrex-160,taillefenetrey-160);
      	
      // Création d'un tableau en deux dimensions de boutons	
         bouton=new Boutons[nblignes][nbcolonnes];
         setOpaque(false);
       
      // Création des boutons  
         for(int i=1;i<nblignes;i++) {
            for(int j=1;j<nbcolonnes;j++){
               bouton[i][j]= new Boutons(new ImageIcon("images/ecran_jeu/Case_norm.png"),i,j,m,this);
               bouton[i][j].setRolloverIcon(new ImageIcon("images/ecran_jeu/Case_roll.png"));
               add(bouton[i][j]);  // Ajout au panel
            }
         }	
      }
      // Méthode permettant la mise à jour du bouton(grâce au clic gauche) en fonction de son contenu
       public void changeBoutonsGauche(int x, int y) {   
      // A chaque clic, la case concernée est rendu découverte dans le modèle, on désactive le rollover et on change le curseur
         m.setCaseEvt(x,y,m.DECOUVERTE);
         bouton[x][y].setRolloverEnabled(false);
         bouton[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       
      /** Si la case est vide, on appelle la méthode decouvrecases, si elle contient un chiffre on l'affiche,
       sinon on appelle griseBoutons qui va découvrir toutes les mines **/   
         if(m.getCaseMine(x,y)==m.CASEVIDE){
            if(m.getCaseChiffre(x,y)==0)
               decouvreCases(x,y); 
            if(m.getCaseChiffre(x,y)!=0)
               bouton[x][y].setIcon(new ImageIcon("images/ecran_jeu/Case_"+m.getCaseChiffre(x,y)+".png"));
         }
         else{ griseBoutons(x,y);} }
   	
   	// Méthode permettant la mise à jour du bouton(grace au clic droit) en fonction de son contenu
       public void changeBoutonsDroite(int x, int y){
        
         if(m.getCaseEvt(x,y)==m.NONDECOUVERTE && nbmines>0){
            m.setCaseEvt(x,y,m.DRAPEAU);
            bouton[x][y].setIcon(new ImageIcon("images/ecran_jeu/Case_drap.png"));
            bouton[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            bouton[x][y].setRolloverEnabled(false);
            nbmines--;
            compteur.setText(String.valueOf(nbmines));		
         } 
         
         else if(m.getCaseEvt(x,y)==m.DRAPEAU){
            m.setCaseEvt(x,y,m.NONDECOUVERTE);
            bouton[x][y].setIcon(new ImageIcon("images/ecran_jeu/Case_norm.png"));
            bouton[x][y].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
            bouton[x][y].setRolloverEnabled(true);
            nbmines++;
            compteur.setText(String.valueOf(nbmines));		
         } 
         
      }
   	
   	// Méthode permettant de dévoiler tous les boutons et d'arrêter le timer si une mine est découverte	
       public void griseBoutons(int x, int y){ 
         montimer.stop();
         for(int i=1;i<nblignes;i++){
            for(int j=1;j<nbcolonnes;j++){
            
               bouton[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
               bouton[i][j].setRolloverEnabled(false);
               
               if(m.getCaseMine(i,j)==m.CASEMINE && m.getCaseEvt(i,j)==m.DRAPEAU)
               {bouton[i][j].setIcon(new ImageIcon("images/ecran_jeu/Case_drap.png"));}
            		
               if(m.getCaseMine(i,j)!=m.CASEMINE && m.getCaseEvt(i,j)==m.DRAPEAU)
               {bouton[i][j].setIcon(new ImageIcon("images/ecran_jeu/Case_F_drap.png"));}
            		   
               if(m.getCaseMine(i,j)==m.CASEMINE && m.getCaseEvt(i,j)!=m.DRAPEAU)
               {bouton[i][j].setIcon(new ImageIcon("images/ecran_jeu/Case_mine.png"));}
                  
               m.setCaseEvt(i,j,m.DECOUVERTE);	
            }
         }
                       
         bouton[x][y].setIcon(new ImageIcon("images/ecran_jeu/Case_mine_active.png"));
         
         dialogueFin(false);		
      }	 
   	
   	// Méthode permettant la découverte des cases aux alentours d'une case vide		  
       public void decouvreCases(int x, int y){
         for(int i=(x-1);i<=(x+1);i++){
            for(int j=(y-1);j<=(y+1);j++){
            	
               if(m.getCaseChiffre(i,j)==0 && m.getCaseMine(i,j)==m.CASEVIDE && m.getCaseEvt(i,j)!=m.DRAPEAU && i!=0 && j!=0 && j!=nbcolonnes && i!=nblignes){ 
                  bouton[i][j].setIcon(new ImageIcon("images/ecran_jeu/Case_vide.png")); 
                  bouton[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  bouton[i][j].setRolloverEnabled(false);
                  if (bouton[i][j]!=bouton[x][y] && m.getCaseEvt(i,j)==m.NONDECOUVERTE){decouvreCases(i,j);}
                  m.setCaseEvt(i,j,m.DECOUVERTE);  
               }          
               
               if(m.getCaseChiffre(i,j)!=0 && m.getCaseMine(i,j)==m.CASEVIDE && m.getCaseEvt(i,j)!=m.DRAPEAU && i!=0 && j!=0 && j!=nbcolonnes && i!=nblignes)
               {bouton[i][j].setIcon(new ImageIcon("images/ecran_jeu/Case_"+m.getCaseChiffre(i,j)+".png"));
                  bouton[i][j].setRolloverEnabled(false);
                  bouton[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                  m.setCaseEvt(i,j,m.DECOUVERTE);
               }
            }
         }
      }
    
   	 // Ouverture du JDialogue de fin, le texte s'adapte si on a gagné ou perdu
       public void dialogueFin(boolean gagne){
         
         montimer.stop();
      
         String text="";
      
         if (gagne==true)
            text="Vous avez gagné !\n Votre temps est de "+chrono.getText()+"\n Cliquez sur:\n-Oui pour Rejouer\n-Non pour Quitter le Jeu";
      
         if (gagne==false)	
            text="Vous avez perdu !\n Votre temps est de "+chrono.getText()+"\n Cliquez sur:\n-Oui pour Rejouer\n-Non pour Quitter le Jeu";
      
         
         int replay=JOptionPane.showConfirmDialog(this,text);
         
         if(replay==JOptionPane.YES_OPTION){
            
            EcranDemineur fenetreDemineur1=new EcranDemineur(400,421,11,11,20,1);
            
            ed.dispose();
         }
         
         if(replay==JOptionPane.NO_OPTION){
            System.exit(0);
         }
              
      }	
   	
   
   }
   	 				 		
