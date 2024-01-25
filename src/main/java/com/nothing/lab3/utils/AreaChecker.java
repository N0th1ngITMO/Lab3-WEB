package com.nothing.lab3.utils;

/**
 * Class for checking hit area of coordinates
 */
public class AreaChecker {

    /**
     * Check area on graph
     * @param x X coordinate
     * @param y Y coordinate
     * @param r R coordinate
     * @return String emoji of hit area
     */
    public static String checkArea(double x, double y, double r) {
        if (checkFirstArea(x, y, r)) {
            return "Hit";
        } else {
            return "Miss";
        }
    }

    private static boolean checkFirstArea(double x, double y, double r) {
        if(x <=0 && x >= (r/(-2)) && 0<=y && y<=r){
            return true;
        } else if (x >= 0 && y <= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2, 2)) {
            return true;
        } else if (x <= 0 && y <= 0 && y+(x/2)+(r/2) >= 0) {
            return true;
        }else{
            return false;
        }
    }
}
