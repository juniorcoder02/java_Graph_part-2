public class DisjointSet {
    // Number of elements in the disjoint set
    static int n = 7;
    
    // Parent array to store the representative (leader) of each set
    static int[] parent = new int[n];
    
    // Rank array to store the depth of trees (helps in union by rank)
    static int[] rank = new int[n];

    /**
     * Initializes the disjoint set by setting each element as its own parent.
     * This represents 'n' disjoint sets initially.
     */
    public static void init() {
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Every node is its own parent initially
            rank[i] = 0; // Initial rank is 0 for all elements
        }
    }

    /**
     * Finds the representative (leader) of the set to which 'x' belongs.
     * Implements path compression optimization to flatten the structure.
     * 
     * @param x The element whose leader is to be found
     * @return The representative (leader) of the set containing 'x'
     */
    public static int find(int x) { 
        if (x == parent[x]) {
            return x; // If 'x' is the leader of its set, return 'x'
        }
        // Path compression: Make the parent of 'x' point directly to the root
        return parent[x] = find(parent[x]); 
    }

    /**
     * Performs the union of two sets containing elements 'a' and 'b'.
     * Uses union by rank to keep the tree as flat as possible.
     * 
     * @param a An element from the first set
     * @param b An element from the second set
     */
    public static void union(int a, int b) {
        int rootA = find(a); // Find leader of set containing 'a'
        int rootB = find(b); // Find leader of set containing 'b'

        if (rootA != rootB) { // Only merge if they belong to different sets
            if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA; // Attach smaller tree under bigger tree
            } else if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA; // Arbitrarily make rootA the leader
                rank[rootA]++; // Increase rank as height increases
            }
        }
    }

    /**
     * Main method to demonstrate the working of the Disjoint Set data structure.
     */
    public static void main(String[] args) {
        init(); // Initialize the disjoint set

        System.out.println("Find(3): " + find(3)); // Initially, every element is its own leader

        union(1, 3); // Merge sets containing 1 and 3
        System.out.println("Find(3) after union(1,3): " + find(3));

        union(2, 4); // Merge sets containing 2 and 4
        union(3, 6); // Merge sets containing 3 and 6
        union(1, 4); // Merge sets containing 1 and 4 (indirectly merging 3, 6, 2, 4, 1)

        System.out.println("Find(3) after more unions: " + find(3));
        System.out.println("Find(4): " + find(4));

        union(1, 5); // Merge sets containing 1 and 5
    }
}