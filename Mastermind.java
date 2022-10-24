
public class Mastermind {

    public static int[] choixOrdi(){
        // Programme qui crée un tableau et le remplit avec des chiffres au hasard allant de 0 à 3
        int[] tab=new int[4];

        for(int i=0;i<3;i++){
            tab[i]= Ut.randomMinMax(0,3);
        }
        return tab;
    }

    public static int[] choixUtilisateur(){
        // Programme qui demande à l'utilisateur de remplir un tableau avec des chiffres
        int[] tab=new int[4];
        Ut.afficher("Veuillez choisir une combinaison de 4 chiffres allant de 0 à 3 inclus.");
        Ut.passerLigne();
        for(int i=0;i<4;i++){
            Ut.afficher("Nombre n°"+(i+1)+":");
            Ut.passerLigne();
            tab[i]=Ut.saisirEntier();
        }
        return tab;
    }

    public static boolean estDansTab(int val, int[] tab){
        // Programme qui regarde si une valeur est dans le tableau
        for (int j : tab) {
            if (val == j) {
                return true;
            }
        }
        return false;
    }

    public static void programmePrincipal(){
        int[] ordi=choixOrdi();
        int bienPlace=0;
        int malPlace=0;
        int pasPlace=0;
        Ut.afficher(ordi);
        while (bienPlace!=4){
            int[] user=choixUtilisateur();
            bienPlace=0;
            malPlace=0;
            pasPlace=0;
            for(int i = 0; i < user.length; i++){
                if(user[i]==ordi[i]){
                    Ut.afficher("Nombre n°"+(i+1)+": Vrai.");
                    Ut.passerLigne();
                    bienPlace++;
                }
                else if(estDansTab(user[i], ordi)){
                    Ut.afficher("Nombre n°"+(i+1)+": Vrai mais au mauvais endroit.");
                    Ut.passerLigne();
                    malPlace++;
                }
                else{
                    Ut.afficher("Nombre n°"+(i+1)+": Faux.");
                    Ut.passerLigne();
                    pasPlace++;
                }

            }
            Ut.afficherSL("Nombres bien placé :"+bienPlace);
            Ut.afficherSL("Nombres mal placé :"+malPlace);
            Ut.afficherSL("Nombres faux :"+pasPlace);


        }
        Ut.afficher("Bravo vous avez gagné !");


    }

    public static void main(String[] args) {
        programmePrincipal();
    }
}


