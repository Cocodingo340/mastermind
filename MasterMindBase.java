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
        for(int i=0; i < tab.length ; i++){
            tab2[i]=tab[i];
        }
        return tab2;

    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
    */
    public static String listElem(char[] t){
        String liste=new String();
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

    //______________________________________________
    
    /** pré-requis : aucun
	action : affiche un doublon et 2 de ses indices dans t s'il en existe
	résultat : vrai ssi les éléments de t sont différents
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean elemDiff(char[] t){
        for (int i=0; i< t.length; i++){
            if (t[i] != plusGrandIndice(3,4)){
                return true;
            }
        }
        return t;
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
        int cpt=0;
        for(int i=0; i < cod1.length; i++){
            if(cod1[i]==cod2[i]){
                cpt++;
            }
        }
        return cpt;

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
    */ // (pr yann)voir TD avec truc compliqué fait avec xavier
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        int cpt=0;
        for(int i=0; i < cod1.length; i++){
            for(int j=0; j < cod1.length; j++){
                if(cod1[i]==cod2[j]){
                    cpt++;
                }
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
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        for (int i=0; i< cod1.length; i++){
            for (int i=0; i< cod2.length; i++){
                if (cod1[i] == cod2[i]){

                }

            }
        }
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
  
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){
 
    }

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont 
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    public static boolean repCorrecte(int[] rep, int lgCode){
        if(rep[0]+rep[1]==lgCode && rep[0] >= 0 && rep[1] >= 0 ){
            return true
        }
        Ut.afficher("Ce n'est pas correct, il y a "+rep[0]+" couleurs bien placé et "+ rep[1]+" couleurs mal placé.");
        return false;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    */
    public static int[] reponseHumain(int lgCode){
        Ut.afficher("Saissisez les nombres de couleur bien et mal placé.");
        int bienp=Ut.saisirEntier();
        int malp=Ut.saisirEntier();
        while(bienp+malp!=lgCode){
            Ut.afficher("Resaissisez.");
            bienp=Ut.saisirEntier();
            malp=Ut.saisirEntier();
        }
        int[] tab={bienp,malp};
        return tab;

    }

    //___________________________________________________________________
    
    /** pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
	action : met dans cod1 le code qui le suit selon l'ordre lexicographique dans l'ensemble 
	         des codes de longueur cod1.length à valeurs de 0 à nbCouleurs-1, si ce code existe 
	résultat : vrai ssi l'action a pu être effectuée
    */
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs){

    }

    //___________________________________________________________________
    
    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length 
                     et  nbCoups < rep.length 
	résultat : vrai ssi cod[nbCoups] est compatible avec les nbCoups premières lignes de cod et de rep, 
	           c'est-à-dire que si cod[nbCoups] était le code secret, les réponses aux nbCoups premières 
	           propositions de cod seraient les nbCoups premières réponses de rep
    */
    public static boolean estCompat(int [][] cod, int [][] rep, int nbCoups, int nbCouleurs){
 
    }

    //___________________________________________________________________
    
    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 < nbCoups < cod.length 
                     et nbCoups < rep.length  
	action : met dans cod[nbCoups] le plus petit code (selon l'ordre lexicographique dans l'ensemble 
	  des codes de longueur cod[0].length à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
	  cod[nbCoups-1] selon cet ordre et compatible avec les nbCoups premières lignes de cod et de rep,
	  si ce code existe 
	résultat : vrai ssi l'action a pu être effectuée
    */
    public static boolean passePropSuivante(int [][] cod,int [][] rep, int nbCoups, int nbCouleurs){
  
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

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................
    

    /** action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, 
           nbManches, nbEssaisMax), 
	   effectue la partie et affiche le résultat (identité du gagnant ou match nul).
	   La longueur d'un code et le nombre de couleurs doivent être strictement positifs. 
	   Le nombre de manches doit être un nombre pair strictement positif.
	   Les initiales des noms de couleurs doivent être différentes. 
	   Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
    */
    public static void main (String[] args){
	
   
    } // fin main

    //___________________________________________________________________
    
} // fin MasterMindBase
