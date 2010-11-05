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

    public static void main(String [] args){
        int testLength = 1000000;

        Road roads[] = new Road[4];
        roads[0] = new Road(2, 1);
        roads[1] = new Road(3, 2);
        roads[2] = new Road(4, 3);
        roads[3] = new Road(5, 4);
        
        test(roads, testLength);
    }

    private static void test(Road [] roads, int testLength){
        int overallTopRoad = 0;
        int overallTopRoadWeight = 0;

        int overallTopWaitRoad = 0;
        int overallTopWaitTime = 0;

        int secondsPassed = 1;
        int numRoads = roads.length;

        // The amount of time it takes for cars to get accross;
        int crossTime = 0;
        int defaultCrossTime = 15;

        // How long the current crossing has gone on
        int crossTimePassed = 0;
        
        // Test Processing
        while(secondsPassed <= testLength){

            for(int i=0; i < numRoads; i++){
                roads[i].tick();
            }

            int roadWeights[] = new int[roads.length];
            for(int i=0; i < numRoads; i++){
                roadWeights[i] = roads[i].getWeight();
            }

            int highestRoad = maxByKey(roadWeights);
            int highestRoadValue = maxByValue(roadWeights);

            if(roads[highestRoad].getWaitTime() > overallTopWaitTime){
                overallTopWaitTime = roads[highestRoad].getWaitTime();
                overallTopWaitRoad = highestRoad;
            }

            // Wait for cars to pass before starting a new road
            if(crossTimePassed == crossTime){

                // Figure out the cross time needed
                crossTimePassed = 0;
                crossTime = roads[highestRoad].getCrossTime();

                // Lets the cars go
                roads[highestRoad].go();

            }else{

                // Increment until cross time reached
                crossTimePassed++;
                
            }
            

            if(highestRoadValue > overallTopRoadWeight){
                overallTopRoad = highestRoad;
                overallTopRoadWeight = highestRoadValue;
            }

            secondsPassed = secondsPassed + 1;
        }

        System.out.println(testLength + " seconds of testing completed.");
        System.out.println("Highest Road Weight Achieved: " + overallTopRoadWeight + ". (Road " + overallTopRoad + ")");
        System.out.println("Highest Road Wait Time Achieved: " + overallTopWaitTime + " seconds. (Road " + overallTopWaitRoad + ")");
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
}
