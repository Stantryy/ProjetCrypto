import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static int[] etape5(int[] deck,int lMot) {
        deck=unAquatre(deck);
        int n=0;
        int k;
        int[] clef = new int[lMot];
        boolean b =false;
        System.out.println("debut étape 5");
        for(int i=0;i<lMot;i++){
            System.out.println("lettre numero "+i);
            while(b==false){
                k=deck[n];
                afficheTabInt(deck,"etat deck");
                System.out.println("valeur carte au dessus "+k);
                if(k==53 || k==54){
                    System.out.println("le joker 12345 ");
                    deck=unAquatre(deck);

                }
                else{
                    clef[i]=deck[k]%26;
                    b=true;
                    System.out.println("c'est bon lettre trouver pour la clef : "+clef[i] + " de base "+deck[k]);
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

            // Copier les éléments après indiceJ2
            for (int k = indiceJ2 + 1; k < deck.length; k++) {
                resultat[j] = deck[k];
                j++;
            }

            // Copier l'élément à indiceJ1
            resultat[j] = deck[indiceJ1];
            j++;

            // Copier les éléments entre indiceJ1+1 et indiceJ2
            for (int k = indiceJ1 + 1; k < indiceJ2+1; k++) {
                resultat[j] = deck[k];
                j++;
            }

            // Copier les éléments avant indiceJ1
            for (int k = 0; k < indiceJ1; k++) {
                resultat[j] = deck[k];
                j++;
            }
            afficheTabInt(resultat,"Double coupe :");
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
            afficheTabInt(resultat,"Simple coupe :");
            return resultat;


        }else{
            return deck;

        }
        
        
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
        
        afficheTabInt(deck,"Mouv joker:");
        return deck;
    }

    
    public static String asciiToString(int[] mot) {
    StringBuilder m = new StringBuilder();
    for (int i = 0; i < mot.length; i++) {     
        int charValue = mot[i] + 96;  // Correction : Ne pas modifier mot[i] directement
        m.append((char) charValue);
    }
    return m.toString();
}


    public static int[] stringtoAscii(String mot) {

        int[] asciiValues = new int[mot.length()];
        for (int i = 0; i < mot.length(); i++) {
            asciiValues[i] = (int) mot.charAt(i);
        }
        
        // Affichage du tableau ASCII
        System.out.println("Tableau ASCII du mot :");
        for (int i=0;i<asciiValues.length;i++) {
            if(asciiValues[i]>=97 && asciiValues[i]<=122){
                asciiValues[i]=asciiValues[i]-96;

            }
            else{
                asciiValues[i]=asciiValues[i]-64;

            }
        }
        return asciiValues;
    }

    public static int[] cryptageMotUn(int[] motI,int clef[]) {
        for(int i=0;i<motI.length;i++){
            motI[i]=motI[i]+clef[i];
        }
        afficheTabInt(motI,"mot crypté en ascii");
        return motI;
    }
    public static String inverseCryptageMotUn(int[] motCrypte, int clef[]) {
        String mot;
        for(int i=0;i<motCrypte.length;i++){
            motCrypte[i]=motCrypte[i]-clef[i];
        }
        mot=asciiToString(motCrypte);
        return mot;
    }

    public static void afficheTabInt(int[] tab,String titre) {
        System.out.println(titre);
        for (int num : tab) {
            System.out.print(num + " ");
        }
        System.out.println("");

    }

    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);
        System.out.print("Mot à crypté: ");
        String mot = scanner.nextLine();

        int[] motI=stringtoAscii(mot);
        List<Integer> liste = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            liste.add(i+1);
            
        }
        
        Collections.shuffle(liste,new Random());
        int[] deck = new int[54];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = liste.get(i);
        }
        afficheTabInt(deck,"Tableau mélangé :");
        int[] deck2 = Arrays.copyOf(deck, deck.length);
        
        
        int[] clef= new int[motI.length];
        
        clef=etape5(deck,motI.length);

        afficheTabInt(clef,"La clef :");

        motI=cryptageMotUn(motI,clef);
        mot=asciiToString(motI);
        System.out.println("mot crypté : "+mot);

        afficheTabInt(deck2,"deck avant décryptage :");
        int[] clef2=etape5(deck2,motI.length);
        afficheTabInt(clef2,"La clef2 :");
        mot=inverseCryptageMotUn(motI,clef2);
        System.out.println("mot décrypté : "+mot);

        }
    }