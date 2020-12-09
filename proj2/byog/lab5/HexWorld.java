package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int HEIGHT = 60;
    private static final int WIDTH = 60;

    public static void drawHexagon(TETile[][] grid, int x, int y, int length, TETile tile){
        int drawLength = length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < drawLength; j++) {
                grid[x+j][y] = tile;
            }
            x -= 1;
            y -= 1;
            drawLength += 2;
        }
        x += 1;
        drawLength -= 2;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < drawLength; j++) {
                grid[x+j][y] = tile;
            }
            x += 1;
            y -= 1;
            drawLength -= 2;
        }
    }

    public static int[][] getAllPoints(int x, int y, int length){
        int[][] ret = new int[19][2];
        int index = 0;
        int incx = (length - 1) * 2 + 1;
        int incy = length;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                int[] tmp = {x, y - j * length * 2};
                ret[index++] = tmp;
            }
            x += incx;
            y += incy;
        }
        y -= 2 * incy;
        for (int i = 4; i > 2 ; i--) {
            for (int j = 0; j < i; j++) {
                int[] tmp = {x, y - j * length * 2};
                ret[index++] = tmp;
            }
            x += incx;
            y -= incy;
        }
        return ret;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // init
        TETile[][] grid = new TETile[WIDTH][HEIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[x][y] = Tileset.NOTHING;
            }
        }

        int[][] points = getAllPoints(5, 45, 3);
        for (int[] point: points) {
            drawHexagon(grid, point[0], point[1], 3, RandomWorldDemo.randomTile());
        }
        ter.renderFrame(grid);
    }
}
