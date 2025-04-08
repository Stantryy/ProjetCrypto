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
    
}
