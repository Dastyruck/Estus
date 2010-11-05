/*
 * Main intersection class, puts it all together.
 */

package mainPack;

import java.util.ArrayList;

/**
 *
 * @author Garrison Price
 */
public class Intersection
{
    private static int DefaultCrossTime = 15;

    private boolean debug = true;

    private ArrayList<Road> roads= new ArrayList();
    private int crossTime = 0;

    private int currentCrossTime = 0;

    private Statistic overallTopRoadWeight = new Statistic("Overall Top Road Weight");
    private Statistic overallTopWaitTime = new Statistic("Overall Top Wait Time");

    private Counter greenSignals = new Counter();
    private Counter carsCrossed = new Counter();

    public void start(){
        int testLength = 10000000;

        if(testLength > 1000){
            this.debug = false;
        }

        // Create the Roads
        for(int x = 0; x<4;x++)
            this.roads.add(new Road(1, 10));

        // Start the test
        test(testLength);
    }

    private void test( int testLength){
        
        int secondsPassed = 1;
        
        // Test Processing
        while(secondsPassed <= testLength){

            log(secondsPassed);

            tick(secondsPassed);

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
        System.out.println("Total Green Lights Given: " + this.greenSignals.getNum());
        System.out.println("Total Cars Crossed: " + this.carsCrossed.getNum());
    }

    private void tick(int secondsPassed){

        for(int i=0; i < this.roads.size(); i++){

            // Tell lights to calculate their values
            this.roads.get(i).tick(secondsPassed);

            // Record Statistics
            this.overallTopWaitTime.record(this.roads.get(i).getWaitTime(), i);
            this.overallTopRoadWeight.record(this.roads.get(i).getWeight(), i);
        }
        
    }

    private int changeRoads(){

        // Get the road with the most weight
        int highestRoad = getHighestRoad();

        int crossTime = this.roads.get(highestRoad).getCrossTime();

        this.greenSignals.increase();
        this.carsCrossed.increase(this.roads.get(highestRoad).getCars());

        // Lets the cars go
        this.roads.get(highestRoad).go();
        

        return crossTime;

    }

    private int getHighestRoad(){
        ArrayList<Integer> roadWeights = new ArrayList();
        for(int i=0; i < this.roads.size(); i++){
            log("Road " + i + " has " + roads.get(i).getWeight());
            roadWeights.add(roads.get(i).getWeight());
        }
        int highestRoad = maxByKey(roadWeights);

        return highestRoad;
    }

    private static int maxByValue(ArrayList<Integer> t) {
        int maximumKey = 0;   // start with the first value
        int maximumValue = t.get(0);
        for (int i=1; i<t.size(); i++) {
            if (t.get(i) > maximumValue) {
                maximumKey = i;
                maximumValue = t.get(i);
            }
        }
        return maximumValue;
    }

    private static int maxByKey(ArrayList<Integer> t) {
        int maximumKey = 0;   // start with the first value
        int maximumValue = t.get(0);
        for (int i=1; i<t.size(); i++) {
            if (t.get(i) > maximumValue) {
                maximumKey = i;
                maximumValue = t.get(i);
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
