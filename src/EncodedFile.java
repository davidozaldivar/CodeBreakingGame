/*

 EncodedFile.java


 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncodedFile extends HashFileOperations {

    HashMap<Character, Double> encodedFileHash = new HashMap<>();
    HashMap<Character, Character> subKeyHash = new HashMap<>();
    String encodedFileName = " ";
    String subLettersFileName = " ";
    String originalText = " ";

    String tmpEncoded = " ";

    EncodedFile() {
    }

    public void SetSubKey(SubKey subKeyObj) {
        this.subKeyHash = subKeyObj.subKeyHash;
    }

    @Override
    public void LoadFile() {
        ///undelete
        originalText = FileNameHelper("a file to encode", true);

        try {

            //starting file
            BufferedReader readFileBR = new BufferedReader((new FileReader(originalText)));
            encodedFileName = FileNameHelper("a file to save coded message to", false);

            BufferedWriter writerFileBW = new BufferedWriter((new FileWriter(encodedFileName)));

            int i = 0;

            while ((i = readFileBR.read()) != -1) {

                char iChar = (char) i;

                if ((char) i == ' ') {

                    writerFileBW.write(" ");

                } else if (Character.isAlphabetic(i)) {

                    writerFileBW.write((char) subKeyHash.get(Character.toLowerCase((char) i)));

                } else if ((char) i == '\n') {

                    writerFileBW.newLine();
                } else if ((char) i == '\'') {
                    writerFileBW.write("'");
                } else if ((char) i == '-') {
                    writerFileBW.write("-");
                }

            }

            System.out.println("\nFile written. ");

            readFileBR.close();
            writerFileBW.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Print() {

        System.out.println("\n\nFrequencies computed from coded file: \n");

        for (int i = 97; i <= 122; i++) {

            System.out.println("\t" + (char) i + " = " + encodedFileHash.get((char) i));

        }

        System.out.println("\n");

    }

    public void ComputeFrequency() {

        int[][] arrCount = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0},
        {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};

        int tmp = 0;

        try {

            BufferedReader codedFileBR = new BufferedReader((new FileReader(encodedFileName)));
            System.out.println("Computing frequency of " + encodedFileName);

            int i = 0;
            int letCount = 0;
            while ((i = codedFileBR.read()) != -1) {

                if ((char) i == ' ') {
                    letCount++;
                }

                if ((char) i >= 'a' && (char) i <= 'z') {

                    i = i - 97;

                    tmp = arrCount[i][0];
                    arrCount[i][0] = tmp + 1;

                    tmp = 0;
                    letCount++;

                }

            }

            Double tmpDouble = null;
            String tmpStr1 = " ";
            String tmpStr2 = " ";

            for (int j = 0; j <= 25; j++) {

                tmpDouble = ((double) arrCount[j][0] / (double) letCount) * 100;

                tmpStr1 = String.valueOf(tmpDouble);

                tmpStr2 = String.format(" %.2f", Double.parseDouble(tmpStr1));

                encodedFileHash.put((char) (j + 97), Double.valueOf(tmpStr2));

            }

            codedFileBR.close();

        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Print();

    }

    public String GetOriginalFileName() {
        return originalText;
    }

    public String GetFileName() {
        return encodedFileName;
    }

    public String MakeNewTempFileName(String oldFN) {

        return oldFN.replace(".txt", "_tmp.txt");

    }

    public void SetTmpEncoded(String tmp) {
        tmpEncoded = tmp;
    }

    public String GetTmpEncoded() {
        return tmpEncoded;
    }

    public String GetSubLetterFileName() {
        return subLettersFileName;
    }

    public void SubstituteTopLetters() {

        String strNumToReplace = null;
        int numToReplace = 0;
        boolean itsGood = false;
        Scanner kb = new Scanner(System.in);
        BufferedWriter aBW = null;

        subLettersFileName = this.MakeNewTempFileName(this.GetFileName());

        try {
            aBW = new BufferedWriter(new FileWriter(subLettersFileName));
        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
            Sort a hash map from:
            https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
        
         */
        //Gets the computed frequencies, places in list
        List<Map.Entry<Character, Double>> encodedList = new LinkedList<>(encodedFileHash.entrySet());

        //sorts list by doubled value:  - should i make menu option to see ordered frequencies? 
        Collections.sort(encodedList, (Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2) -> (o2.getValue()).compareTo(o1.getValue()));

        //Place sorted values in a hash, for faster character replacement.  This or encodedList may be redundant? 
        HashMap<Character, Double> encodedSortedByVal = new LinkedHashMap<>();

        for (Map.Entry<Character, Double> x : encodedList) {
            encodedSortedByVal.put(x.getKey(), x.getValue());
        }

        do {
            System.out.print("How many letters do you want to replace? ");

            strNumToReplace = kb.nextLine();

            if (Integer.parseInt(strNumToReplace) >= 0 && Integer.parseInt(strNumToReplace) <= 26) {

                itsGood = true;
                numToReplace = Integer.parseInt(strNumToReplace);

            }

        } while (!(itsGood));

        System.out.println("");

        /*
            https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        
        
         */
        HashMap<Character, Double> topEncoded = new HashMap<>();

        HashMap<Character, Character> reverseSubKey = new HashMap<>();

        /*
        
            4/20 - changed subKeyHash.entrySet to encodedSortedByVal.entrySet - didn't work
         */
        //Get a reverse copy of the subKey!! 
        //for (Map.Entry mapElement : subKeyHash.entrySet()) {
        for (Map.Entry mapElement : subKeyHash.entrySet()) {

            reverseSubKey.put((char) mapElement.getValue(), (char) mapElement.getKey());
        }

        //Get top values user requested
        int i = 0;
        for (Map.Entry mapElement : encodedSortedByVal.entrySet()) {

            if (i++ < numToReplace) {

                topEncoded.put((char) mapElement.getKey(), (double) mapElement.getValue());
            } else {
                break;
            }

        }

        /*
            Start replacing coded text file with top results..
        
         */
        try {

            BufferedReader codedFileBR = new BufferedReader((new FileReader(this.GetFileName())));

            int k = 0;

            while ((k = codedFileBR.read()) != -1) {

                if (topEncoded.containsKey((char) k)) {
                    System.out.print(Character.toUpperCase(reverseSubKey.get((char) k)));
                    aBW.write(Character.toUpperCase(reverseSubKey.get((char) k)));

                } else if (k == ' ') {
                    System.out.print(" ");
                    aBW.write(" ");
                } else if (k == '\n') {
                    System.out.println("");
                    aBW.newLine();
                } else {
                    System.out.print((char) k);
                    aBW.write((char) k);
                }
            }

            aBW.close();

            if (numToReplace == 26) {
                System.out.println("\nSolved!");
                CompareTextFiles(true, GetSubLetterFileName());
                System.exit(0);
            }

        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //keeps track of decoded progress
    public String MakeSingleLineFile(String fileNameOrig, String fileNameSL) {

        String s = null;
        String tmp = null;
        String[] j = null;

        ArrayList<String> tmpHolder = new ArrayList<>();

        try {
            BufferedReader aBR = new BufferedReader(new FileReader(fileNameOrig));
            BufferedWriter aBW = new BufferedWriter(new FileWriter(fileNameSL));

            while (((s = aBR.readLine()) != null)) {

                tmp = s.replaceAll("[[\\.:;,!?] | [-] | [ \"] | [.]]", " ");
                j = tmp.split(" ");

                for (int x = 0; x < j.length; x++) {
                    tmpHolder.add(j[x].toLowerCase());
                }

            }

            aBR.close();

            String[] m = new String[tmpHolder.size()];
            m = tmpHolder.toArray(m);

            Arrays.sort(m);

            for (int i = 0; i < m.length; i++) {

                if (i > 0 && m[i].compareTo(m[i - 1]) != 0) {

                    aBW.write(m[i]);

                    aBW.newLine();
                }

            }

            aBW.close();

            tmpHolder.clear();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(EncodedFile.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {

            Logger.getLogger(EncodedFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileNameSL;
    }

    public double CompareTextFiles(boolean PrintNotCorrectWords, String subFileName) {

        String originalSL = MakeSingleLineFile(GetOriginalFileName(), "originalSL.txt");

        String subSL = MakeSingleLineFile(subFileName, "subLetSL.txt");

        ArrayList<String> Orig = new ArrayList<>();
        ArrayList<String> Sub = new ArrayList<>();
        double notCorrectCount = 0;
        double total = 0;

        try {
            BufferedReader originalTextBR = new BufferedReader((new FileReader(originalSL)));
            BufferedReader subTextBR = new BufferedReader((new FileReader(subSL)));

            String i = null;
            String j = null;

            while ((i = originalTextBR.readLine()) != null) {

                Orig.add(i);

            }

            while ((j = subTextBR.readLine()) != null) {

                Sub.add(j);

            }

            for (int k = 0; k < Orig.size(); k++) {
                total++;
                if (!(Sub.contains(Orig.get(k)))) {
                    notCorrectCount++;

                    if (PrintNotCorrectWords) {
                        System.out.println("Not Correct: " + Orig.get(k));
                    }
                }

            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (100 - (notCorrectCount / total) * 100);

    }

}
