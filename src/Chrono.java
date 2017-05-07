/**
* Description : La classe Chrono permet de faire fonctionner le timer, elle contient l'ActionPerformed qui d�termine les 
*actions d�finies � chaque secondes dans ce cas.
* 
*
*/

//***** La Classe Chrono *****/ 

   import java.awt.*; 
   import java.awt.event.*;
   import javax.swing.*; 
   import javax.swing.event.*;

    public class Chrono implements ActionListener{
   
   	// Les Champs
    int cptMin;
	int cptSec;
	int niveau;
    JTextField textChrono;
    EcranDemineur ed;
    PanelJeu panJ;
   	
   	// Le Constructeur
       Chrono(JTextField jt,int niv,EcranDemineur eccd,PanelJeu pj){
      // Mise en place du compteur pour le chronom�tre
         textChrono=jt;
         niveau=niv;
         ed=eccd;
         panJ=pj;
        
      // Initialisation du chronom�tre � 0:0
            cptMin=0;
            cptSec=0;
      }
         
   	// Accesseurs des compteurs de secondes et de minutes
       int getMin(){
         return cptMin; }
   	
       int getSec(){
         return cptSec; }
   	
   	
   	// ActionPerformed, c'est l'action qui est r�p�t�e par le timer toute les secondes
       public void actionPerformed(ActionEvent e) {
      
      // Chaine de caract�re permettant de mettre � jour le JTextField de la vue
         String temps="";
      
      // Comportement du compteur
              
        // mode d�butant = incr�mentation � partir de 0:0      
         if(niveau==1) {
            cptSec++;
            
            if (getSec()==60){
               cptMin++;
               cptSec=0;}
            
            temps=getMin()+":"+getSec(); 
            textChrono.setText(temps);   
         }
         
      }
      
   }
