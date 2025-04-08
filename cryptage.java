import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class cryptage {
    public static String nomFichier="log/";

    public static int[] etape5(int[] deck,int lMot) {
        deck=unAquatre(deck);
        int n=0;
        int k;
        int[] clef = new int[lMot];
        boolean b =false;
        onLog("debut étape 5");
        for(int i=0;i<lMot;i++){
            onLog("lettre numero "+i);
            while(b==false){
                k=deck[n];
                afficheTabInt(deck,"etat deck");
                onLog("valeur carte au dessus "+k);
                if(k==53 || k==54){
                    onLog("le joker ");
                    deck=unAquatre(deck);

                }
                else{
                    clef[i]=deck[k]%26;
                    b=true;
                    onLog("c'est bon lettre trouver pour la clef : "+clef[i] + " de base "+deck[k]);
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
    public static void onLog(String texte) {
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier, true))) {
            writer.write(texte);
            writer.newLine(); // Ajoute une nouvelle ligne
        } catch (IOException e) {
            System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
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
        int charValue = mot[i] + 96;  
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
        onLog("Tableau ASCII du mot :");
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
            motI[i] = (motI[i] + clef[i] - 1) % 26 + 1;
        }
        
        afficheTabInt(motI,"mot crypté en ascii");
        return motI;
    }
    public static String inverseCryptageMotUn(int[] motCrypte, int clef[]) {
        String mot;
        for(int i=0;i<motCrypte.length;i++){
            motCrypte[i] = (motCrypte[i] - clef[i] - 1 + 26) % 26 + 1;
        }
        
        mot=asciiToString(motCrypte);
        return mot;
    }

    public static void afficheTabInt(int[] tab,String titre) {
        onLog(titre);
        String tabTempo="";
        for (int num : tab) {
            tabTempo += Integer.toString(num)+" ";
        }
        onLog(tabTempo);

    }
    public static int[] genDeck(){

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

        return deck;
    }

    public static String CryptageAvecDeck(String mot,int[] deck) {  
        
        
        nomFichier = nomFichier+mot.charAt(0)+"_Log.txt";
        onLog("Mot à crypté:");
        onLog(mot);
        
        
        int[] motI=stringtoAscii(mot);

        int[] clef= new int[motI.length];
        
        clef=etape5(deck,motI.length);

        afficheTabInt(clef,"La clef :");

        motI=cryptageMotUn(motI,clef);
        mot=asciiToString(motI);
        onLog("mot crypté : "+mot);
        System.out.println("mot Crypté :"+mot);

        System.out.println(mot);
        System.out.println(motI);
        System.out.println(deck);

        return mot;

        }

        public static String DecryptageAvecDeck(String mot ,int [] deck){
        onLog("mot load :");
        onLog(mot);
        int[] motI=stringtoAscii(mot);
   

        mot="";
        afficheTabInt(deck,"deck avant décryptage :");
        int[] clef2=etape5(deck,motI.length);
        afficheTabInt(clef2,"La clef2 :");
        mot=inverseCryptageMotUn(motI,clef2);
        onLog("mot décrypté : "+mot);
        System.out.println("mot décrypté : "+mot);
        System.out.println("Détail à voir dans le fichier log : "+nomFichier);

        return mot;

        }
    }
    
