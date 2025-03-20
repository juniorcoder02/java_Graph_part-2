import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
    // Edge class to represent a weighted edge in the graph
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

    // Function to create an undirected weighted graph
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Initialize adjacency list for each vertex
        }
        
        // Adding edges (Graph is undirected, so we add both directions)
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    // Helper class to represent a vertex with its cost in the priority queue
    static class Pair implements Comparable<Pair> {
        int v;    // Vertex number
        int cost; // Cost associated with reaching this vertex

        public Pair(int v, int c) {
            this.v = v;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost; // Sorting in ascending order based on cost
        }
    }

    // Prim’s Algorithm to find the Minimum Spanning Tree (MST)
    public static void prims(ArrayList<Edge>[] graph) {
        int vertices = graph.length;       // Number of vertices
        boolean[] visited = new boolean[vertices]; // Track visited nodes
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // Min-heap to store the minimum cost edges
        pq.add(new Pair(0, 0)); // Start from vertex 0 with cost 0
        int finalCost = 0; // MST cost

        // Process nodes until all are visited
        while (!pq.isEmpty()) {
            Pair curr = pq.remove(); // Get the smallest weight edge

            // If the node is already visited, continue
            if (visited[curr.v]) {
                continue;
            }

            // Mark node as visited and add its cost to final MST cost
            visited[curr.v] = true;
            finalCost += curr.cost;

            // Traverse all adjacent edges of the current vertex
            for (int i = 0; i < graph[curr.v].size(); i++) {
                Edge e = graph[curr.v].get(i);
                if (!visited[e.dest]) {
                    pq.add(new Pair(e.dest, e.wt)); // Add adjacent edges to the priority queue
                }
            }
        }
        
        // Print the final MST cost
        System.out.println("Final minimum cost of MST : " + finalCost);
    }

    // Main function
    public static void main(String[] args) {
        int v = 4; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v]; // Graph adjacency list
        createGraph(graph); // Construct the graph
        prims(graph); // Compute MST using Prim’s algorithm
    }
}
