/*

 FrequencyFile.java

 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrequencyFile extends HashFileOperations {

    HashMap<Character, Double> freqFileHash = new HashMap<>();

    @Override
    public void LoadFile() {

        String s = null;

        String[] letter = new String[26];
        String[] number = new String[26];

        String[] splitArr = null;

        try {

            BufferedReader freqFileBR = new BufferedReader((new FileReader(this.FileNameHelper("a frequency file name", true))));

            int i = 0;
            while ((s = freqFileBR.readLine()) != null) {

                splitArr = s.split(" ");

                letter[i] = splitArr[0];
                number[i] = splitArr[1];

                i++;

            }

            freqFileBR.close();

        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < letter.length; i++) {
            Double numDouble = new Double(number[i]);

            freqFileHash.put(letter[i].charAt(0), numDouble);
        }

        Print();

    }

    @Override
    void Print() {

        System.out.println("\n\nFrequencies Read from file: \n");

        for (int i = 97; i <= 122; i++) {

            System.out.println("\t" + (char) i + " = " + freqFileHash.get((char) i));

        }

        System.out.println("\n");

    }

}
