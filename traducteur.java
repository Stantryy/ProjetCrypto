public class traducteur {
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
            cryptage.onLog("Tableau ASCII du mot :");
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
        public static void afficheTabInt(int[] tab,String titre) {
            cryptage.onLog(titre);
            String tabTempo="";
            for (int num : tab) {
                tabTempo += Integer.toString(num)+" ";
            }
            cryptage.onLog(tabTempo);
    
        }

        public static String deckToString(int[] deck) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < deck.length; i++) {
                sb.append(deck[i]);
                if (i < deck.length - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    
        public static int[] stringToDeck(String deckString) {
            String[] parts = deckString.split(",");
            int[] deck2 = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                deck2[i] = Integer.parseInt(parts[i].trim());
            }
            return deck2;
        }
    
}
