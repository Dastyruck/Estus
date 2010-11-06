package mainPack;
public class Stats {

    /*
     * Test Numbers
     */
    public static int testLength = 10000;

    /*
     * Intersection Numbers
     */
    
    // The number of roads connecting to the intersection
    public static int numRoads = 3;

    // How many seconds between adding more cars to a road
    public static int addCarInterval = 60;
    public static int addCarIntervalHigh = 30;

    public static int defaultCarsPerHour = 100;

    /*
     * Car Numbers
     */

    // How long it takes a car to cross the intersection
    public static int carCrossTime = 5;

    // How many cars can contribute to the tick value
    public static int maxCarsTracked = 20;

    /*
     * Weighting Numbers
     */

    // Amount of points generated per car per second
    public static int carWeightPerSec = 1;

    // Multiplies carWeightPerSec by this amount after 1 minute of waiting
    public static int carWeightMinuteMultiplier = 2;

    // Multiplies carWeightPerSec by this amount after 1 minute of waiting
    public static int maxCrossTime = 25;

}
