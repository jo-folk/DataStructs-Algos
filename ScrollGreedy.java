
// minimum number of announcements problem solution
import java.util.*;
import java.lang.Math;

public class ScrollGreedy {
    public static int oo = 987654321;
    public static void main(String[] Args) {
        // These events store the student sorted by their end time
        ArrayList<Event> eves = new ArrayList<Event>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Grab all the times
        for (int i = 0; i < n; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            eves.add(new Event(first, second));
        }

        // Sort by the end
        Collections.sort(eves);


        int FirstTraslator=0;
        int SecondTraslator=0;
        // Loop over the student

        for (Event e : eves) {
            FirstTraslator+=e.first;
            SecondTraslator=Math.max(FirstTraslator+e.second,SecondTraslator+e.second);            
        }

        // Print the answer
        System.out.println(SecondTraslator);
    }

    // Student interval
    public static class Event implements Comparable<Event> {
        int first, second, type;// type is group number

        Event(int first, int second) {
            this.first = first;
            this.second = second;

            //initalizing group number
            if(first<second){
                type=1;
            }else if(first==second){
                type=2;
            }
            else{
                type =3;
            }
        }
        
        public int compareTo(Event o) { // (this, o) (FIRST, SECOND)
            if(this.type<o.type){
                return -1;
            }
            else if(this.type>o.type){
                return 1;
            }
            else{ 
                // breaking ties
                if(this.type!=3){
                    if(this.first<o.first){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }
                else{
                    if(this.second>o.second){
                        return -1;
                    }
                    else{
                        return 1;
                    }

                }
            }
        }
    }
}
