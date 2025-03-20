import java.util.PriorityQueue;

public class ConnectingCities {
    // Edge class for priority queue comparison
    static class Edge implements Comparable<Edge> {
        int dest, cost;

        public Edge(int d, int c) {
            this.dest = d;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.cost - e2.cost; // Sort by ascending cost (min-heap)
        }
    }

    // Function to compute minimum cost to connect all cities using Prim's algorithm
    public static int connectCities(int[][] cities) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(); // Min-heap for edges
        boolean[] visited = new boolean[cities.length]; // Track visited cities

        pq.add(new Edge(0, 0)); // Start from city 0
        int finalCost = 0; // Total cost to connect all cities

        while (!pq.isEmpty()) {
            Edge curr = pq.remove(); // Extract the minimum-cost edge
            if (!visited[curr.dest]) { // If city is not already connected
                visited[curr.dest] = true;
                finalCost += curr.cost; // Add cost to final cost

                // Add all unvisited neighboring cities to the priority queue
                for (int i = 0; i < cities[curr.dest].length; i++) {
                    if (!visited[i] && cities[curr.dest][i] != 0) {
                        pq.add(new Edge(i, cities[curr.dest][i]));
                    }
                }
            }
        }

        return finalCost;
    }

    public static void main(String[] args) {
        int[][] cities = {
            { 0, 1, 2, 3, 4 },
            { 1, 0, 5, 0, 7 },
            { 2, 5, 0, 6, 0 },
            { 3, 0, 6, 0, 0 },
            { 4, 7, 0, 0, 0 }
        };

        System.out.println("Minimum cost to connect all cities: " + connectCities(cities));
    }
}
