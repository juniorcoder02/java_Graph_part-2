import java.util.ArrayList;
import java.util.Collections;

public class KruskalAlgorithm {
    // Class representing an edge in the graph
    static class Edge implements Comparable<Edge> {
        int src;  // Source vertex
        int dest; // Destination vertex
        int wt;   // Weight of the edge

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.wt - e2.wt; // Sorting edges based on weight (Ascending order)
        }
    }

    /**
     * Creates a graph by adding edges to the edge list.
     * 
     * @param edges The list of edges representing the graph.
     */
    public static void createGraph(ArrayList<Edge> edges) {
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(1, 3, 40));
        edges.add(new Edge(2, 3, 50));
    }

    static int n = 4; // Number of vertices
    static int[] parent = new int[n]; // Parent array for Union-Find
    static int[] rank = new int[n];   // Rank array for Union by Rank optimization

    /**
     * Initializes the Disjoint Set data structure.
     * Each vertex is initially its own parent.
     */
    public static void init() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0; // Initially, ranks are 0
        }
    }

    /**
     * Finds the representative (leader) of the set containing 'x' using Path Compression.
     * 
     * @param x The element whose set leader is to be found.
     * @return The representative of the set containing 'x'.
     */
    public static int find(int x) {
        if (parent[x] == x) {
            return x; // If 'x' is its own leader, return 'x'
        }
        return parent[x] = find(parent[x]); // Path compression
    }

    /**
     * Unites two disjoint sets containing elements 'a' and 'b'.
     * Uses Union by Rank to keep the tree flat.
     * 
     * @param a An element from the first set.
     * @param b An element from the second set.
     */
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) { // Merge only if they belong to different sets
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++; // Increase rank if both sets have the same rank
            }
        }
    }

    /**
     * Implements Kruskal's Algorithm to find the Minimum Spanning Tree (MST).
     * 
     * @param edges The list of all edges in the graph.
     * @param v     The number of vertices in the graph.
     */
    public static void kruskalMST(ArrayList<Edge> edges, int v) {
        init(); // Initialize the disjoint set
        Collections.sort(edges); // Sort edges based on weight (O(E log E))
        int mstCost = 0;
        int edgeCount = 0; // To track number of edges included in MST

        for (int i = 0; edgeCount < v - 1; i++) { // MST contains (v-1) edges
            Edge e = edges.get(i);
            int rootA = find(e.src);
            int rootB = find(e.dest);
            
            if (rootA != rootB) { // If adding this edge does not form a cycle
                union(e.src, e.dest);
                mstCost += e.wt;
                edgeCount++; // Increase count of edges in MST
            }
        }
        System.out.println("Minimum Spanning Tree Cost: " + mstCost);
    }

    /**
     * Main method to demonstrate Kruskal's Algorithm.
     */
    public static void main(String[] args) {
        int v = 4; // Number of vertices
        ArrayList<Edge> edges = new ArrayList<>(); // List to store graph edges
        createGraph(edges); // Initialize graph
        kruskalMST(edges, v); // Compute MST
    }
}
