
import java.util.ArrayList;
import java.util.Scanner;

public class list {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> ourList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ourList.add(sc.next());
        }
        for (String x : ourList) {
            System.out.println(x);
        }
    }
} 
