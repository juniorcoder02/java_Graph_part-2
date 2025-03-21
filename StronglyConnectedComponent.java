import java.util.ArrayList;
import java.util.Stack;

public class StronglyConnectedComponent {
    // Kosaraju's Algorithm for finding Strongly Connected Components (SCCs) in O(V+E)
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Creates a directed graph using adjacency list representation
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }

    // Step 1: Perform Topological Sorting using DFS
    public static void topSort(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> s) {
        visited[curr] = true;
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                topSort(graph, e.dest, visited, s);
            }
        }
        s.push(curr); // Push finished node onto the stack
    }

    // Step 3: Perform DFS on the transposed graph to find SCCs
    public static void DFS(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        visited[curr] = true;
        System.out.print(curr + " ");
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                DFS(graph, e.dest, visited);
            }
        }
    }

    // Kosaraju's algorithm to find and print SCCs
    public static void kosaraju(ArrayList<Edge>[] graph, int v) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[v];

        // Step 1: Topological Sorting using DFS
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topSort(graph, i, visited, s);
            }
        }

        // Step 2: Transpose (Reverse) the Graph
        ArrayList<Edge>[] transpose = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            transpose[i] = new ArrayList<>();
        }
        for (int i = 0; i < v; i++) {
            for (Edge e : graph[i]) {
                transpose[e.dest].add(new Edge(e.dest, e.src)); // Reverse the edge
            }
        }

        // Step 3: Perform DFS on transposed graph to get SCCs
        visited = new boolean[v]; // Reset visited array
        while (!s.isEmpty()) {
            int curr = s.pop(); // Get nodes in topological order
            if (!visited[curr]) {
                System.out.print("Strongly Connected Component -> ");
                DFS(transpose, curr, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int v = 5; // Number of vertices
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        
        System.out.println("Finding Strongly Connected Components using Kosaraju's Algorithm:");
        kosaraju(graph, v);
    }
}