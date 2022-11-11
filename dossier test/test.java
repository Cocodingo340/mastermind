public class test {

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
    public static String listElem(char[] t){
        String liste=new String();
        liste="("+liste+t[0];
        for(int i=1; i < t.length ; i++){
            liste=liste+","+t[i];
        }
        liste=liste+")";
        return liste;
    }

    public static int plusGrandIndice(char[] t, char c){
        int resultat=-1;
        for(int i=0; i < t.length ; i++){
            if(t[i]==c && t[i]>resultat){
                resultat=i;
            }
        }
        return resultat;

    }
    public static boolean estPresent(char[] t, char c){
        if(plusGrandIndice(t,c)!=-1){
            return true;
        }
        return false;

    }

    public static boolean sontEgaux(int[] t1, int[] t2){
        int resultat=0;
        for(int i=0; i < t1.length ; i++){
            if(t1[i]==t2[i]){
                resultat++;
            }
        }
        return resultat == t1.length;
    }

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

    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){
        String code=Ut.saisirChaine();
        while(!codeCorrect(code,4,tabCouleurs)){
            Ut.afficher("Code incorrect : veuillez resaisir.");
            code=Ut.saisirChaine();
        }
        return motVersEntiers(code,tabCouleurs);
    }

    public static void main(String[] args) {
        String test=new String("RBVB");
        char[] omg=new char[]{'R','B','V'};
        Ut.afficher(propositionCodeHumain(4,4,omg));
    }
}
