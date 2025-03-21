public class FloodFillAlgorithm {
    /**
     * Performs flood fill on an image starting from the given source pixel.
     * 
     * @param images 2D matrix representing the image.
     * @param sr     Starting row index.
     * @param sc     Starting column index.
     * @param color  New color to fill.
     * @return Modified image after flood fill.
     */
    public int[][] floodFill(int[][] images, int sr, int sc, int color) {
        boolean[][] visited = new boolean[images.length][images[0].length];
        helper(images, sr, sc, color, visited, images[sr][sc]);
        return images;
    }

    /**
     * Helper method to recursively perform flood fill.
     * 
     * @param images 2D matrix representing the image.
     * @param sr     Current row index.
     * @param sc     Current column index.
     * @param color  New color to fill.
     * @param visited 2D boolean array to track visited pixels.
     * @param orgCol Original color of the starting pixel.
     */
    public void helper(int[][] images, int sr, int sc, int color, boolean[][] visited, int orgCol) {
        // Base case: Check for out-of-bounds, visited cells, or different color
        if (sr < 0 || sc < 0 || sr >= images.length || sc >= images[0].length || visited[sr][sc]
                || images[sr][sc] != orgCol) {
            return;
        }

        // Mark the current pixel as visited and change its color
        visited[sr][sc] = true;
        images[sr][sc] = color;

        // Recursive calls in all four directions
        helper(images, sr, sc - 1, color, visited, orgCol); // Left
        helper(images, sr, sc + 1, color, visited, orgCol); // Right
        helper(images, sr - 1, sc, color, visited, orgCol); // Up
        helper(images, sr + 1, sc, color, visited, orgCol); // Down
    }

    public static void main(String[] args) {
        // Example usage
        FloodFillAlgorithm obj = new FloodFillAlgorithm();
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1, sc = 1, color = 2;
        obj.floodFill(image, sr, sc, color);
        
        // Print the modified image
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
