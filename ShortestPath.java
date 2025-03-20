import java.util.ArrayList;

public class ShortestPath {
    
    // Class to represent an edge in the graph
    static class Edge {
        int src;  // Source vertex
        int dest; // Destination vertex
        int wt;   // Weight of the edge

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    // Method to create a directed weighted graph using adjacency list representation
    public static void createGraph2(ArrayList<Edge> graph) {
        graph.add(new Edge(0, 1, 2));
        graph.add(new Edge(0, 2, 4));

        graph.add(new Edge(1, 2, -4));

        graph.add(new Edge(2, 3, 2));

        graph.add(new Edge(3, 4, 4));

        graph.add(new Edge(4, 1, -1));
    }

    // Bellman-Ford Algorithm for Single-Source Shortest Paths
    // Time Complexity: O(VE), where V is the number of vertices and E is the number of edges
    public static void bellmanFord(ArrayList<Edge> graph, int src, int V) {
        // Step 1: Initialize distance array with infinity (except source vertex)
        int[] distance = new int[V];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = (i == src) ? 0 : Integer.MAX_VALUE;
        }

        // Step 2: Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (Edge e : graph) { // Iterating over all edges
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;

                // Relaxation step: Update distance if a shorter path is found
                if (distance[u] != Integer.MAX_VALUE && distance[u] + wt < distance[v]) {
                    distance[v] = distance[u] + wt;
                }
            }
        }

        // Step 3: Print the shortest distances
        System.out.println("Shortest distances from source vertex " + src + ":");
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.print("INF "); // If vertex is unreachable, print INF
            else
                System.out.print(distance[i] + " ");
        }
    }

    public static void main(String[] args) {
        int v = 5; // Number of vertices
        ArrayList<Edge> graph = new ArrayList<>();

        // Creating the graph with edges
        createGraph2(graph);

        // Running Bellman-Ford algorithm from source vertex 0
        bellmanFord(graph, 0, v);
    }
}