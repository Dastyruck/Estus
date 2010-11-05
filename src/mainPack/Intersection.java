/*
 * Main intersection class, puts it all together.
 */

package mainPack;

/**
 *
 * @author Garrison Price
 */
public class Intersection
{
    private static int DefaultCrossTime = 15;

    private boolean debug = true;

    private Road roads[] = new Road[4];
    private int crossTime = 0;

    private int currentCrossTime = 0;

    private Statistic overallTopRoadWeight = new Statistic("Overall Top Road Weight");
    private Statistic overallTopWaitTime = new Statistic("Overall Top Wait Time");

    public void start(){
        int testLength = 1000000000;

        if(testLength > 1000){
            this.debug = false;
        }

        // Create the Roads
        this.roads[0] = new Road(1, 10);
        this.roads[1] = new Road(1, 10);
        this.roads[2] = new Road(1, 10);
        this.roads[3] = new Road(1, 10);

        // Start the test
        test(testLength);
    }

    private void test( int testLength){
        
        int secondsPassed = 1;
        
        // Test Processing
        while(secondsPassed <= testLength){

            log(secondsPassed);

            tick();

            // Wait for cars to pass before starting a new road
            if(this.currentCrossTime == this.crossTime){

                this.crossTime = changeRoads();
                this.currentCrossTime = 0;

                log("Starting Traffic ("+ this.crossTime +")");

            }else{

                // Increment until cross time reached
                this.currentCrossTime++;
                
            }
            
            secondsPassed = secondsPassed + 1;
        }

        System.out.println(testLength + " seconds of testing completed.");
        System.out.println("Highest Road Weight Achieved: " + this.overallTopRoadWeight.getHighest() + ". (Road " + this.overallTopRoadWeight.getHighestRoad() + ")");
        System.out.println("Highest Road Wait Time Achieved: " + this.overallTopWaitTime.getHighest() + " seconds. (Road " + this.overallTopWaitTime.getHighestRoad() + ")");
        System.out.println("Average Road Weight: " + this.overallTopRoadWeight.getAverage() + ".");
        System.out.println("Average Road Wait Time: " + this.overallTopWaitTime.getAverage() + " seconds.");
        System.out.println("Lowest Road Weight Achieved: " + this.overallTopRoadWeight.getLowest() + ". (Road " + this.overallTopRoadWeight.getLowestRoad() + ")");
        System.out.println("Lowest Road Wait Time Achieved: " + this.overallTopWaitTime.getLowest() + " seconds. (Road " + this.overallTopWaitTime.getLowestRoad() + ")");
    }

    private void tick(){

        for(int i=0; i < this.roads.length; i++){

            // Tell lights to calculate their values
            this.roads[i].tick();

            // Record Statistics
            this.overallTopWaitTime.record(this.roads[i].getWaitTime(), i);
            this.overallTopRoadWeight.record(this.roads[i].getWeight(), i);
        }
        
    }

    private int changeRoads(){

        // Get the road with the most weight
        int highestRoad = getHighestRoad();

        int crossTime = this.roads[highestRoad].getCrossTime();

        // Lets the cars go
        this.roads[highestRoad].go();

        return crossTime;

    }

    private int getHighestRoad(){
        int roadWeights[] = new int[this.roads.length];
        for(int i=0; i < this.roads.length; i++){
            log("Road " + i + " has " + roads[i].getWeight());
            roadWeights[i] = roads[i].getWeight();
        }
        int highestRoad = maxByKey(roadWeights);

        return highestRoad;
    }

    private static int maxByValue(int[] t) {
        int maximumKey = 0;   // start with the first value
        int maximumValue = t[0];
        for (int i=1; i<t.length; i++) {
            if (t[i] > maximumValue) {
                maximumKey = i;
                maximumValue = t[i];
            }
        }
        return maximumValue;
    }

    private static int maxByKey(int[] t) {
        int maximumKey = 0;   // start with the first value
        int maximumValue = t[0];
        for (int i=1; i<t.length; i++) {
            if (t[i] > maximumValue) {
                maximumKey = i;
                maximumValue = t[i];
            }
        }
        return maximumKey;
    }

    private void log(String message){
        if(this.debug == true){
            System.out.println(message);
        }
    }

    private void log(int message){
        if(this.debug == true){
            System.out.println(message);
        }
    }
}
