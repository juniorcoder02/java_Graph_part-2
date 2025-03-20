import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlights {
    
    // Class representing a directed edge in the graph
    static class Edge {
        int src, dest, wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Creating an adjacency list representation of the graph
    public static void createGraph(int[][] flights, ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            int src = flight[0], dest = flight[1], wt = flight[2];
            graph[src].add(new Edge(src, dest, wt));
        }
    }

    // Helper class to store information for BFS traversal
    static class Info {
        int v, cost, stops;
        
        public Info(int v, int cost, int stops) {
            this.v = v;
            this.cost = cost;
            this.stops = stops;
        }
    }

    /**
     * Finds the cheapest flight cost from source to destination with at most k stops.
     * Uses BFS approach (modified Dijkstra).
     * 
     * Approach:
     * - We use a queue to perform BFS traversal from the source.
     * - Maintain a distance array to track the minimum cost to reach each node.
     * - Only explore paths with stops <= k.
     * - If a cheaper path is found, update the distance array and add the node to the queue.
     * 
     * Time Complexity: O(V + E), where V is the number of cities and E is the number of flights.
     */
    public static int cheapestFlight(int n, int[][] flights, int src, int dest, int k) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraph(flights, graph);
        
        // Distance array to store the minimum cost to reach each city
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;
        
        // BFS queue initialized with source city
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));
        
        while (!q.isEmpty()) {
            Info curr = q.poll();
            if (curr.stops > k) {
                continue;
            }
            
            for (Edge e : graph[curr.v]) {
                int newCost = curr.cost + e.wt;
                if (newCost < distance[e.dest] && curr.stops <= k) {
                    distance[e.dest] = newCost;
                    q.add(new Info(e.dest, newCost, curr.stops + 1));
                }
            }
        }
        
        return (distance[dest] == Integer.MAX_VALUE) ? -1 : distance[dest];
    }

    public static void main(String[] args) {
        int n = 4; // Number of cities
        int[][] flights = {
            {0, 1, 100}, 
            {1, 2, 100}, 
            {1, 3, 600}, 
            {2, 3, 200}
        };
        int src = 0, dst = 3, k = 1;
        
        System.out.println("Cheapest Flight Cost: " + cheapestFlight(n, flights, src, dst, k));
    }
}