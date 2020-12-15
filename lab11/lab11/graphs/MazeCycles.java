package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cycle;

    public MazeCycles(Maze m) {
        super(m);
        cycle = false;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        boolean[] visited = new boolean[marked.length];
        cycleCheck(63, visited);
    }


    // Helper methods go here
    private void cycleCheck(int v, boolean[] visited) {
        visited[v] = true;
        // marked[v] = true;
        // announce();
        for (int w : maze.adj(v)) {
            if (!visited[w]) {
                edgeTo[w] = v;
                cycleCheck(w, visited);
                if (cycle) {
                    break;
                }
            } else if (w != edgeTo[v]) {
                cycle = true;
                edgeTo[w] = v;
                while (v != w) {
                    marked[v] = true;
                    announce();
                    v = edgeTo[v];
                }
                marked[w] = true;
                announce();
                break;
            }
        }

    }
}

