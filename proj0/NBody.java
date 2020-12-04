/*
 * @Author: lambdafate
 * @Description: 
 * @Date: 2020-12-04 16:17:10
 */

public class NBody {
    private static final String background = "images/starfield.jpg";

    public static double readRadius(String filepath) {
        In in = new In(filepath);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filepath) {
        In in = new In(filepath);
        int line = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[line];
        for (int i = 0; i < line; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String g = in.readString();
            Planet p = new Planet(xp, yp, xv, yv, m, g);
            planets[i] = p;
        }
        return planets;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You must input: java NBody T dt filepath");
            return;
        }
        // handler input
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filepath = args[2];

        // read info from filepath
        double radius = readRadius(filepath);
        Planet[] planets = readPlanets(filepath);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double[] xForce = new double[planets.length];
        double[] yForce = new double[planets.length];
        double time = 0;
        while (time < T) {
            StdDraw.clear();
            StdDraw.picture(0, 0, background);
            // 计算当前时刻所有星球受力
            for (int i = 0; i < planets.length; i++) {
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // 更新星球位置并绘制到屏幕上
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForce[i], yForce[i]);
                planets[i].draw();
            }
            time += dt;
            StdDraw.show();
            StdDraw.pause(2);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}