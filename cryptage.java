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

    public static String nomFichier="";

    public static int[] cryptageMotUn(int[] motI,int clef[]) {
        for(int i=0;i<motI.length;i++){
            motI[i] = (motI[i] + clef[i] - 1) % 26 + 1;
        }
        
        traducteur.afficheTabInt(motI,"mot crypté en ascii");
        return motI;
    }
    public static String inverseCryptageMotUn(int[] motCrypte, int clef[]) {
        String mot;
        for(int i=0;i<motCrypte.length;i++){
            motCrypte[i] = (motCrypte[i] - clef[i] - 1 + 26) % 26 + 1;
        }
        
        mot=traducteur.asciiToString(motCrypte);
        return mot;
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
        traducteur.afficheTabInt(deck,"Tableau mélangé :");

        return deck;
    }
    public static void onLog(String texte) {
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier, true))) {
            writer.write(texte);
            writer.newLine(); // Ajoute une nouvelle ligne
        } catch (IOException e) {
            //System.err.println("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
    public static int[] genClef(String mot,int[] deck){
        nomFichier="log/"+mot.charAt(0)+"_Log.txt";
        onLog("Mot à crypté:");
        onLog(mot);

        int[] clef= new int[mot.length()];
        
        clef=manipDeck.etape5(deck,mot.length());

        traducteur.afficheTabInt(clef,"La clef :");

        return clef;
    }
    public static String CryptageAvecDeck(String mot,int[] deck,int[] clef) {  
        
        nomFichier="log/"+mot.charAt(0)+"_Log.txt";
        int[] motI=traducteur.stringtoAscii(mot);

        motI=cryptageMotUn(motI,clef);
        mot=traducteur.asciiToString(motI);
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
        int[] motI=traducteur.stringtoAscii(mot);
   

        mot="";
        traducteur.afficheTabInt(deck,"deck avant décryptage :");
        int[] clef2=manipDeck.etape5(deck,motI.length);
        traducteur.afficheTabInt(clef2,"La clef2 :");
        mot=inverseCryptageMotUn(motI,clef2);
        onLog("mot décrypté : "+mot);
        System.out.println("mot décrypté : "+mot);
        System.out.println("Détail à voir dans le fichier log : "+nomFichier);

        return mot;

        }
    }
    
