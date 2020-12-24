
// Program to demonstrate the set

import java.util.TreeSet;
import java.util.HashSet;
import java.util.Scanner;

// The class
public class set {

    // The main method
    public static void main(String[] Args) {

        // Create the Scanner for reading in our code
        Scanner sc = new Scanner(System.in);

        // Read in the number of tokens
        int n = sc.nextInt();

        // This set will be in sorted order
        TreeSet<String> set = new TreeSet<>(); // Note the class needs to be comparable

        // This set will not be in sorted order
        // TreeSet<String> set = new TreeSet<>();

        // Read in n tokens
        for (int i = 0; i < n; i++) {
            set.add(sc.next());
        }
   

        // Iterate and print out each unique token only once
        for (String s : set) {
            System.out.println(s);
        }
    }
}
