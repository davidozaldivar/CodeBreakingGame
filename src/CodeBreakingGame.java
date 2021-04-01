/*

Code Breaking Game!
By: David Zaldivar


Files: 

    Included file to encode: 
        rs2.txt

    Included Frequency File:
        Frequency.txt
    
    Included Subkey file:
        Subkey.txt
    
    Included Dictionary:    
        Dict.txt



Purpose:  
    This program encodes a text file submitted by the user, based on a submitted subkey file.
    Once encoded, frequency analysis of the encoded file will be computed, and the user must choose how many 
    characters (with the highest frequency) to substitute (decode), following the included Frequency file.  

    Once the user has at least one substitution in place, they will have the option to attempt to decode 
    the file using the 'dictionary breaking menu.'  This will scan all words of the encoded file, and display
    a list of any where the first letter is solved. Once the user selects one of these words, possible matches
    from the dictionary will be displayed, and the user must select one, and decide if the changes it would make
    to the encoded file makes sense.  

    Once a single possible match is found for an encoded word in the dictionary, an 'Auto-solve' menu item pops up. 
    The user can choose to keep or discard these matches, as they decode the file.
    
    Encoded characters appear in lowercase
    Decoded characters appear in uppercase

   
To Fix: 
    1.  The frequency Analysis substitution is cheating, by using the SubKey to draw replacement 
        letters from, rather than the computed frequency.  
        Location: EncodedFile.SubstituteTopLetters

    2.  The very last "solved with: " word is incorrect, and showing for the word following the 
        one that actually solved it.  


    3. When not solved, 'You solved it!' is still popping up.  May stay as a morale boost.  

    




 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CodeBreakingGame {

    public static void main(String[] args) {

        SubKey subKeyObj = new SubKey();
        EncodedFile encodedFileObj = new EncodedFile();
        FrequencyFile frequencyFileObj = new FrequencyFile();

        //Flags for menu
        boolean isSubKeySubmitted = false;
        boolean isOriginalTextSubmitted = false;
        boolean isFreqFileSubmitted = false;
        boolean isFreqAlreadyComputed = false;
        boolean isLetterSubstituted = false;

        System.out.println("Program by David Zaldivar\n");

        Scanner kb = new Scanner(System.in);
        String strMenuOptions;

        try {

            while (true) {

                //receives main menu as string, with '%' as delimiter
                strMenuOptions = MainMenu(isSubKeySubmitted, isOriginalTextSubmitted, isFreqFileSubmitted, isFreqAlreadyComputed, isLetterSubstituted);

                //Create a menu object, pass in the menu string, which will get parsed and stored within the obj.
                Menu myMenu = new Menu(strMenuOptions);

                //returned string of menu option user selected
                String readOption = myMenu.runMenu();

                if (readOption.equalsIgnoreCase("Exit. ")) {
                    return;

                } else if (readOption.equalsIgnoreCase("Enter Substitution Key File. ")) {
                    isSubKeySubmitted = true;
                    subKeyObj.LoadFile();

                } else if (readOption.equalsIgnoreCase("View Substitution Key. ")) {
                    subKeyObj.Print();

                } else if (readOption.equalsIgnoreCase("Enter a text file to encode. ")) {

                    if (isSubKeySubmitted == false) {
                        System.out.println("Please enter Substitution File First");
                    } else {
                        isOriginalTextSubmitted = true;
                        encodedFileObj.SetSubKey(subKeyObj);
                        encodedFileObj.LoadFile();
                    }

                } else if (readOption.equalsIgnoreCase("Compute Frequency of Encoded File. ")) {
                    isFreqAlreadyComputed = true;
                    encodedFileObj.ComputeFrequency();

                } else if (readOption.equalsIgnoreCase("View Frequency of Encoded File. ")) {
                    encodedFileObj.Print();

                } else if (readOption.equalsIgnoreCase("Enter a Frequency File. ")) {
                    isFreqFileSubmitted = true;
                    frequencyFileObj.LoadFile();

                } else if (readOption.equalsIgnoreCase("View loaded Frequency Analysis File. ")) {
                    frequencyFileObj.Print();

                } else if (readOption.equalsIgnoreCase("Substitute top coded letters. ")) {
                    isLetterSubstituted = true;
                    encodedFileObj.SubstituteTopLetters();

                } else if (readOption.equalsIgnoreCase("Go to Dictionary Breaking Menu. ")) {

                    DictionaryBreakingManual(encodedFileObj);
                }

            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Illegal argument provided in Main!\n" + ex);
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("Null pointer exception Main" + ex);
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {

                if (Files.deleteIfExists(Paths.get(encodedFileObj.GetFileName())) && Files.deleteIfExists(Paths.get(encodedFileObj.GetSubLetterFileName()))
                        && Files.deleteIfExists(Paths.get("SingleLineCodedFile.txt"))) {
                    System.out.println("deleted successfully");
                }

            } catch (IOException ex) {
                Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /*
    
        
    MakeSingleLineFile can be deleted - but test more
    
    
    
     */
//    public String MakeSingleLineFile(String fileNameOrig, String fileNameSL) {
//
//        String s = null;
//        String tmp = null;
//        String[] j = null;
//
//        ArrayList<String> tmpHolder = new ArrayList<>();
//
//        try {
//            BufferedReader aBR = new BufferedReader(new FileReader(fileNameOrig));
//            BufferedWriter aBW = new BufferedWriter(new FileWriter(fileNameSL));
//
//            while (((s = aBR.readLine()) != null)) {
//
//                tmp = s.replaceAll("[^A-Z|a-z]", " ");
//                j = tmp.split(" ");
//
//                for (int x = 0; x < j.length; x++) {
//                    tmpHolder.add(j[x].toLowerCase());
//                }
//
//            }
//
//            aBR.close();
//
//            String[] m = new String[tmpHolder.size()];
//            m = tmpHolder.toArray(m);
//
//            Arrays.sort(m);
//
//            for (int i = 0; i < m.length; i++) {
//
//                if (i > 0 && m[i].compareTo(m[i - 1]) != 0) {
//
//                    aBW.write(m[i]);
//
//                    aBW.newLine();
//                }
//
//            }
//
//            aBW.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return fileNameSL;
//    }
    
    
    
    
    /*
    
        Auto Solving Menu.  
    
        This becomes an option if there exists an encoded word that has only one possible match
        in the dictionary. 
    
    */
    static void DictionaryBreakingAuto(EncodedFile encodedFileObj, Dictionary dictionaryHM, ArrayList<String> matchedWords) {

        System.out.println("Starting Autosolve...\n\n");
        String strDictWordSelected = " ";
        ArrayList<String> tmpWords = new ArrayList<>();
        String usrAns = " ";
        Scanner kb = new Scanner(System.in);
        String DictBreakFileName = encodedFileObj.GetSubLetterFileName();

        do {

            CrackedWords wordsToCrackHash = new CrackedWords(DictBreakFileName);
            String tmpStr = "";
            for (int i = 0; i < wordsToCrackHash.GetHashSize(); i++) {

                for (int j = 0; j < wordsToCrackHash.GetNumArrElements((char) (i + 65)); j++) {

                    tmpStr = wordsToCrackHash.GetValue((char) (i + 65), j);

                    tmpWords = MatchMaker(dictionaryHM, tmpStr, false);

                    if (tmpWords.size() == 1) {

                        strDictWordSelected = tmpWords.get(0);

                        WriteFiles(encodedFileObj, DictBreakFileName, tmpStr, strDictWordSelected, true);

                    }

                }

                if ((wordsToCrackHash.CheckIfSolved())) {
                    System.out.println("You solved it!!!");
                    encodedFileObj.CompareTextFiles(true, DictBreakFileName);
                    
                    System.exit(0);
                }

                
                tmpWords.clear();

            }

            System.out.print("again? ");
            usrAns = kb.nextLine();
        } while (usrAns.charAt(0) == 'y' || usrAns.charAt(0) == 'Y');

    }
    
    /*
        WriteFiles() - this is responsible for displaying the encoded/decoded text on screen.  It writes the decoded letters
        to a separate _tmp.txt file, and if the user wants to keep selection, this will be the file used.
        
    
    */
    

    static void WriteFiles(EncodedFile encodedFileObj, String DictBreakFileName, String wordSelected, String strDictWordSelected,
            boolean printDictWord) {

        Scanner kb = new Scanner(System.in);

        try {

            BufferedReader readFileBR = new BufferedReader((new FileReader(DictBreakFileName)));
            String tmpEncodedFileName = DictBreakFileName.replace(".txt", "_tmp.txt");

            encodedFileObj.SetTmpEncoded(tmpEncodedFileName);

            BufferedWriter writerFileBW = new BufferedWriter((new FileWriter(tmpEncodedFileName, false)));

            int i = 0;
            boolean wasReplaced = false;

            while ((i = readFileBR.read()) != -1) {

                for (int j = 0; j < wordSelected.length(); j++) {

                    if ((char) i == wordSelected.charAt(j)) {

                        wasReplaced = true;

                        writerFileBW.write(strDictWordSelected.toUpperCase().charAt(j));
                        break;
                    }
                }

                if (wasReplaced == false) {

                    if ((char) i == ' ') {
                        writerFileBW.write(" ");
                    } else if ((char) i == '\n') {
                        writerFileBW.newLine();
                    } else {
                        writerFileBW.write((char) i);
                    }

                }
                wasReplaced = false;

            }

            readFileBR.close();
            writerFileBW.close();

            BufferedReader newBR = new BufferedReader((new FileReader(tmpEncodedFileName)));

            i = 0;
            while ((i = newBR.read()) != -1) {

                System.out.print((char) i);
            }

            newBR.close();
            
            
            //Optional flag, displays the word solved with.
            if (printDictWord) {
                System.out.println("\n\nAbove was solved with: " + strDictWordSelected);
            }

            double percentCorrectCurr = encodedFileObj.CompareTextFiles(false, DictBreakFileName);
            double precentCorrectNew = encodedFileObj.CompareTextFiles(false, tmpEncodedFileName);

            System.out.printf("\n\nPrecent Currently Solved: %.2f%% %n", percentCorrectCurr);
            System.out.printf("Percent Solved with new word: %.2f%% %n", precentCorrectNew);

            if (percentCorrectCurr == 100) {
                encodedFileObj.CompareTextFiles(true, DictBreakFileName);
                System.out.println("You solved it!!\nWoohoo!!");
                System.exit(0);
                //return;
            }

            System.out.print("\n\nKeep selection? ");
            char yn = kb.next().charAt(0);

            //Overwrite dictbreakfile
            if (yn == 'y' || yn == 'Y') {
                Files.deleteIfExists(Paths.get(DictBreakFileName));
                Files.deleteIfExists(Paths.get("SingleLineCodedFile.txt"));
                BufferedReader newBR2 = new BufferedReader((new FileReader(tmpEncodedFileName)));
                BufferedWriter writerFileBW2 = new BufferedWriter((new FileWriter(DictBreakFileName)));

                i = 0;

                while ((i = newBR2.read()) != -1) {

                    if ((char) i == ' ') {
                        writerFileBW2.write(" ");
                    } else if ((char) i == '\n') {
                        writerFileBW2.newLine();
                    } else {
                        writerFileBW2.write((char) i);
                    }

                }

                newBR2.close();
                writerFileBW2.close();
                Files.deleteIfExists(Paths.get(tmpEncodedFileName));

            } else {
                Files.deleteIfExists(Paths.get(tmpEncodedFileName));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodeBreakingGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
        
        DictionaryBreakingManual - this is where Dictionary Breaking begins.  The loop
        will continue running, and if only one word in the dictionary matches a possible encoded letter,
        DictionaryBreakingAuto() will become available.  
    
    */

    static void DictionaryBreakingManual(EncodedFile encodedFileObj) {

        String DictBreakFileName = encodedFileObj.GetSubLetterFileName();
        

        boolean firstIteration = true;

        Dictionary dictionaryHM = new Dictionary();
        String strWordToGet = "";
        int intWordToGet = 0;

        Scanner kb = new Scanner(System.in);

        boolean canBeSolved = false;

        String yyn = " ";

        do {

            
            CrackedWords wordsToCrackHash = new CrackedWords(DictBreakFileName);
            ArrayList<String> matchedWords = new ArrayList<>();

            if ((wordsToCrackHash.CheckIfSolved()) && firstIteration == true) {
                System.out.println("Please Substitute some letters first");
                return;
            }

            firstIteration = false;

            if (CanBeAutoSolved(wordsToCrackHash, dictionaryHM)) {
                canBeSolved = true;
                System.out.println("\nFYI..this can be autosolved...\n! to autosolve");

            }

            if (!(wordsToCrackHash.CheckIfSolved())) {
                System.out.println("\nFound words with first letter known: \n");
                wordsToCrackHash.PrintKnownFirstLetter();
            }

            System.out.println("");

           
            if (wordsToCrackHash.CheckIfSolved()) {
                encodedFileObj.CompareTextFiles(true, DictBreakFileName);
                System.out.println("You solved it!!!");

                return;
            }

            if (canBeSolved) {
                System.out.println("\nAutoSolve Available!!...\nType ! to autosolve!");
            }

            System.out.println("\nSelect word to crack: ");

            strWordToGet = kb.nextLine();

            if (strWordToGet.matches("[!]")) {

                DictionaryBreakingAuto(encodedFileObj, dictionaryHM, matchedWords);
                System.out.println("bye!!");
                return;
            }

            intWordToGet = Integer.parseInt(strWordToGet);

            /*
            Find word user wants to crack, save to String
        
             */
            String wordSelected = wordsToCrackHash.GetWordSelectedFromMenu(intWordToGet);

            System.out.println("You selected: " + wordSelected);

            System.out.println("Show matching " + wordSelected.length() + " letter words in dictionary that start with " + wordSelected.charAt(0));

            matchedWords = MatchMaker(dictionaryHM, wordSelected, true);

            System.out.println("Num of matches: " + matchedWords.size());

            System.out.print("Select word: ");

            String strnum = kb.nextLine();

            int num = Integer.parseInt(strnum);
            String strDictWordSelected = matchedWords.get(num - 1);

            System.out.println("You selected: " + strDictWordSelected);
            System.out.println("coded word: " + wordSelected);

            WriteFiles(encodedFileObj, DictBreakFileName, wordSelected, strDictWordSelected, false);

            encodedFileObj.CompareTextFiles(false, DictBreakFileName); 
            System.out.print("Again? ");

            yyn = kb.nextLine();

        } while (yyn.charAt(0) == 'y' || yyn.charAt(0) == 'Y');

    }

    /*
        CanBeAutoSolved - runs through cracked word list, checks words against matching words returned from MatchMaker(),
        if only 1 possibility, returns true.  
    
    */
    static boolean CanBeAutoSolved(CrackedWords wordsToCrackHash, Dictionary dictionaryHM) {

        ArrayList<String> tmpWords = new ArrayList<>();

        String tmpStr = " ";
        for (int i = 0; i < wordsToCrackHash.GetHashSize(); i++) {

            for (int j = 0; j < wordsToCrackHash.GetNumArrElements((char) (i + 65)); j++) {

                tmpStr = wordsToCrackHash.GetValue((char) (i + 65), j);

                tmpWords = MatchMaker(dictionaryHM, tmpStr, false);
                if (tmpWords.size() == 1) {
                    return true;
                }

                tmpWords.clear();

            }

        }

        return false;

    }
    
    /*
        MatchMaker - this returns list of possible matching words from dictionary, against cracked word list.  
    
    */

    static ArrayList MatchMaker(Dictionary dictionaryHM, String wordSelected, boolean PrintWordsToo) {
        /*
        
            Runs through dictionary checking against wordSelected.  Stores matches in matchedWords
        
         */
        ArrayList<String> matchedWords = new ArrayList<>();

        boolean isAMatch = false;
        int count = 1;
        String unknownDictLetters = "";
        String unknownSelectedWordLetters = "";

        String dictWord = null;
        for (int i = 0; i < dictionaryHM.GetNumArrElements(wordSelected.charAt(0)); i++) {

            //only gives similar length
            if (wordSelected.length() == dictionaryHM.GetLengthKeyElements(wordSelected.charAt(0), i)) {

                dictWord = dictionaryHM.GetValue(wordSelected.charAt(0), i);

                //now check if UNKNOWN distinct # of words match
                for (int k = 0; k < dictWord.length(); k++) {

                    if (wordSelected.charAt(k) >= 97 && wordSelected.charAt(k) <= 122) {
                        unknownDictLetters = unknownDictLetters.concat(Character.toString(dictWord.charAt(k)));
                        unknownSelectedWordLetters = unknownSelectedWordLetters.concat(Character.toString(wordSelected.charAt(k)));
                    }

                }

                //check if TOTAL distinct word count matches as well as unsolved distinct word count
                if ((returnDistinct(dictWord) == returnDistinct(wordSelected))
                        && (doLowerCaseDistinctMatch(unknownSelectedWordLetters, unknownDictLetters))) {

                    //now check each word for matching letters
                    for (int j = 0; j < wordSelected.length(); j++) {

                        //only look at known matches 
                        if ((int) wordSelected.charAt(j) >= 65 && (int) wordSelected.charAt(j) <= 90) {

                            if (wordSelected.toLowerCase().charAt(j) == dictWord.toLowerCase().charAt(j)) {

                                isAMatch = true;
                            } else {
                                isAMatch = false;
                                break;
                            }

                        }

                        if (j == wordSelected.length() - 1) {
                            if (isAMatch) {
                                matchedWords.add(dictWord);
                                if (PrintWordsToo) {
                                    System.out.println(count++ + ". " + dictWord);
                                }
                                isAMatch = false;

                            }
                        }

                    }

                }

                unknownDictLetters = "";
                unknownSelectedWordLetters = "";

            }
        }

        return matchedWords;

    }

    /*
        doLowerCaseDistinctMatch - checks that lower case letters match 
    
    
    */
    static boolean doLowerCaseDistinctMatch(String wordSelected, String dictWord) {

        int aVal, bVal;

        for (int i = 0; i < wordSelected.length(); i++) {

            for (int j = i + 1; j < wordSelected.length(); j++) {

                //found matching letters
                if (wordSelected.charAt(i) == wordSelected.charAt(j)) {

                    aVal = (int) wordSelected.charAt(i) - (int) dictWord.charAt(i);
                    bVal = (int) wordSelected.charAt(j) - (int) dictWord.charAt(j);

                    if (aVal != bVal) {
                        return false;
                    }

                }

            }

        }

        return true;

    }

    static int returnDistinct(String n) {

        char[] ch1 = n.toCharArray();
        ArrayList<Character> chList = new ArrayList<>();

        for (char x : ch1) {
            chList.add(x);
        }
        Collections.sort(chList);

        for (int i = 0; i < chList.size(); i++) {

            for (int j = i + 1; j < chList.size(); j++) {

                if (chList.get(i) == chList.get(j)) {

                    chList.remove(j);
                    j--;

                }

            }
        }

        return chList.size();

    }
    
    /*
        Main Menu.  
    
        Note: I was experimenting with trying to make the menu dynamic (using flags).  While it makes
        the menu look cleaner, it makes OCP take a hit, as you have to now consider flags when adding other
        menu options to it.  
    
    */

    static String MainMenu(boolean isSubKeySubmitted, boolean isOriginalTextSubmitted,
            boolean isFreqFileSubmitted, boolean isFreqAlreadyComputed, boolean isLetterSubstituted) {

        String menuOptions = null;

        //SubKey
        String strSubKeyIsNotLoaded = "Enter Substitution Key File. %";
        String strSubKeyIsLoaded = "View Substitution Key. %";

        //Original Text
        String strOriginalTextIsNotLoaded = "Enter a text file to encode. %";
        String strViewFreqOfEncoded = "View Frequency of Encoded File. %";
        String strComputeFrequency = "Compute Frequency of Encoded File. %";

        //Frequency File
        String strSubFreqFile = "Enter a Frequency File. %";
        String strViewLoadedFrequency = "View loaded Frequency Analysis File. %";

        //substitute coded with freq
        String strSubCodeWithFreq = "Substitute top coded letters. %";
        String strDictionaryBreakMenu = "Go to Dictionary Breaking Menu. %";

        String strExit = "Exit. %";

        //SubKey
        if (isSubKeySubmitted == false) {
            menuOptions = strSubKeyIsNotLoaded;
        } else {
            menuOptions = strSubKeyIsLoaded;
        }

        //Original File
        if (isOriginalTextSubmitted == false) {
            menuOptions = menuOptions.concat(strOriginalTextIsNotLoaded);
        } else if (isOriginalTextSubmitted && isFreqAlreadyComputed == false) {

            menuOptions = menuOptions.concat(strComputeFrequency);
        } else if (isOriginalTextSubmitted && isFreqAlreadyComputed) {
            menuOptions = menuOptions.concat(strViewFreqOfEncoded);
        }

        if (isFreqFileSubmitted == false) {
            menuOptions = menuOptions.concat(strSubFreqFile);

        } else if (isFreqFileSubmitted) {
            menuOptions = menuOptions.concat(strViewLoadedFrequency);
        }

        if (isSubKeySubmitted && isOriginalTextSubmitted && isFreqFileSubmitted && isFreqAlreadyComputed) {
            menuOptions = menuOptions.concat(strSubCodeWithFreq);

            if (isLetterSubstituted) {
                menuOptions = menuOptions.concat(strDictionaryBreakMenu);
            }
        }

        menuOptions = menuOptions.concat(strExit);

        return menuOptions;

    }

}
