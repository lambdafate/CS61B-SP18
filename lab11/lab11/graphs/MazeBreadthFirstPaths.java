package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int t;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            marked[v] = true;
            announce();
            if (v == t) {
                break;
            }
            for (int tmp : maze.adj(v)) {
                if (distTo[tmp] == Integer.MAX_VALUE) {
                    edgeTo[tmp] = v;
                    distTo[tmp] = distTo[v] + 1;
                    queue.offer(tmp);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

