/*

 CrackedWords.java

 This stores the list of words being cracked in a hash.

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
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrackedWords {

    private HashMap<Character, ArrayList<String>> wordsToCrack = new HashMap<>();

    String singleLineCodeFileName = "";

    CrackedWords(String DictBreakFileName) {

        MakeSingleLineFile(DictBreakFileName);
        BuildHashArray();

    }

    public int GetHashSize() {
        return wordsToCrack.size();
    }

    public int GetNumArrElements(char x) {
        return wordsToCrack.get(x).size();

    }

    public int GetLengthKeyElements(char x, int i) {
        return wordsToCrack.get(x).get(i).length();

    }

    public String GetValue(char x, int i) {
        return wordsToCrack.get(x).get(i);

    }

    private void BuildHashArray() {
        ArrayList<String> aArr = new ArrayList<>();
        ArrayList<String> bArr = new ArrayList<>();
        ArrayList<String> cArr = new ArrayList<>();
        ArrayList<String> dArr = new ArrayList<>();
        ArrayList<String> eArr = new ArrayList<>();
        ArrayList<String> fArr = new ArrayList<>();
        ArrayList<String> gArr = new ArrayList<>();
        ArrayList<String> hArr = new ArrayList<>();
        ArrayList<String> iArr = new ArrayList<>();
        ArrayList<String> jArr = new ArrayList<>();
        ArrayList<String> kArr = new ArrayList<>();
        ArrayList<String> lArr = new ArrayList<>();
        ArrayList<String> mArr = new ArrayList<>();
        ArrayList<String> nArr = new ArrayList<>();
        ArrayList<String> oArr = new ArrayList<>();
        ArrayList<String> pArr = new ArrayList<>();
        ArrayList<String> qArr = new ArrayList<>();
        ArrayList<String> rArr = new ArrayList<>();
        ArrayList<String> sArr = new ArrayList<>();
        ArrayList<String> tArr = new ArrayList<>();
        ArrayList<String> uArr = new ArrayList<>();
        ArrayList<String> vArr = new ArrayList<>();
        ArrayList<String> wArr = new ArrayList<>();
        ArrayList<String> xArr = new ArrayList<>();
        ArrayList<String> yArr = new ArrayList<>();
        ArrayList<String> zArr = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader((new FileReader(singleLineCodeFileName)));

            int i = 0;
            String s = null;

            System.out.print("\nAnalyzing Coded File.");
            while ((s = br.readLine()) != null) {

                if (i++ % 150000 == 0) {
                    System.out.print(".");

                }

                if (s.charAt(0) == 'A') {
                    if (!isItAllCapitals(s)) {
                        aArr.add(s);
                    }
                } else if (s.charAt(0) == 'B') {
                    if (!isItAllCapitals(s)) {
                        bArr.add(s);
                    }
                } else if (s.charAt(0) == 'C') {
                    if (!isItAllCapitals(s)) {
                        cArr.add(s);
                    }
                } else if (s.charAt(0) == 'D') {
                    if (!isItAllCapitals(s)) {
                        dArr.add(s);
                    }
                } else if (s.charAt(0) == 'E') {
                    if (!isItAllCapitals(s)) {
                        eArr.add(s);
                    }
                } else if (s.charAt(0) == 'F') {
                    if (!isItAllCapitals(s)) {
                        fArr.add(s);
                    }
                } else if (s.charAt(0) == 'G') {
                    if (!isItAllCapitals(s)) {
                        gArr.add(s);
                    }
                } else if (s.charAt(0) == 'H') {
                    if (!isItAllCapitals(s)) {
                        hArr.add(s);
                    }
                } else if (s.charAt(0) == 'I') {
                    if (!isItAllCapitals(s)) {
                        iArr.add(s);
                    }
                } else if (s.charAt(0) == 'J') {
                    if (!isItAllCapitals(s)) {
                        jArr.add(s);
                    }
                } else if (s.charAt(0) == 'K') {
                    if (!isItAllCapitals(s)) {
                        kArr.add(s);
                    }
                } else if (s.charAt(0) == 'L') {
                    if (!isItAllCapitals(s)) {
                        lArr.add(s);
                    }
                } else if (s.charAt(0) == 'M') {
                    if (!isItAllCapitals(s)) {
                        mArr.add(s);
                    }
                } else if (s.charAt(0) == 'N') {
                    if (!isItAllCapitals(s)) {
                        nArr.add(s);
                    }
                } else if (s.charAt(0) == 'O') {
                    if (!isItAllCapitals(s)) {
                        oArr.add(s);
                    }
                } else if (s.charAt(0) == 'P') {
                    if (!isItAllCapitals(s)) {
                        pArr.add(s);
                    }
                } else if (s.charAt(0) == 'Q') {
                    if (!isItAllCapitals(s)) {
                        qArr.add(s);
                    }
                } else if (s.charAt(0) == 'R') {
                    if (!isItAllCapitals(s)) {
                        rArr.add(s);
                    }
                } else if (s.charAt(0) == 'S') {
                    if (!isItAllCapitals(s)) {
                        sArr.add(s);
                    }
                } else if (s.charAt(0) == 'T') {
                    if (!isItAllCapitals(s)) {
                        tArr.add(s);
                    }
                } else if (s.charAt(0) == 'U') {
                    if (!isItAllCapitals(s)) {
                        uArr.add(s);
                    }
                } else if (s.charAt(0) == 'V') {
                    if (!isItAllCapitals(s)) {
                        vArr.add(s);
                    }
                } else if (s.charAt(0) == 'W') {
                    if (!isItAllCapitals(s)) {
                        wArr.add(s);
                    }
                } else if (s.charAt(0) == 'X') {
                    if (!isItAllCapitals(s)) {
                        xArr.add(s);
                    }
                } else if (s.charAt(0) == 'Y') {
                    if (!isItAllCapitals(s)) {
                        yArr.add(s);
                    }
                } else if (s.charAt(0) == 'Z') {
                    if (!isItAllCapitals(s)) {
                        zArr.add(s);
                    }
                }

            }

            System.out.println("");

            br.close();

            Collections.sort(aArr, Comparator.comparing(String::length));
            Collections.sort(bArr, Comparator.comparing(String::length));
            Collections.sort(cArr, Comparator.comparing(String::length));
            Collections.sort(dArr, Comparator.comparing(String::length));
            Collections.sort(eArr, Comparator.comparing(String::length));
            Collections.sort(fArr, Comparator.comparing(String::length));
            Collections.sort(gArr, Comparator.comparing(String::length));
            Collections.sort(hArr, Comparator.comparing(String::length));
            Collections.sort(iArr, Comparator.comparing(String::length));
            Collections.sort(jArr, Comparator.comparing(String::length));
            Collections.sort(kArr, Comparator.comparing(String::length));
            Collections.sort(lArr, Comparator.comparing(String::length));
            Collections.sort(mArr, Comparator.comparing(String::length));
            Collections.sort(nArr, Comparator.comparing(String::length));
            Collections.sort(oArr, Comparator.comparing(String::length));
            Collections.sort(pArr, Comparator.comparing(String::length));
            Collections.sort(qArr, Comparator.comparing(String::length));
            Collections.sort(rArr, Comparator.comparing(String::length));
            Collections.sort(sArr, Comparator.comparing(String::length));
            Collections.sort(tArr, Comparator.comparing(String::length));
            Collections.sort(uArr, Comparator.comparing(String::length));
            Collections.sort(vArr, Comparator.comparing(String::length));
            Collections.sort(wArr, Comparator.comparing(String::length));
            Collections.sort(xArr, Comparator.comparing(String::length));
            Collections.sort(yArr, Comparator.comparing(String::length));
            Collections.sort(zArr, Comparator.comparing(String::length));

            wordsToCrack.put('A', aArr);
            wordsToCrack.put('B', bArr);
            wordsToCrack.put('C', cArr);
            wordsToCrack.put('D', dArr);
            wordsToCrack.put('E', eArr);
            wordsToCrack.put('F', fArr);
            wordsToCrack.put('G', gArr);
            wordsToCrack.put('H', hArr);
            wordsToCrack.put('I', iArr);
            wordsToCrack.put('J', jArr);
            wordsToCrack.put('K', kArr);
            wordsToCrack.put('L', lArr);
            wordsToCrack.put('M', mArr);
            wordsToCrack.put('N', nArr);
            wordsToCrack.put('O', oArr);
            wordsToCrack.put('P', pArr);
            wordsToCrack.put('Q', qArr);
            wordsToCrack.put('R', rArr);
            wordsToCrack.put('S', sArr);
            wordsToCrack.put('T', tArr);
            wordsToCrack.put('U', uArr);
            wordsToCrack.put('V', vArr);
            wordsToCrack.put('W', wArr);
            wordsToCrack.put('X', xArr);
            wordsToCrack.put('Y', yArr);
            wordsToCrack.put('Z', zArr);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void MakeSingleLineFile(String DictionaryBreakFileName) {

        String s = null;
        String tmp = null;
        String[] j = null;

        ArrayList<String> tmpHolder = new ArrayList<>();

        try {
            BufferedReader aBR = new BufferedReader(new FileReader(DictionaryBreakFileName));
            BufferedWriter aBW = new BufferedWriter(new FileWriter("SingleLineCodedFile.txt"));

            while (((s = aBR.readLine()) != null)) {

                tmp = s.replaceAll("[[\\.:;,!?] | [-] | [ \"] | [.]]", " ");
                j = tmp.split(" ");

                for (int x = 0; x < j.length; x++) {
                    tmpHolder.add(j[x]);
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

            singleLineCodeFileName = "SingleLineCodedFile.txt";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrackedWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isItAllCapitals(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                return false;
            }

        }

        return true;

    }

    public void CanAutoSolve() {

        int count = 1;

        System.out.println("AutoSolvePrint:\n\n");
        for (int j = 0; j < GetHashSize(); j++) {

            for (int i = 0; i < GetNumArrElements((char) (j + 65)); i++) {
                System.out.println(count++ + ". " + GetValue((char) (j + 65), i));
            }

        }

    }

    public void PrintKnownFirstLetter() {

        int count = 1;

        for (int j = 0; j < GetHashSize(); j++) {
            for (int i = 0; i < GetNumArrElements((char) (j + 65)); i++) {
                System.out.println(count++ + ". " + GetValue((char) (j + 65), i));
            }
        }

    }

    public boolean CheckIfSolved() {

        int count = 0;

        for (int j = 0; j < GetHashSize(); j++) {

            for (int i = 0; i < GetNumArrElements((char) (j + 65)); i++) {

                count++;
            }
        }

        if (count > 0) {
            return false;
        }

        return true;

    }

    public String GetWordSelectedFromMenu(int intWordToGet) {

        int count = 1;
        for (int j = 0; j < GetHashSize(); j++) {
            for (int i = 0; i < GetNumArrElements((char) (j + 65)); i++) {

                if (count++ == intWordToGet) {
                    return GetValue((char) (j + 65), i);
                }
            }
        }
        return null;

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
}
