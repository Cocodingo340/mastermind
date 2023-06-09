import java.util.*;
import java.lang.*;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................
    
    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
	résultat : un tableau de nb entiers égaux à val
    */
    public static int[] initTab(int nb, int val){
        int[] tab = new int[nb];
        for(int i=0; i < tab.length ; i++){
            tab[i]=val;
        }
        return tab;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : une copie de tab
    */
    public static int[] copieTab(int[] tab){
        int[] tab2 = new int[tab.length];
        System.arraycopy(tab, 0, tab2, 0, tab.length);
        return tab2;

    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
    */
    public static String listElem(char[] t){
        String liste= "";
        liste="("+liste+t[0];
        for(int i=1; i < t.length ; i++){
            liste=liste+","+t[i];
        }
        liste=liste+")";
        return liste;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
    */
    public static int plusGrandIndice(char[] t, char c){
        int resultat=-1;
        for(int i=0; i < t.length ; i++){
            if(t[i]==c && t[i]>resultat){
                resultat=i;
            }
        }
        return resultat;

    }
    //______________________________________________

    /** pré-requis : aucun
	résultat : vrai ssi c est un élément de t
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean estPresent(char[] t, char c){
        return plusGrandIndice(t, c) != -1;

    }

    public static boolean estPresent(int[] t, int c){
        for(int i=0; i < t.length ; i++){
            if (t[i]==c){
                t[i]=-1;
                return true;

            }
        }
        return false;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	action : affiche un doublon et 2 de ses indices dans t s'il en existe
	résultat : vrai ssi les éléments de t sont différents
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean elemDiff(char[] t){
        for (int i=0; i < t.length; i++){
            for (int j=0; j < t.length; j++){
                if(t[i]==t[j] && i!=j){
                    return false;
                }
            }

        }
        return true;
    }
    
    //______________________________________________

    /** pré-requis : t1.length = t2.length
	résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
    */
    public static boolean sontEgaux(int[] t1, int[] t2){
        int resultat=0;
        for(int i=0; i < t1.length ; i++){
            if(t1[i]==t2[i]){
                resultat++;
            }
        }
        return resultat == t1.length;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
	résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
    */
    public static int[] codeAleat(int lgCode, int nbCouleurs){
        int[] tab = new int[lgCode];
        for(int i=0; i < tab.length ; i++){
            tab[i]=Ut.randomMinMax(0,nbCouleurs-1);
        }
        return tab;

    }

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : si codMot n'est pas correct, affiche pourquoi
	résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
    */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        // string genre c'est BRJR pour dire bleu rouge...
        int resultat=0;
        if (codMot.length()==lgCode){
            for (int i=0 ; i < lgCode ; i++){
                if(estPresent(tabCouleurs,codMot.charAt(i))){
                    resultat++;
                }
            }
            return resultat == lgCode;
        }
        return false;

    }
   
    //____________________________________________________________
    
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
	résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
    */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){
        int[] tab = initTab(codMot.length(),0);
        for(int i=0; i < tab.length ; i++) {
            char lettre = codMot.charAt(i);
            for (int j = 0; j < tabCouleurs.length; j++) {
                if (lettre == tabCouleurs[j]) {
                    tab[i] = j;
                }
            }
        }
        return tab;
    }

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce 
	qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
	résultat : le code saisi sous forme de tableau d'entiers
    */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){
        String code=Ut.saisirChaine();
        while(!codeCorrect(code,4,tabCouleurs)){
            Ut.afficher("Code incorrect : veuillez resaisir.");
            code=Ut.saisirChaine();
        }
        return motVersEntiers(code,tabCouleurs);
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length
	résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
    */
    public static int nbBienPlaces(int[] cod1,int[] cod2){
        int bienPlace = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) {
                bienPlace++;
            }
        }
        return bienPlace;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
	Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
    */
    public static int[] tabFrequence(int[] cod, int nbCouleurs){
        int[] tab = new int[nbCouleurs];
        for(int i=0; i < cod.length; i++){
            tab[cod[i]]=tab[cod[i]]+1;
        }
        return tab;

    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
    */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        int cpt=0;
        for(int i=0; i < cod1.length; i++) {
            if (tabFrequence(cod1, nbCouleurs)[i] > tabFrequence(cod2, nbCouleurs)[i]) {
                cpt = cpt + tabFrequence(cod2, nbCouleurs)[i];
            } else {
                cpt = cpt + tabFrequence(cod1, nbCouleurs)[i];
            }
        }
        return cpt;


    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
	se trouvant  (resp. ne se trouvant pas) au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3) 
	et 2 mal placés (1 "0" et 1 "1")
    */
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleur) {
        int[] code1= copieTab(cod1);
        int[] code2= copieTab(cod2);
        int bienPlace = 0;
        int malPlace = 0;
        for (int i = 0; i < code1.length; i++) {
            if (code1[i] == code2[i]) {
                bienPlace++;
                code1[i]=-1;

            }
        }
        for (int j = 0; j < code1.length; j++) {
            if (estPresent(code1, code2[j])) {
                malPlace++;
            }
        }

        return new int[]{bienPlace,malPlace};
    }


    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /** pré-requis : numMache >= 1
	action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par le joueur humain          
    */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){
        int manche=1;
        int[] resultat = codeAleat(lgCode,tabCouleurs.length);
        System.out.println(Arrays.toString(resultat));
        int[] proposition=propositionCodeHumain(numManche,lgCode,tabCouleurs);
        while (manche<=nbEssaisMax){

            if(nbBienPlaces(resultat,proposition)==4){
                return manche;
            }
            else{
                System.out.println(Arrays.toString(nbBienMalPlaces(resultat, proposition, 3)));
            }

            manche++;
            proposition=propositionCodeHumain(numManche,4,tabCouleurs);
        }
        Ut.afficher("flop");
        return manche;

    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
        String mot = "";
        for (int i = 0; i < cod.length; i++) {
            mot = mot + tabCouleurs[cod[i]];
        }
        return mot;
    }

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont 
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    public static boolean repCorrecte(int[] rep, int lgCode){
        if (rep.length == 2) {
            return rep[0] + rep[1] == lgCode && rep[0] >= 0 && rep[1] >= 0;
        }
        return false;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    */
    public static int[] reponseHumain(int lgCode){
        Ut.afficherSL("Saissisez les nombres de couleur bien et mal placé.");
        Ut.afficher("Bien placé : "); int bienp=Ut.saisirEntier();
        Ut.afficher("Mal placé : "); int malp=Ut.saisirEntier();
        while(bienp+malp!=lgCode){
            Ut.afficherSL("Resaissisez.");
            Ut.afficher("Bien placé : "); bienp=Ut.saisirEntier();
            Ut.afficher("Mal placé : "); malp=Ut.saisirEntier();
        }
        int[] tab={bienp,malp};
        return tab;

    }

    //___________________________________________________________________

     /**CHANGE : action si le code suivant n'existe pas
     *************************************************
        pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
	action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
    */

     /** pas ça mais ça a cette gueule**/
     public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs) {
         boolean rep=true;
         int[] max= new int[]{nbCouleurs - 1, nbCouleurs - 1, nbCouleurs - 1, nbCouleurs - 1};
         if (Arrays.equals(cod1, max)) {
             for(int i=0; i < cod1.length; i++){
                 cod1[i]=0;
             }
             rep=false;
             return rep;

         }
         else{
             if(rep) {
                 for (int i = cod1.length-1; i > 0; i--) {
                     if (cod1[i] == nbCouleurs-1) {
                         cod1[i] = 0;
                         cod1[i - 1]++;

                         return true;
                     }
                     else{
                         cod1[i]=cod1[i]+1;

                         return true;
                     }
                 }


             }
         }
         return true;
     }

    //___________________________________________________________________

     /**CHANGE : ajout du paramètre cod1 et modification des spécifications 
     *********************************************************************  
        pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
             c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
            propositions de cod seraient les nbCoups premières réponses de rep resp.
   */
   public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
       for (int i=0; i < nbCoups; i++){
           if(!Arrays.equals(rep[i], nbBienMalPlaces(cod1, cod[i], nbCouleurs))){
               return false;
           }
       }
       return true;
    }

    //___________________________________________________________________

     /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat, 
                 ajout du paramètre cod1 et modification des spécifications 
     **************************************************************************      
            pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	    action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
      cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
      sinon met dans cod1 le code ne contenant que des "0" et retourne faux
   */
   public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
      passeCodeSuivantLexico(cod1,nbCouleurs);
      while(!estCompat(cod1, cod, rep, nbCoups, nbCouleurs)){
          if(passeCodeSuivantLexico(cod1,nbCouleurs)){
              return false;
          }
      }
      return true;
   }

    //___________________________________________________________________
    
    // manche Ordinateur

    /** pré-requis : numManche >= 2
	action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - 0 si le programme détecte une erreur dans les réponses du joueur humain
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par l'ordinateur
    */
    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int [] cod1 = new int[lgCode];
        int [][] rep = new int[nbEssaisMax][2];
        int [][] cod = new int[nbEssaisMax][lgCode];
        int nbCoups = 0;
        while(rep[nbCoups][0] != lgCode && nbEssaisMax>nbCoups){
            passeCodeSuivantLexicoCompat(cod1, cod, rep, nbCoups, tabCouleurs.length);
            Ut.afficher(entiersVersMot(cod1, tabCouleurs));
            rep[nbCoups] = reponseHumain(lgCode);
            nbCoups ++;
            cod[nbCoups] = cod1;
        }
        return nbCoups;


    }


    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier strictement positif saisi
    */
    public static int saisirEntierPositif(){
        Ut.afficher("Saisissez un entier positif.");
        int rep=Ut.saisirEntier();
        while(rep<0){
            Ut.afficher("Il n'est pas positif, recommencez.");
            rep=Ut.saisirEntier();
        }
        return rep;

    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier pair strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier pair strictement positif saisi
    */
    public static int saisirEntierPairPositif(){
        Ut.afficher("Saisissez un entier positif et pair.");
        int rep=Ut.saisirEntier();
        while(rep<0 && rep%2!=0){
            Ut.afficher("Il n'est pas positif ou il n'est pas pair, recommencez.");
            rep=Ut.saisirEntier();
        }
        return rep;

    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir le nombre de couleurs (stricement positif), 
                 puis les noms de couleurs aux initiales différentes, 
	         avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : le tableau des initiales des noms de couleurs saisis
    */
    public static char[] saisirCouleurs(){
        Ut.afficher("Saisissez un entier positif correspondant au nombre de couleur souhaité..");
        int rep1=Ut.saisirEntier();
        Ut.afficher("Saisissez maintenant les noms des couleurs avec les initiales.");
        String rep2=Ut.saisirChaine();
        char[] tabchar= new char[rep2.length()];
        while(rep1!=tabchar.length){
            Ut.afficher("Erreur : ressaisisez les noms des couleurs avec les initiales.");
            rep2=Ut.saisirChaine();
            tabchar= new char[rep2.length()];
        }
        for(int i=0; i <tabchar.length ; i++){
            tabchar[i]=rep2.charAt(i);
        }
        return tabchar;


    }

    public static int[][] affichePlateau(int [][] cod, int [][] rep, int nbCoups, char[] tabCouleurs){
        int[] cod1= {0,1,2,3};
        int[] rep1= {0,0,0,0};
        for (int i=0; i< nbCoups; i++) {
            Ut.afficherSL("Code proposé :" + entiersVersMot(cod1, tabCouleurs));
            Ut.afficherSL("Réponse: " + nbBienMalPlaces(cod1, rep1,4 ));


        }



        return cod;
    }


    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................
    

        /**CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
        ******************************************************************************
           action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, 
           nbManches, nbEssaisMax), 
	   effectue la partie et affiche le résultat (identité du gagnant ou match nul).
	   La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs. 
	   Le nombre de manches doit être un nombre pair strictement positif.
	   Les initiales des noms de couleurs doivent être différentes. 
	   Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
    */
    public static void main (String[] args){
        Ut.afficher("Veuillez saisir les paramètres de la partie :");
        Ut.afficherSL("Veuillez rentrer la longueur du code :");
        int lgCode=saisirEntierPositif();
        Ut.afficherSL("Veuillez rentrer les initiales des couleurs (elles doivent être différentes) :");
        char[] tabCouleurs = saisirCouleurs();
        Ut.afficherSL("Veuillez rentrer le nombre de manches :");
        int nbManches=saisirEntierPositif();
        Ut.afficherSL("Veuillez rentrer le nombre de manches maximum souhaité :");
        int nbEssaisMax=saisirEntierPositif();
        Ut.afficherSL("Merci. La partie va commencer");
        mancheHumain(lgCode,tabCouleurs,nbManches,nbEssaisMax);




    } // fin main

    //___________________________________________________________________
    
} // fin MasterMindBase
