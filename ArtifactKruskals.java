
import java.util.*;



public class ArtifactKruskals {

    public static int arr[];
    public static int queries;
    public static int nodes;

    // The main method
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);

        // Read in the number of nodes and edges
        nodes = sc.nextInt();
        int edges = sc.nextInt();

        // array to store the set number
        arr = new int[nodes];

        // new disjoint set number for every node
        for(int i=0;i<nodes;i++){
            arr[i]=i;
        }

        // Create the placeholder memory
        ArrayList<Event> list = new ArrayList<Event>();


        // Read in all the edges
        for (int i = 0; i < edges; i++) {

            // Adjust for the 0 indexing
            int st = sc.nextInt() - 1;
            int en = sc.nextInt() - 1;
            int weight = sc.nextInt();

            // Add the undirected edge
            list.add(new Event(1, st, en , weight, -1));

        }
        queries=sc.nextInt();
        boolean results[]=new boolean[queries];

        // Read in all the queries
        for (int i = 0; i < queries; i++) {

            // Adjust for the 0 indexing
            int st = sc.nextInt() - 1;
            int en = sc.nextInt() - 1;
            int weight = sc.nextInt();

            // Add the undirected edge
            list.add(new Event(0, st, en , weight, i));
        }

        // sorting the events
        Collections.sort(list);

        // processing the events
        for(Event e: list){
            if(e.type==0){
                results[e.queryIndex]=(arr[e.start]==arr[e.end]);
            }
            else{
                if(arr[e.start]!=arr[e.end]){
                    join(arr[e.start],arr[e.end]);
                }

            }
        }


        //printing the results of queries
        for(int i=0;i<queries;i++){
            if(results[i]){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
        }


    }


    // method to join the sets with the given numbers, the set number of the joined set will be the lower set number of two sets
    public static void join(int start,int end){
        if(start>end){
            int temp=start;
            start=end;
            end=temp;
        }
        for(int i=0;i<nodes;i++){
            if(arr[i]==end){
                arr[i]=start;
            }
        }
    }

    // class to store the information of any event
    public static class Event implements Comparable<Event>{
        int type; // 0 a query and 1 a path connecting pedestals
        int start, end, w;
        int queryIndex; // stores which query this corresponds to

        public Event(int type, int start, int end, int w, int queryIndex) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.w = w;
            this.queryIndex = queryIndex;
        }

        // The edge comparison method
        public int compareTo(Event o) {
            return Integer.compare(o.w, w);
        }

        @Override
        public String toString() {
            return "Event{" +
                    "type=" + type +
                    ", start=" + start +
                    ", end=" + end +
                    ", w=" + w +
                    ", queryIndex=" + queryIndex +
                    "}\n";
        }
    }
}

