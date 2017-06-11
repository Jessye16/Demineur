/**
* Description : La Classe Modèle qui est le modèle du jeu
* et qui contient toutes les méthodes nécessaires
*
*
*/
//***** La Classe Modele *****/
    public class Modele {
   
	// Les Champs statiques
      static final int CASEVIDE=0;
      static final int CASEMINE=1;
      static final int DRAPEAU=2;
      static final int NONDECOUVERTE=3;
      static final int DECOUVERTE=4;
   
	// Les variables d'instance
    int [][] plateauMine;
    int [][] plateauChiffre;
    int [][] plateauEvt;
    int nbl;
	int nbc;
	int nbmines;  
   
   // Constructeur
       Modele(int nbl, int nbc, int nbmines){
         this.nbl=nbl;
         this.nbc=nbc;
         this.nbmines=nbmines;		
         init(nbl, nbc, nbmines); }
   	
   
   // Les accesseurs et modifieurs
       int getCaseMine(int x, int y){
         return plateauMine[x][y]; }
   		
       int getCaseEvt(int x, int y){
         return plateauEvt[x][y];}
   		
       int getCaseChiffre(int x, int y){
         return plateauChiffre[x][y]; }
   	
       void setCaseEvt(int x, int y, int etat){
         plateauEvt[x][y]=etat; }	
   	
   // Initialisation des différents plateaux et calcul du nombre de mines aux alentours des cases
       void init(int nbl, int nbc, int nbmines){
         plateauMine=new int[nbl][nbc];
         plateauEvt=new int[nbl][nbc];
         plateauChiffre=new int[nbl][nbc];
         for(int i=0;i<nbl;i++){
            for(int j=0;j<nbc;j++){
               plateauMine[i][j]=CASEVIDE;
               plateauEvt[i][j]=NONDECOUVERTE;
               if(i==0 || j==0 || i==(nbl-1) || j==(nbc-1))
                  plateauEvt[i][j]=DECOUVERTE; }}	
         plateauAlea(nbl, nbc, nbmines);
         calcPlateauChiffre(nbl, nbc);
      }
   	
   			 
   /** Méthode permettant de vérifier si le joueur à gagné, renvoie true s'il a gagné
	Si les drapeaux sont au bon endroit, si leur nombre est égale au nombre de mines et
	toutes les autres cases sont découvertes alors le jour à gagné **/
       boolean verifVictoire(){	
         int compteur=0;
         boolean victoire=false;
         for(int i=0;i<nbl;i++){
            for(int j=0;j<nbc;j++){
               if(getCaseMine(i,j)==CASEMINE && getCaseEvt(i,j)==DRAPEAU && compteCasesDecouvertes()==(this.nbl*nbc)-nbmines){
                  compteur++;
                  if(compteur==nbmines) 
                     victoire=true;
                  else 
                     victoire=false;
               }
            }
         }
         return victoire;
      }
   	
	// Méthode comptant le nombre de cases découvertes, sert dans la méthode verifVictoire()
       int compteCasesDecouvertes(){
         int nombre=0;
         for(int i=0;i<nbl;i++){
            for(int j=0;j<nbc;j++){
               if(getCaseEvt(i,j)==DECOUVERTE)
                  nombre++; 
            }
         }
         return nombre; }
               
   // Génération aléatoire du plateau de mines
       void plateauAlea(int nbl, int nbc, int nbmines){
         for (int i=0;i<nbmines;i++){
         
            int lig=1+(int)(Math.random()*(nbl-2));
            int col=1+(int)(Math.random()*(nbc-2));
         
            if (plateauMine[lig][col]==CASEVIDE){
               plateauMine[lig][col]=CASEMINE;
            }
            else{i--;}
         }
      }
   	
   	
   // Calcul du nombre de mines contenues dans les cases alentour
       void calcPlateauChiffre(int nbl, int nbc){
         for(int i=1;i<(nbl-1);i++){
            for(int j=1;j<(nbc-1);j++){
               if(plateauMine[i][j]!=CASEMINE)
                  plateauChiffre[i][j]=plateauMine[i-1][j-1]+plateauMine[i][j-1]+plateauMine[i+1][j-1]+plateauMine[i-1][j]+plateauMine[i+1][j]+plateauMine[i-1][j+1]+plateauMine[i][j+1]+plateauMine[i+1][j+1]; } }
      }
       
    // Méthode décrivant l'état des différents plateaux		
       public String toString(){
         String chaine="";
         chaine+="C'est le tableau des Mines\n";
         for(int i=0;i<nbl;i++){
            for(int j=0;j<nbc;j++){
               chaine+=getCaseMine(i,j); }chaine+="\n"; }chaine+="\n";
      	
         chaine+="Maintenant le tableau des Chiffres\n";
         for(int b=0;b<nbl;b++){
            for(int c=0;c<nbc;c++){
               chaine+=getCaseChiffre(b,c); }chaine+="\n"; }chaine+="\n";
         			
         chaine+="Enfin le tableau des Evenements\n";
         for(int x=0;x<nbl;x++){
            for(int y=0;y<nbc;y++){
               chaine+=getCaseEvt(x,y); }chaine+="\n"; }chaine+="\n";
         return chaine; }
   
	// Méthode affichant la méthode toString	
       void affiche(){
         System.out.println(toString());}
   	
   }
