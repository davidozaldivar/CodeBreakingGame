/*

 Dictionary.java

 This stores the dictionary file in a hash.

 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {

    private HashMap<Character, ArrayList<String>> dictionaryHM = new HashMap<>();

    Dictionary() {
        BuildHashArray();
    }

    public int GetHashSize() {
        return dictionaryHM.size();
    }

    public int GetNumArrElements(char x) {
        return dictionaryHM.get(x).size();

    }

    public int GetLengthKeyElements(char x, int i) {
        return dictionaryHM.get(x).get(i).length();

    }

    public String GetValue(char x, int i) {
        return dictionaryHM.get(x).get(i);

    }

    //Dictionary will be made into a hash array of Char, ArrayList<String>.  Each arrayList will be sorted alpbabetically
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
            BufferedReader br = new BufferedReader((new FileReader("Dict.txt")));

            int i = 0;
            String s = null;

            System.out.print("\nAnalyzing Dictionary.");
            while ((s = br.readLine()) != null) {

                if (i++ % 150000 == 0) {
                    System.out.print(".");

                }

                if (s.toLowerCase().charAt(0) == 'a') {
                    aArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'b') {
                    bArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'c') {
                    cArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'd') {
                    dArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'e') {
                    eArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'f') {
                    fArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'g') {
                    gArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'h') {
                    hArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'i') {
                    iArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'j') {
                    jArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'k') {
                    kArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'l') {
                    lArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'm') {
                    mArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'n') {
                    nArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'o') {
                    oArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'p') {
                    pArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'q') {
                    qArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'r') {
                    rArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 's') {
                    sArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 't') {
                    tArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'u') {
                    uArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'v') {
                    vArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'w') {
                    wArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'x') {
                    xArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'y') {
                    yArr.add(s.toLowerCase());
                } else if (s.toLowerCase().charAt(0) == 'z') {
                    zArr.add(s.toLowerCase());
                }

            }

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

            dictionaryHM.put('A', aArr);
            dictionaryHM.put('B', bArr);
            dictionaryHM.put('C', cArr);
            dictionaryHM.put('D', dArr);
            dictionaryHM.put('E', eArr);
            dictionaryHM.put('F', fArr);
            dictionaryHM.put('G', gArr);
            dictionaryHM.put('H', hArr);
            dictionaryHM.put('I', iArr);
            dictionaryHM.put('J', jArr);
            dictionaryHM.put('K', kArr);
            dictionaryHM.put('L', lArr);
            dictionaryHM.put('M', mArr);
            dictionaryHM.put('N', nArr);
            dictionaryHM.put('O', oArr);
            dictionaryHM.put('P', pArr);
            dictionaryHM.put('Q', qArr);
            dictionaryHM.put('R', rArr);
            dictionaryHM.put('S', sArr);
            dictionaryHM.put('T', tArr);
            dictionaryHM.put('U', uArr);
            dictionaryHM.put('V', vArr);
            dictionaryHM.put('W', wArr);
            dictionaryHM.put('X', xArr);
            dictionaryHM.put('Y', yArr);
            dictionaryHM.put('Z', zArr);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
