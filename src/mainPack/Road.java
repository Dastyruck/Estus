/*
 * Road Class
 */

package mainPack;
import java.util.ArrayList;

/**
 *
 * @author Garrison Price
 */
public class Road implements TickBased {

    static int carCrossTime = Stats.carCrossTime;
    static int MaxCars = Stats.maxCarsTracked;
    private Runtime r = Runtime.getRuntime();

    protected int addCarInterval = Stats.addCarInterval;

    int weight = 0;
    int tickValue = 0;
    int waitTime = 0;
    int cars = 0;

    public Statistic TopWeight = new Statistic("Overall Top Road Weight");
    public Statistic TopWaitTime = new Statistic("Overall Top Wait Time");
    public Statistic CarsCrossing = new Statistic("Cars Crossing Per Light");

    public Road(int initialWeight){
        this(initialWeight, Stats.carWeightPerSec);
    }

    public Road(int initialWeight, int tickValue){
        this.weight = initialWeight;
        this.tickValue = tickValue;
    }

    public void go(){
        this.weight = 0;
        this.waitTime = 0;
        int carsLeaving = (int)Math.floor(Stats.maxCrossTime/Road.carCrossTime);
        if(carsLeaving > this.cars)
        {
            this.CarsCrossing.record(this.cars);
            this.cars = 0;
        }
        else
        {
            this.CarsCrossing.record(carsLeaving);
            this.cars -= carsLeaving;
        }

        
        this.r.gc();
    }

    public void tick(int secondsPassed){
        this.waitTime += 1;

        int numCars = this.cars;
        if(numCars > Stats.maxCarsTracked){
            numCars = Stats.maxCarsTracked;
        }

        // If the wait time is over than 60, the weight per second is doubled
        if(this.waitTime < 60){
            this.weight += this.tickValue * numCars;
        }else{
            this.weight += (this.tickValue * Stats.carWeightMinuteMultiplier) * numCars;
        }
        
        // Add a new car at set intervals
        if(secondsPassed % this.addCarInterval == 0){
           addCar();
        }

        // Record Statistics
        this.TopWaitTime.record(getWaitTime());
        this.TopWeight.record(getWeight());
    }

    public void addCar(){
        this.cars += 1;
    }

    public void addCar(Car newCar){
        this.cars += 1;
    }

    // Gets the current weight value
    public int getWeight(){
        return this.weight;
    }

    // Returns the number of cars currently waiting
    public int getCars(){
        return this.cars;
    }

    // Returns the amount of time since last go()
    public int getWaitTime(){
        return this.waitTime;
    }

    // Returns the cross time need for all cars to cross
    public int getCrossTime(){
        int crossTime = (int)Math.floor(Stats.maxCrossTime/Road.carCrossTime)*Road.carCrossTime;
        return crossTime;
    }

}