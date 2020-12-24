import java.util.*;

public class Pedestals {
    public static class Graph {
        TreeSet<Integer>[] adj;
        int mat[][];
        int n;

        Graph(int[] parents){
            this.n = parents.length;
            adj = new TreeSet[this.n];
            for (int i = 0; i < n; i++)
                adj[i] = new TreeSet<Integer>();

            for (int i=0;i<parents.length;i++){
                if (parents[i] != -1){
                    adj[i].add(parents[i]);
                    adj[parents[i]].add(i);
                }
            }
        }

        Graph(int n){
            this.n = n;
            adj = new TreeSet[n];
            for (int i = 0; i < n; i++)
                adj[i] = new TreeSet<Integer>();
        }

        int distanceBetwen(int a, int b){
            return mat[a][b];
        }

        void add(int i,int j){
            adj[i].add(j);
            adj[j].add(i);
        }

        void calculateDistanceMatrix(){
            mat = new int[n][n];
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = n;
                    if (adj[i].contains(j)) mat[i][j] = 1;
                    if (i == j) mat[i][j] = 0;
                }
            }

            for(int k=0;k<n;k++){
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        if (mat[i][k] + mat[k][j] < mat[i][j])
                            mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }

        List<Integer> getCenters(){
            int currentMin = n;
            List<Integer> centers = new ArrayList<Integer>();

            for (int i=0;i<n;i++){
                int vertMax = 0;
                for(int j=0;j<n;j++){
                    if (mat[i][j] > vertMax) vertMax = mat[i][j];
                }

                if (vertMax < currentMin){
                    centers = new ArrayList<Integer>();
                    currentMin = vertMax;
                }

                if (vertMax <= currentMin){
                    centers.add(i);
                }
            }

            return centers;
        }

        int[] getShortestPathTree(int index){
            // Map node to their parents
            int[] parents = new int[n];
            // if parent is -1, it doesn't have a parent
            Arrays.fill(parents, -1);

            Queue<Integer> q = new LinkedList<Integer>();
            q.add(index);

            boolean[] visited = new boolean[n];
            visited[index] = true;

            while(!q.isEmpty()){
                int cur = q.poll();

                for (Integer next: adj[cur]){
                    if (!visited[next]){
                        parents[next] = cur;
                        q.add(next);
                        visited[next] = true;
                    }
                }

            }

//            for(int i=0;i<parents.length;i++){
//                System.out.println((i+1)+":"+(parents[i]+1));
//            }
            return parents;

        }

        int getFurthestNode(int index){
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(index);

            boolean[] visited = new boolean[n];
            visited[index] = true;

            int last = index;

            while (!q.isEmpty()){
                int cur = q.poll();

                for(Integer next: adj[cur]){
                    if (!visited[next]){
                        last = next;
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }

            return last;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Read in the number of nodes and edges
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Create the graph
        Graph g = new Graph(n);

        // Read in the edges
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            g.add(a, b);
        }
        g.calculateDistanceMatrix();

        List<Integer> centers = g.getCenters();
        int currentMinDist = Integer.MAX_VALUE;

        for (int center: centers){
            int[] parents = g.getShortestPathTree(center);
            Graph tree = new Graph(parents);
            tree.calculateDistanceMatrix();

            int a = tree.getFurthestNode(center);
            int b = tree.getFurthestNode(a);

            int d = tree.distanceBetwen(a,b);

            if (d < currentMinDist) {
                currentMinDist = d;
            }
        }

        System.out.println(currentMinDist);

    }
}