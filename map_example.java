
import java.util.*;

// have a list of pairs of friends
// store all the friend pairs

public class map_example {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, TreeSet<String>> all_pairs =
           new TreeMap<String, TreeSet<String>>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String a = sc.next();
            String b = sc.next();

            // Create the user if they don't exist
            if (!all_pairs.containsKey(a))
                all_pairs.put(a, new TreeSet<String>());
            
            // Create the user if they don't exist
            if (!all_pairs.containsKey(b))
                all_pairs.put(b, new TreeSet<String>());

            // Getting the friends list for both users
            TreeSet<String> as_friends = all_pairs.get(a);
            TreeSet<String> bs_friends = all_pairs.get(b);
            
            as_friends.add(b); // update their friends list
            bs_friends.add(a);
        }

        // Loop through all unique users we have seen
        for (String s : all_pairs.keySet()) {
            // Print their list of friends
            System.out.println(s + " " + all_pairs.get(s));
        }
    }
}


