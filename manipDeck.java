public class manipDeck {
    public static int[] etape5(int[] deck,int lMot) {
        deck=unAquatre(deck);
        int n=0;
        int k;
        int[] clef = new int[lMot];
        boolean b =false;
        cryptage.onLog("debut étape 5");
        for(int i=0;i<lMot;i++){
            cryptage.onLog("lettre numero "+i);
            while(b==false){
                k=deck[n];
                traducteur.afficheTabInt(deck,"etat deck");
                cryptage.onLog("valeur carte au dessus "+k);
                if(k==53 || k==54){
                    cryptage.onLog("le joker ");
                    deck=unAquatre(deck);

                }
                else{
                    clef[i]=deck[k]%26;
                    b=true;
                    cryptage.onLog("c'est bon lettre trouver pour la clef : "+clef[i] + " de base "+deck[k]);
                    if(i<lMot-1){
                        deck=unAquatre(deck);
                    }
                }
            }
            b=false;

        }

    return clef;
    }
    public static int[] unAquatre(int[] deck) {
        deck=moveJoker(deck,53);
        deck=moveJoker(deck,54);
        deck=moveJoker(deck,54);
        deck=doublecoupe(deck);
        deck=simpleCoupe(deck);
        return deck;

    }

    public static int[] moveJoker(int[] deck,int aMove) {
        boolean b=true;
        int i=0;
        do{
            if(deck[i]==aMove){
                b=false;
                if(i==53){
                    deck[53]=deck[2];
                    deck[2]=aMove;
                }
                
                else{
                    deck[i]=deck[i+1];
                    deck[i+1]=aMove;
                }
            }
        i++;
        }while(b);
        
        traducteur.afficheTabInt(deck,"Mouv joker:");
        return deck;
    }

    public static int[] doublecoupe(int[] deck) {
        int indiceJ1 = -1;
        int indiceJ2 = -1;
        int i = 0;

        // Trouver les indices des éléments 53 et 54 dans le tableau
        while (indiceJ1 == -1 || indiceJ2 == -1) {
            if ((deck[i] == 53 || deck[i] == 54) && indiceJ1 < 0) {
                indiceJ1 = i; 
            } else if ((deck[i] == 53 || deck[i] == 54) && indiceJ2 < 0) {
                indiceJ2 = i; 
            }
            i++;
        }  

          int[] resultat = new int[deck.length];
            int j = 0;

            for (int k = indiceJ2 + 1; k < deck.length; k++) {
                resultat[j] = deck[k];
                j++;
            }

            resultat[j] = deck[indiceJ1];
            j++;

            for (int k = indiceJ1 + 1; k < indiceJ2+1; k++) {
                resultat[j] = deck[k];
                j++;
            }

            for (int k = 0; k < indiceJ1; k++) {
                resultat[j] = deck[k];
                j++;
            }
            traducteur.afficheTabInt(resultat,"Double coupe :");
            return resultat;
    }
    public static int[] simpleCoupe(int[] deck) {
        if(deck[deck.length-1]<=53){
            int indiceJ1 = deck[deck.length-1];
        int i = 0;
        
          int[] resultat = new int[deck.length];
            int j = 0;

            // Copier les éléments après indiceJ2
            for (int k = indiceJ1 ; k < deck.length-1; k++) {
                resultat[j] = deck[k];
                j++;
            }
            for (int k = 0; k < indiceJ1; k++) {
                resultat[j] = deck[k];
                j++;
            }
            resultat[j]=deck[deck.length-1];
            traducteur.afficheTabInt(resultat,"Simple coupe :");
            return resultat;


        }else{
            return deck;

        }
        
        
    }
    
}
