/*

Subkey.java


 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubKey extends HashFileOperations {

    HashMap<Character, Character> subKeyHash = new HashMap<>();

    SubKey() {

    }

    @Override
    public void LoadFile() {

        String[] firstLine = new String[26];
        String[] secondLine = new String[26];

        String s;

        //read file, put in arrays
        try {

            BufferedReader subKeyBR = new BufferedReader((new FileReader(FileNameHelper("a Substitution Key", true))));

            int i = 0;
            while ((s = subKeyBR.readLine()) != null) {

                if (i == 0) {
                    firstLine = s.split("(?!^)");

                    i++;
                } else if (i == 1) {
                    secondLine = s.split("(?!^)");

                    i++;
                } else {
                    System.out.println("More than 2 lines in your subkey file..Bye!");
                    System.exit(2);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound in SubKey.java");
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("IOException in SubKey.java");
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check if length matches - change this
        if (firstLine.length != secondLine.length) {
            System.out.println("Your SubKey substitution Lengths don't match!!\nPlease fix"
                    + " and try again!");
            System.exit(3);
        }

        for (int i = 0; i < firstLine.length; i++) {
            subKeyHash.put(firstLine[i].charAt(0), secondLine[i].charAt(0));
        }

        System.out.println("\nFile loaded successfully.");

    }

    public void Print() {

        System.out.println("\nLoaded Substitution Key: \n");

        for (int i = 97; i <= 122; i++) {

            System.out.println("\t" + (char) i + " = " + subKeyHash.get((char) i));

        }

    }
}
