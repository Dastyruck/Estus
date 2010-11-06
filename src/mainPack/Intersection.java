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
    private boolean debug = true;

    private ArrayList<Road> roads = new ArrayList();
    
    private int crossTime = 0;
    private int currentCrossTime = 0;

    public Counter greenSignals = new Counter();
    public Counter carsCrossed = new Counter();

    public void start(int testLength){

        if(testLength > 100){
            this.debug = false;
        }
            
        // Start the test
        test(testLength);
    }

    public void addRoad(Road newRoad){
        this.roads.add(newRoad);
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

        return;
    }

    private void tick(int secondsPassed){

        for(int i=0; i < this.roads.size(); i++){

            // Tell lights to calculate their values
            this.roads.get(i).tick(secondsPassed);
        }
        
    }

    public int numCarsLeft()
    {
        int count = 0;
        for(int x = 0;x<roads.size();x++)
        {
            count += roads.get(x).getCars();
        }
        return count;
    }

    private int changeRoads(){

        // Get the road with the most weight
        int highestRoad = getHighestRoad();

        int crossTime = this.roads.get(highestRoad).getCrossTime();

        // Record the green light
        this.greenSignals.increase();

        // Calculate how many cars are leaving and add it to the counter
        int maxCarsLeaving = (int)Math.floor(Stats.maxCrossTime/Road.carCrossTime);

        int carsLeaving = this.roads.get(highestRoad).getCars();
        if(carsLeaving > maxCarsLeaving){
            carsLeaving = maxCarsLeaving;
        }
        this.carsCrossed.increase(carsLeaving);

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
