/*
 * @Author: lambdafate
 * @Description: 
 * @Date: 2020-12-04 13:41:45
 */

public class Planet {
    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * @description: 计算Planet之间的距离
     * @param {*}
     * @return {*}
     */
    public double calcDistance(Planet p) {
        double x = Math.abs(xxPos - p.xxPos);
        double y = Math.abs(yyPos - p.yyPos);
        double distance = x * x + y * y;
        return Math.sqrt(distance);
    }

    /**
     * @description: 计算Planet之间的万有引力 F = G * m1 * m2 / r*r
     * @param {*}
     * @return {*}
     */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        // make sure r > 0
        if (r <= 0) {
            return 0;
        }
        return G * this.mass * p.mass / (r * r);
    }

    /**
     * @description: 计算万有引力在x方向的分力(注意正负)
     * @param {*}
     * @return {*}
     */
    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (distance <= 0) {
            return 0;
        }
        double cosx = (p.xxPos - this.xxPos) / distance;
        return F * cosx;
    }

    /**
     * @description: 计算万有引力在y方向上的分力
     * @param {*}
     * @return {*}
     */
    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (distance <= 0) {
            return 0;
        }
        double sinx = (p.yyPos - this.yyPos) / distance;
        return F * sinx;
    }

    /**
     * @description: 计算其他所有行星对本星球的x方向万有引力之和
     * @param {Planet[]} allPlanets
     * @return {*}
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double F = 0.0;
        for (Planet planet : allPlanets) {
            F += calcForceExertedByX(planet);
        }
        return F;
    }

    /**
     * @description: 计算其他所有行星对本星球的y方向万有引力之和
     * @param {Planet[]} allPlanets
     * @return {*}
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double F = 0.0;
        for (Planet planet : allPlanets) {
            F += calcForceExertedByY(planet);
        }
        return F;
    }

    /**
     * @description: 在dt时间内, 更新星球的位置
     * @param {double} dt
     * @param {double} fX
     * @param {double} fY
     * @return {*}
     */
    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}