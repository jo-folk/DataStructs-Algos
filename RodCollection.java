
import java.util.*;

public class RodCollection {
    public static void main(String[] Args) {
        int C;// capacity in milligrams
        int R;// number of rods
        Scanner scanner = new Scanner(System.in);  //  Scanner object

        C = scanner.nextInt() * 1000;  // Read capacity and convert to milligrams
        R = scanner.nextInt();  // read number of rods

        long[] weights = new long[R];
        int[] values = new int[R];
        long totalWeight=0;
        int totalValue=0;
        for(int i=0;i<R;i++){
            values[i]= scanner.nextInt();  // value of ith rod
            totalValue+=values[i];
            weights[i]= (int)(scanner.nextFloat()*1000); // weight of ith rod
            totalWeight+= weights[i];
        }
        long[] answer = new long[totalValue + 1];
        // [ lowest weight for 0 value knap, lowest weight for 1 profit knap...
        for(int i=0;i<=totalValue;i++){
            answer[i]=totalWeight;
        }
        int ans=0;
        answer[0]=0;
        for (int i = 0; i < R; i++) { // current item
            for (int j = totalValue; j >= values[i]; j--) { // current value
                long possibleWeight = weights[i] + answer[j - values[i]];
                if (possibleWeight < answer[j]) {
                    if(possibleWeight<=C&& ans<j){// storing answer
                        ans=j;
                    }
                    answer[j] = possibleWeight;
                }
            }
        }
        System.out.println(ans);
    }
}
