public class cpttabtest {
    public static int[] compteur(int[] tab){
        int cpt=0;
        int[]newtab= new int[tab.length];
        for(int i=0; i<tab.length; i++){
            if(tab[i]==i){
                cpt++;
            }
            newtab[i]=cpt;

        }
        return newtab;
    }

    public static void main(String[] args) {
        int []pr={1, 2, 3, 5, 2, 4};
        Ut.afficher(compteur(pr));
    }
}
