/*

 HashFileOperations.java

 Abstract Class, with FileNameHelper implemented.

 */

import java.io.File;
import java.util.Scanner;

public abstract class HashFileOperations {

    abstract void LoadFile();

    abstract void Print();

    //so children classes can use
    protected String FileNameHelper(String fName, boolean itShouldExists) {

        System.out.println("Type ! to exit");
        Scanner kb = new Scanner(System.in);
        String fileName = null;

        if (itShouldExists) {

            do {

                System.out.printf("Please enter %s: ", fName);
                fileName = kb.next();

                if (fileName.matches("[!]")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }

            } while (!fileName.endsWith(".txt") || !(new File(fileName).exists()));

        } else {

            do {

                System.out.printf("Please enter %s: ", fName);
                fileName = kb.next();

                if (fileName.matches("[!]")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                } else if (new File(fileName).exists()) {
                    System.out.println("File already exists, please enter a different file name.");
                }

            } while (!fileName.endsWith(".txt") || (new File(fileName).exists()));

        }

        return fileName;

    }

}
