/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainPack;
import java.util.ArrayList;

/**
 *
 * @author Tyler(Chacha) chacha@chacha102.com
 */
public class Main {

    public static void main(String [] args){
        
        // Create and add roads to the intersection
        Intersection intersection = new Intersection();
        ArrayList<Road> roads = new ArrayList();
        for(int x = 0; x < Stats.numRoads; x++){

            Road newRoad = new Road(1);
            roads.add(newRoad);
            intersection.addRoad(newRoad);
            
        }

        // Decide how long to run the test for
        if(args.length > 0){

            // Use the first argument
            intersection.start(Integer.parseInt(args[0]));
            System.out.println("Ran for " +Integer.parseInt(args[0])+" seconds." );

        }else{

            // Use the default
            intersection.start(Stats.testLength);
            System.out.println("Ran for " + Stats.testLength +" seconds." );
            
        }
        

        Statistic overallTopRoadWeight = new Statistic("Overall Top Road Weight");
        Statistic overallTopWaitTime = new Statistic("Overall Top Wait Time");
        Statistic carsCrossing = new Statistic("Cars Crossing Per Green");

        System.out.println("Size: " + roads.size());

        for(int x = 0; x < roads.size(); x++){
            overallTopRoadWeight.record(roads.get(x).TopWeight.getAverage(), x);
            overallTopWaitTime.record(roads.get(x).TopWaitTime.getAverage(), x);
            carsCrossing.record(roads.get(x).CarsCrossing.getAverage(), x);
        }

        System.out.println("Highest Road Weight Achieved: " + overallTopRoadWeight.getHighest() + ". (Road " + overallTopRoadWeight.getHighestRoad() + ")");
        System.out.println("Highest Road Wait Time Achieved: " + overallTopWaitTime.getHighest() + " seconds. (Road " + overallTopWaitTime.getHighestRoad() + ")");

        System.out.println("Average Road Weight: " + overallTopRoadWeight.getAverage() + ".");
        System.out.println("Average Road Wait Time: " + overallTopWaitTime.getAverage() + " seconds.");

        System.out.println("Lowest Road Weight Achieved: " + overallTopRoadWeight.getLowest() + ". (Road " + overallTopRoadWeight.getLowestRoad() + ")");
        System.out.println("Lowest Road Wait Time Achieved: " + overallTopWaitTime.getLowest() + " seconds. (Road " + overallTopWaitTime.getLowestRoad() + ")");

        System.out.println("Total Green Lights Given: " + intersection.greenSignals.getNum());
        System.out.println("Total Cars Crossed: " + intersection.carsCrossed.getNum());

        System.out.println("Average Cars Crossed: " + carsCrossing.getAverage());
    }

}
